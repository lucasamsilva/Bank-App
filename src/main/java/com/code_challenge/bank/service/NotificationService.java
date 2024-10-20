package com.code_challenge.bank.service;

import com.code_challenge.bank.repository.ToolsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    @Autowired
    private ToolsRepository toolsRepository;

    public void notifyCreditor(String email) {
        log.info("Notificando recebimento do usuário com e-mail: {}", email);
        try {
            toolsRepository.notifyCreditor();
            log.info("Notificação enviada com sucesso para o usuário com e-mail: {}", email);
        } catch (Exception e) {
            log.error("Erro ao enviar notificação para o usuário com e-mail: {}", email);
            throw e;
        }
    }

}
