package dev.orion.client.notification.grpc.impl;

import dev.orion.client.notification.grpc.NotificationClient;
import dev.orion.grpc.notification.NotificationCommonResponse;
import dev.orion.grpc.notification.OtpGrpcServiceGrpc;
import dev.orion.grpc.notification.OtpNotificationRequest;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CompletableFuture;

public class NotificationClientImpl  implements NotificationClient {

    private final OtpGrpcServiceGrpc.OtpGrpcServiceStub asyncStub;

    public NotificationClientImpl(OtpGrpcServiceGrpc.OtpGrpcServiceStub asyncStub){
        this.asyncStub = asyncStub;
    }

    @Override
    public CompletableFuture<NotificationCommonResponse> sendOtp(OtpNotificationRequest request) {
        var  result = new CompletableFuture<NotificationCommonResponse>();
        asyncStub.sendOtp(request, new StreamObserver<>() {
            @Override
            public void onNext(NotificationCommonResponse value) {
                result.complete(value);
            }

            @Override
            public void onError(Throwable t) {
                result.completeExceptionally(t);
            }

            @Override
            public void onCompleted() {

            }
        });
        return result;
    }
}
