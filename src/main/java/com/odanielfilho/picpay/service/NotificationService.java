package com.odanielfilho.picpay.service;

import com.odanielfilho.picpay.client.NotificationClient;
import com.odanielfilho.picpay.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient client;

    public NotificationService(NotificationClient client) {
        this.client = client;
    }

    public void sendNotification(Transfer transfer) {
        try {
            logger.info("Enviando notificação para {}", transfer.getReceiver().getEmail());
            var response = client.sendNotification(transfer);
            if (response.getStatusCode().isError()) {
                logger.error("Erro ao enviar notificação", response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Erro ao enviar notificação", e);
        }
    }
}
