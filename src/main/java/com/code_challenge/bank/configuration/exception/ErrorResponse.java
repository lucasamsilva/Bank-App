package com.code_challenge.bank.configuration.exception;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(LocalDateTime datetime, String reason, List<String> violations) {
}
