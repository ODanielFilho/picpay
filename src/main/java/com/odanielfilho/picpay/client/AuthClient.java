package com.odanielfilho.picpay.client;

import com.odanielfilho.picpay.client.dto.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "AuthorizationClient",
        url = "${client.authorization-service.url}"
)
public interface AuthClient {
    @GetMapping
    ResponseEntity<AuthResponse> isAuthorized();
}
