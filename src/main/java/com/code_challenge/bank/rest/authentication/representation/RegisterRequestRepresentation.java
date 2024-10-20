package com.code_challenge.bank.rest.authentication.representation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record RegisterRequestRepresentation(
        @Email
        String email,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Senha deve ser mais forte")
        String password,
        String name,
        @Pattern(regexp = "^\\d{11}$|^\\d{14}$\n", message = "CPF ou CNPJ inválido")
        String document,
        String accountType) {}
