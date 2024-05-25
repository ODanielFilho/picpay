package com.odanielfilho.picpay.service;

import com.odanielfilho.picpay.client.AuthClient;
import com.odanielfilho.picpay.controller.dto.TransferDTO;
import com.odanielfilho.picpay.exceptions.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final AuthClient client;

    public AuthorizationService(AuthClient client) {
        this.client = client;
    }

    public boolean isAuthorized(TransferDTO transfer) {

        var resp = client.isAuthorized();

        if (resp.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }
}
