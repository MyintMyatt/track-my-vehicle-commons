package dev.orion.commons.client.notification.grpc.impl;

import dev.orion.commons.client.notification.grpc.NotificationClient;
import dev.orion.grpc.notification.*;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CompletableFuture;

public class NotificationClientImpl  implements NotificationClient {

    private final NotificationServiceGrpc.NotificationServiceStub asyncStub;

    public NotificationClientImpl(NotificationServiceGrpc.NotificationServiceStub asyncStub){
        this.asyncStub = asyncStub;
    }

    @Override
    public NotificationCommonResponse sendOtp(OtpNotificationRequest request) {
        var  result = new CompletableFuture<NotificationCommonResponse>();
        asyncStub.sendOtpMail(request, new StreamObserver<>() {
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
        return result.join();
    }

    @Override
    public NotificationProfileRegisterResponse registerNotificationProfile(NotificationProfileRegisterRequest request) {
        var result = new CompletableFuture<NotificationProfileRegisterResponse>();
        asyncStub.notificationProfileRegister(request, new StreamObserver<NotificationProfileRegisterResponse>() {
            @Override
            public void onNext(NotificationProfileRegisterResponse value) {
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

        return result.join();
    }
}
