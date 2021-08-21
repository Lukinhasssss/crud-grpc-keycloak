package br.com.lukinhasssss.crud

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Cadastro (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val cep: String,

    val logradouro: String,

    val complemento: String,

    val bairro: String,

    val localidade: String,

    val uf: String,

    val ibge: String,

    val ddd: String
)