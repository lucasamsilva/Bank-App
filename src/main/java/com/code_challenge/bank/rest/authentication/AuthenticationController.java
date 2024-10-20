package com.code_challenge.bank.rest.authentication;

import com.code_challenge.bank.mapper.UserMapper;
import com.code_challenge.bank.rest.authentication.representation.LoginRequestRepresentation;
import com.code_challenge.bank.rest.authentication.representation.LoginResponseRepresentation;
import com.code_challenge.bank.rest.authentication.representation.RegisterRequestRepresentation;
import com.code_challenge.bank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public LoginResponseRepresentation login(@Valid @RequestBody LoginRequestRepresentation loginRequestRepresentation) {
        var token = userService.login(userMapper.toDomain(loginRequestRepresentation));
        return new LoginResponseRepresentation(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterRequestRepresentation registerRequestRepresentation) {
        userService.registerUser(userMapper.toDomain(registerRequestRepresentation));
        return ResponseEntity.ok().build();
    }

}
