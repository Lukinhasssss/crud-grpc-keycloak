package br.com.lukinhasssss.factory

import br.com.lukinhasssss.SalvarServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class SalvarFactory(@GrpcChannel("manager")val channel: ManagedChannel){

    @Singleton
    fun salvar() = SalvarServiceGrpc.newBlockingStub(channel)

}