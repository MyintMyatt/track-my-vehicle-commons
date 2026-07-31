package dev.orion.commons.client.notification.grpc;

import dev.orion.grpc.notification.NotificationCommonResponse;
import dev.orion.grpc.notification.NotificationProfileRegisterRequest;
import dev.orion.grpc.notification.NotificationProfileRegisterResponse;
import dev.orion.grpc.notification.OtpNotificationRequest;

import java.util.concurrent.CompletableFuture;


public interface NotificationClient {

   NotificationCommonResponse sendOtp(OtpNotificationRequest request);
   NotificationProfileRegisterResponse registerNotificationProfile(NotificationProfileRegisterRequest request);

}
