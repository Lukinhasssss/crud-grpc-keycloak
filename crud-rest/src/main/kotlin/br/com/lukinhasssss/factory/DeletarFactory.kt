package br.com.lukinhasssss.factory

import br.com.lukinhasssss.DeletarServiceGrpc
import br.com.lukinhasssss.SalvarServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class DeletarFactory(@GrpcChannel("manager")val channel: ManagedChannel){

    @Singleton
    fun deletar() = DeletarServiceGrpc.newBlockingStub(channel)

}