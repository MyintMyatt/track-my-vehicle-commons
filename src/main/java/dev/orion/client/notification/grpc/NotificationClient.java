package dev.orion.client.notification.grpc;

import dev.orion.grpc.notification.NotificationCommonResponse;
import dev.orion.grpc.notification.OtpNotificationRequest;


public interface NotificationClient {

   NotificationCommonResponse sendOtp(OtpNotificationRequest request);

}
