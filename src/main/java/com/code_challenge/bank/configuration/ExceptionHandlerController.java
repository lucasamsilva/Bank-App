package com.code_challenge.bank.configuration;

import com.code_challenge.bank.configuration.exception.ErrorResponse;
import com.code_challenge.bank.exception.AuthException;
import com.code_challenge.bank.exception.BusinessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        var errorResponse = buildErrorResponse("Campo(s) inválido(s)", ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> authExceptionHandler(AuthException ex) {
        var errorResponse = buildErrorResponse("E-mail ou senha inválido", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException ex) {
        var errorResponse = buildErrorResponse(ex.getMessage(), null);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        var errorResponse = buildErrorResponse("Ocorreu um erro inesperado!", null);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(String reason, List<String> violations) {
        return new ErrorResponse(LocalDateTime.now(), reason, violations);
    }

}
