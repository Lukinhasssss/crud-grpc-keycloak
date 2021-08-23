package br.com.lukinhasssss.controllers

import br.com.lukinhasssss.SalvarRequest
import br.com.lukinhasssss.SalvarResponse
import br.com.lukinhasssss.SalvarServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import javax.annotation.security.RolesAllowed

@Controller("/v1/cadastro")
class SalvarController(
    private val grpcClient: SalvarServiceGrpc.SalvarServiceBlockingStub
) {

    @Post("/{cep}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @RolesAllowed("admin")
    fun salvar(@PathVariable cep: String): HttpResponse<CepResponse> {
        grpcClient.salvar(SalvarRequest.newBuilder()
            .setCep(cep)
            .build()).let {

            return HttpResponse.created(CepResponse(it))
        }
    }
}

class CepResponse(
    response: SalvarResponse
) {

    val cep: String = response.cep
    val logradouro: String = response.logradouro
    val bairro: String = response.bairro
    val complemento: String = response.complemento
    val localidade: String = response.localidade
    val uf: String = response.uf
    val ibge: String = response.ibge
    val ddd: String = response.ddd
}