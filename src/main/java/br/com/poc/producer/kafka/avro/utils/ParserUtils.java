package br.com.poc.producer.kafka.avro.utils;

import br.com.poc.producer.kafka.avro.model.DomicileLock;
import org.springframework.stereotype.Component;
import ps.customer.event.avro.DomicileLockChangedEvent;

@Component
public class ParserUtils {

    public DomicileLock convertDomicileLockEventInDomicileLock(DomicileLockChangedEvent domicileLockChangedEvent){
        DomicileLock domicileLock = new DomicileLock();
        domicileLock.setId(domicileLockChangedEvent.getCustomerId());
        domicileLock.setAccountType(domicileLockChangedEvent.getAccountType());
        domicileLock.setChangeDate(domicileLockChangedEvent.getChangeDate());
        domicileLock.setChangeType(domicileLockChangedEvent.getChangeType());
        domicileLock.setCodAccount(domicileLockChangedEvent.getCodAccount());
        domicileLock.setCodAgency(domicileLockChangedEvent.getCodAgency());
        domicileLock.setCreditCardBrand(domicileLockChangedEvent.getCreditCardBrand());
        domicileLock.setPaymentAccount(domicileLockChangedEvent.getPaymentAccount());
        domicileLock.setTransactionType(domicileLockChangedEvent.getTransactionType());
        return domicileLock;
    }

    public static DomicileLockChangedEvent loadDocmiDomicileLockChangedEvent(){

        DomicileLockChangedEvent domicileLockChangedEvent =
                DomicileLockChangedEvent.newBuilder()
                .setCustomerId("123")
                .setCodAccount(1)
                .setCodAgency(2)
                .setAccountType("Account")
                .setChangeType(1)
                .setCreditCardBrand("VISA")
                .setPaymentAccount("123asd123")
                .setTransactionType("TED")
                .setChangeDate(123456)
                .build();

        return domicileLockChangedEvent;
    }


}
