package dev.orion.commons.client.notification.grpc;

import dev.orion.grpc.notification.NotificationCommonResponse;
import dev.orion.grpc.notification.OtpNotificationRequest;

import java.util.concurrent.CompletableFuture;


public interface NotificationClient {

   CompletableFuture<NotificationCommonResponse> sendOtp(OtpNotificationRequest request);

}
