package br.com.lukinhasssss.crud

import br.com.lukinhasssss.ConsultarRequest
import br.com.lukinhasssss.ConsultarResponse
import br.com.lukinhasssss.ConsultarServiceGrpc
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton

@Singleton
class ConsultarEndpoint (
    private val cadastroRepository: CadastroRepository
) : ConsultarServiceGrpc.ConsultarServiceImplBase() {

    override fun consultar(request: ConsultarRequest, responseObserver: StreamObserver<ConsultarResponse>) {
        cadastroRepository.findById(request.id).let {
            responseObserver.onNext(it.get().toGrpc())
            responseObserver.onCompleted()
        }
    }

}

private fun Cadastro.toGrpc(): ConsultarResponse? {
    return ConsultarResponse.newBuilder()
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