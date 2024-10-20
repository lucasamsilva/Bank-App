package com.code_challenge.bank.rest.authentication.representation;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestRepresentation {

    @Email
    private String username;
    private String password;

}
