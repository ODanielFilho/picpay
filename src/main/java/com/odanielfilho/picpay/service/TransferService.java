package com.odanielfilho.picpay.service;

import com.odanielfilho.picpay.controller.dto.TransferDTO;
import com.odanielfilho.picpay.entity.Transfer;
import com.odanielfilho.picpay.entity.Wallet;
import com.odanielfilho.picpay.exceptions.InsufficientBalanceException;
import com.odanielfilho.picpay.exceptions.TransferNotAllowedForWalletTypeException;
import com.odanielfilho.picpay.exceptions.TransferNotAuthorizedException;
import com.odanielfilho.picpay.exceptions.WalletNotFoundException;
import com.odanielfilho.picpay.repository.TransferRepository;
import com.odanielfilho.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(AuthorizationService authorizationService,
                           NotificationService notificationService,
                           TransferRepository transferRepository,
                           WalletRepository walletRepository) {
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDTO dto) {
        var sender = walletRepository.findById(dto.payer())
                .orElseThrow(() -> new WalletNotFoundException(dto.payer()));
        var receiver = walletRepository.findById(dto.payee())
                .orElseThrow(() -> new WalletNotFoundException(dto.payee()));

        validateTransfer(dto, sender);
        sender.debit(dto.value());
        receiver.credit(dto.value());
        var transferResult = new Transfer(sender, receiver, dto.value());
        transferRepository.save(transferResult);
        walletRepository.save(sender);
        walletRepository.save(receiver);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDTO dto, Wallet sender) {
        if(!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }
        if(!sender.isBalanceEqualOrGreaterThan(dto.value())){
            throw new InsufficientBalanceException();
        }
        if(authorizationService.isAuthorized(dto)){
            throw new TransferNotAuthorizedException();
        }
    }
}
