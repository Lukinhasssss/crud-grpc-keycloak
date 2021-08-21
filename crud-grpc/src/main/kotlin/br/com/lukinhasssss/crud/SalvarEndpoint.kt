package br.com.lukinhasssss.crud

import br.com.lukinhasssss.SalvarRequest
import br.com.lukinhasssss.SalvarResponse
import br.com.lukinhasssss.SalvarServiceGrpc
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton

@Singleton
class SalvarEndpoint (
    private val cadastroRepository: CadastroRepository,
    private val viaCepClient: ViaCepClient
) : SalvarServiceGrpc.SalvarServiceImplBase() {

    override fun salvar(request: SalvarRequest, responseObserver: StreamObserver<SalvarResponse>) {
        viaCepClient.consultaCep(request.cep).let { response ->
            cadastroRepository.save(response?.body()?.toModel()).let {
                responseObserver.onNext(it?.toGrpc())
                responseObserver.onCompleted()
            }
        }
    }

}

private fun Cadastro.toGrpc(): SalvarResponse? {
    return SalvarResponse.newBuilder()
        .setBairro(bairro)
        .setCep(cep)
        .setComplemento(complemento)
        .setDdd(ddd)
        .setIbge(ibge)
        .setLocalidade(localidade)
        .setLogradouro(logradouro)
        .setUf(uf)
        .build()
}
