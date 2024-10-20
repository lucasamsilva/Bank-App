package com.code_challenge.bank.mapper;

import com.code_challenge.bank.domain.user.User;
import com.code_challenge.bank.rest.authentication.representation.LoginRequestRepresentation;
import com.code_challenge.bank.rest.authentication.representation.RegisterRequestRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(representation.password()))")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    public abstract User toDomain(RegisterRequestRepresentation representation);

    @Mapping(target = "email", source = "username")
    public abstract User toDomain(LoginRequestRepresentation loginRequestRepresentation);

}
