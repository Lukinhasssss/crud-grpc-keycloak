package br.com.lukinhasssss.factory

import br.com.lukinhasssss.ConsultarServiceGrpc
import br.com.lukinhasssss.SalvarServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class ConsultarFactory(@GrpcChannel("manager")val channel: ManagedChannel){

    @Singleton
    fun consultar() = ConsultarServiceGrpc.newBlockingStub(channel)

}