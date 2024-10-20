package com.code_challenge.bank.processor.transference;

import com.code_challenge.bank.processor.Processor;
import com.code_challenge.bank.processor.transference.data.TransferenceData;
import org.springframework.stereotype.Component;

@Component
public class ValidateCreditorProcessor extends Processor<TransferenceData, String> {

    @Override
    public String process(TransferenceData data) {
        checkBusinessRule(data.getCreditedUser().isEnabled(), "Este usuário não pode receber transferências.");

        return checkNext(data);
    }
}
