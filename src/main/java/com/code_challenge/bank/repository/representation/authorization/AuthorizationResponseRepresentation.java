package com.code_challenge.bank.repository.representation.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationResponseRepresentation {

    private String status;
    private DataRepresentation data;

}

