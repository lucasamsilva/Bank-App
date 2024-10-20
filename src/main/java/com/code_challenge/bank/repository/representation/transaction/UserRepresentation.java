package com.code_challenge.bank.repository.representation.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRepresentation {

    private Long id;
    private String email;
    private String document;
    private String name;

}
