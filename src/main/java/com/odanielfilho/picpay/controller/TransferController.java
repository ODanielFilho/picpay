package com.odanielfilho.picpay.controller;

import com.odanielfilho.picpay.controller.dto.TransferDTO;
import com.odanielfilho.picpay.entity.Transfer;
import com.odanielfilho.picpay.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody TransferDTO dto){
        var transfer = transferService.transfer(dto);

        return ResponseEntity.ok(transfer);
    }
}
