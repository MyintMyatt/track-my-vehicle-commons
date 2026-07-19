package dev.orion.commons.client.auth;

import dev.orion.grpc.auth.client.PermissionCheckRequest;
import dev.orion.grpc.auth.client.PermissionCheckResponse;

import java.util.concurrent.CompletableFuture;

public interface AuthClient {
    CompletableFuture<PermissionCheckResponse> checkPermission(PermissionCheckRequest request);
}
