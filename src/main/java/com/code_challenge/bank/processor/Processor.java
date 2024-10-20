package com.code_challenge.bank.processor;

import com.code_challenge.bank.exception.BusinessException;

import java.util.Arrays;
import java.util.function.Predicate;

public abstract class Processor<T, K> {

    private Processor next;

    public static Processor link(Processor first, Processor... chain) {
        Processor head = first;
        for (Processor nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract K process(T data);

    protected K checkNext(T data) {
        if (next == null) {
            return null;
        }
        return (K) next.process(data);
    }

    protected void checkBusinessRule(Boolean result, String message) {
        if(!result) {
            throw new BusinessException(message);
        }
    }

}
