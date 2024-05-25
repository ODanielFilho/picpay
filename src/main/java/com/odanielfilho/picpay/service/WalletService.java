package com.odanielfilho.picpay.service;

import com.odanielfilho.picpay.controller.dto.CreateWalletDTO;
import com.odanielfilho.picpay.entity.Wallet;
import com.odanielfilho.picpay.exceptions.WalletDataAlreadyExistsException;
import com.odanielfilho.picpay.exceptions.WalletNotFoundException;
import com.odanielfilho.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    private final WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    public Wallet createWallet(CreateWalletDTO dto) {
        var walletDB = repository.findByDocumentOrEmail(dto.document(), dto.email());
        if (walletDB.isPresent()) {
            throw new WalletDataAlreadyExistsException("Document or email already exists");
        }
        return repository.save(dto.toWallet());
    }

    public List<Wallet> getAllWallet(){
        return repository.findAll();
    }

    public Wallet findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(id));
    }

    public void deleteWalletById(Long id) {
        var wallet = repository.findById(id);
        if (!wallet.isPresent()) {
            throw new WalletNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
