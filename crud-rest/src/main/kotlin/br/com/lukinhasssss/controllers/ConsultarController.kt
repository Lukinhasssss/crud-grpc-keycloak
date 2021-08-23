package br.com.lukinhasssss.controllers

import br.com.lukinhasssss.ConsultarRequest
import br.com.lukinhasssss.ConsultarResponse
import br.com.lukinhasssss.ConsultarServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import javax.annotation.security.PermitAll

@Controller("/v1/cadastro")
class ConsultarController(
    private val grpcClient: ConsultarServiceGrpc.ConsultarServiceBlockingStub
) {

    @Get("/{id}")
    @PermitAll
    fun consultar(@PathVariable id: Long): HttpResponse<ConsultarCepResponse> {
        return grpcClient.consultar(ConsultarRequest.newBuilder()
            .setId(id)
            .build()).let {
            HttpResponse.ok(ConsultarCepResponse(it))
        }
    }

}

class ConsultarCepResponse(response: ConsultarResponse) {
    val cep: String = response.cep
    val logradouro: String = response.logradouro
    val bairro: String = response.bairro
    val complemento: String = response.complemento
    val localidade: String = response.localidade
    val uf: String = response.uf
    val ibge: String = response.ibge
    val ddd: String = response.ddd
}