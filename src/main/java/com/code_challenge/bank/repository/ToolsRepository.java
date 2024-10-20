package com.code_challenge.bank.repository;

import com.code_challenge.bank.repository.representation.authorization.AuthorizationResponseRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "toolsClient", url = "https://util.devi.tools/api")
public interface ToolsRepository {

    @GetMapping("/v2/authorize")
    AuthorizationResponseRepresentation getAuthorization();

    @PostMapping("/v1/notify")
    void notifyCreditor();

}
