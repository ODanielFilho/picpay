package com.odanielfilho.picpay.controller.dto;

import com.odanielfilho.picpay.entity.Wallet;
import com.odanielfilho.picpay.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDTO(@NotBlank String fullName,
                              @NotBlank String document,
                              @NotBlank String email,
                              @NotBlank String password,
                              @NotNull WalletType.Type walletType) {


    public Wallet toWallet(){
       return new Wallet(fullName, document, email, password, walletType.get());
    }
}
