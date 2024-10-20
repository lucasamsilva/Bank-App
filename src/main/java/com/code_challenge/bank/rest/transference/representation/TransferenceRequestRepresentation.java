package com.code_challenge.bank.rest.transference.representation;

import java.math.BigDecimal;

public record TransferenceRequestRepresentation(Long creditedUserId, BigDecimal amount) {
}
