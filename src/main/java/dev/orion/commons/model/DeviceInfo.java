package dev.orion.commons.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo {
    @NotBlank(message = "device id required")
    private String deviceId;
    @NotBlank(message = "fcm token required")
    private String fcmToken;
    @NotBlank(message = "device OS required")
    private String deviceOs;
    @NotBlank(message = "Os version required")
    private String osVersion;
    @NotBlank(message = "App version required")
    private String appVersion;
}
