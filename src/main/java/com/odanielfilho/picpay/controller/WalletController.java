package com.odanielfilho.picpay.controller;

import com.odanielfilho.picpay.controller.dto.CreateWalletDTO;
import com.odanielfilho.picpay.entity.Wallet;
import com.odanielfilho.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {
    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDTO dto) {

        var wallet = service.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> getAllWallets() {
        var wallets = service.getAllWallet();

        return ResponseEntity.ok(wallets);
    }
    @GetMapping("/wallets/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        var wallet = service.findById(id);

        return ResponseEntity.ok(wallet);
    }

    @DeleteMapping("/wallets/{id}")
    public ResponseEntity<Void> deleteWalletById(@PathVariable Long id) {
        service.deleteWalletById(id);
        return ResponseEntity.noContent().build();
    }
}
