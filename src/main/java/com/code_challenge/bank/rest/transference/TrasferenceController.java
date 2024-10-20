package com.code_challenge.bank.rest.transference;

import com.code_challenge.bank.rest.transference.representation.TransferenceRequestRepresentation;
import com.code_challenge.bank.service.TransferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/transference")
public class TrasferenceController {

    @Autowired
    private TransferenceService transferenceService;

    @PostMapping
    public String doTransference(@RequestBody TransferenceRequestRepresentation transferenceRequestRepresentation) {
        return transferenceService.doTransference(transferenceRequestRepresentation.creditedUserId(), transferenceRequestRepresentation.amount());
    }

    @GetMapping("/{transactionId}")
    public String getTransaction(@PathVariable String transactionId) {
        return transferenceService.getByTransactionIdentification(UUID.fromString(transactionId)).getStatus().toString();
    }
}
