package br.com.lukinhasssss.crud

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws/")
interface ViaCepClient {

    @Get("{cep}/json")
    fun consultaCep(@PathVariable cep: String?): HttpResponse<ViaCepResponse>?

}

data class ViaCepResponse (
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: String,
    val ddd: String
) {
    fun toModel(): Cadastro {
        return Cadastro(
            cep = cep,
            logradouro = logradouro,
            complemento = complemento,
            bairro = bairro,
            localidade = localidade,
            uf = uf,
            ibge = ibge,
            ddd = ddd
        )
    }
}