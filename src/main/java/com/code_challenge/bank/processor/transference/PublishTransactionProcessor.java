package com.code_challenge.bank.processor.transference;

import com.code_challenge.bank.processor.Processor;
import com.code_challenge.bank.processor.transference.data.TransferenceData;
import com.code_challenge.bank.repository.PublisherInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublishTransactionProcessor extends Processor<TransferenceData, String> {

    @Autowired
    private PublisherInterface publisherInterface;

    @Override
    public String process(TransferenceData data) {
        publisherInterface.publishTransaction(data.getTransaction());

        return data.getTransaction().getTransactionIdentification().toString();
    }
}
