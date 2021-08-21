package br.com.lukinhasssss.controllers

import br.com.lukinhasssss.ListarRequest
import br.com.lukinhasssss.ListarResponse
import br.com.lukinhasssss.ListarServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/v1/cadastro")
class ListarController(
    private val grpcClient: ListarServiceGrpc.ListarServiceBlockingStub
) {

    @Get
    fun listar(): HttpResponse<List<ListarCepsResponse>> {
        return grpcClient.listar(ListarRequest.newBuilder().build()).listaList.map {
            ListarCepsResponse(it)
        }.let {
            HttpResponse.ok(it)
        }
    }

}

class ListarCepsResponse(response: ListarResponse.Lista) {
    val id = response.id
    val cep = response.cep
    val logradouro = response.logradouro
    val bairro = response.bairro
    val complemento = response.complemento
    val localidade = response.localidade
    val uf = response.uf
    val ibge = response.ibge
    val ddd = response.ddd
}