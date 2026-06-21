package dev.orion.client.notification.grpc.impl;

import dev.orion.client.notification.grpc.NotificationClient;
import dev.orion.grpc.notification.NotificationCommonResponse;
import dev.orion.grpc.notification.OtpGrpcServiceGrpc;
import dev.orion.grpc.notification.OtpNotificationRequest;

public class NotificationClientImpl  implements NotificationClient {

    private final OtpGrpcServiceGrpc.OtpGrpcServiceBlockingStub stub;
    private final OtpGrpcServiceGrpc.OtpGrpcServiceStub asyncStub;

    NotificationClientImpl(OtpGrpcServiceGrpc.OtpGrpcServiceBlockingStub stub, OtpGrpcServiceGrpc.OtpGrpcServiceStub asyncStub){
        this.stub = stub;
        this.asyncStub = asyncStub;
    }

    @Override
    public NotificationCommonResponse sendOtp(OtpNotificationRequest request) {
        // sending logic
        asyncStub.sendOtp(request, null);
        return null;
    }
}
