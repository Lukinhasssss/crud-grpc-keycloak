package br.com.lukinhasssss.crud

import br.com.lukinhasssss.ListarRequest
import br.com.lukinhasssss.ListarResponse
import br.com.lukinhasssss.ListarServiceGrpc
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton

@Singleton
class ListarEndpoint(
    private val cadastroRepository: CadastroRepository
) : ListarServiceGrpc.ListarServiceImplBase() {

    override fun listar(request: ListarRequest?, responseObserver: StreamObserver<ListarResponse>?) {
        val cadastros = cadastroRepository.findAll().map {
            ListarResponse.Lista.newBuilder()
                .setId(it.id.toString())
                .setCep(it.cep)
                .setBairro(it.bairro)
                .setComplemento(it.complemento)
                .setDdd(it.ddd)
                .setIbge(it.ibge)
                .setLocalidade(it.localidade)
                .setLogradouro(it.logradouro)
                .setUf(it.uf)
                .build()
        }

        responseObserver?.onNext(ListarResponse.newBuilder()
            .addAllLista(cadastros)
            .build())
        responseObserver?.onCompleted()
    }
}