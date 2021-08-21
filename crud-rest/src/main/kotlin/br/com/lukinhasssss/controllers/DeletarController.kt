package br.com.lukinhasssss.controllers

import br.com.lukinhasssss.DeletarRequest
import br.com.lukinhasssss.DeletarResponse
import br.com.lukinhasssss.DeletarServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/v1/cadastro")
class DeletarController(
    private val grpcClient: DeletarServiceGrpc.DeletarServiceBlockingStub
) {

    @Delete("/{id}")
    fun deletar(@PathVariable id: Long): HttpResponse<DeletarCepResponse> {
        return grpcClient.deletar(DeletarRequest.newBuilder()
            .setId(id)
            .build()).let {
            HttpResponse.ok(DeletarCepResponse(it))
        }
    }

}

class DeletarCepResponse(it: DeletarResponse) {
    val mensagem = it.message
}