package com.code_challenge.bank.mapper;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.repository.representation.transaction.TransactionRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionRepresentation toRepresentation(Transaction transaction);

}
