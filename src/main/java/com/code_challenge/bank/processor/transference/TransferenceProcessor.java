package com.code_challenge.bank.processor.transference;

import com.code_challenge.bank.processor.Processor;
import com.code_challenge.bank.processor.transference.data.TransferenceData;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static com.code_challenge.bank.processor.Processor.link;

@Component
public class TransferenceProcessor {

    @Getter
    private Processor<TransferenceData, String> processor;

    public TransferenceProcessor(SaveTransactionProcessor saveTransactionProcessor, ValidateCreditorProcessor validateCreditorProcessor, ValidateDebtorProcessor validateDebtorProcessor, PublishTransactionProcessor publishTransactionProcessor) {
        processor = link(validateDebtorProcessor, validateCreditorProcessor, saveTransactionProcessor, publishTransactionProcessor);
    }

}
