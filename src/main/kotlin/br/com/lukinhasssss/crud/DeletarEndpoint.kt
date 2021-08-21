package br.com.lukinhasssss.crud

import br.com.lukinhasssss.DeletarRequest
import br.com.lukinhasssss.DeletarResponse
import br.com.lukinhasssss.DeletarServiceGrpc
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton

@Singleton
class DeletarEndpoint(
    private val cadastroRepository: CadastroRepository
) : DeletarServiceGrpc.DeletarServiceImplBase() {

    override fun deletar(request: DeletarRequest, responseObserver: StreamObserver<DeletarResponse>) {
        cadastroRepository.deleteById(request.id)
        responseObserver.onNext(DeletarResponse.newBuilder()
            .setMessage("Excluido com sucesso!")
            .build())
        responseObserver.onCompleted()
    }

}