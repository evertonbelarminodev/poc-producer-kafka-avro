package br.com.poc.producer.kafka.avro.producer;

import br.com.poc.producer.kafka.avro.configuration.KafkaConfiguration;
import br.com.poc.producer.kafka.avro.utils.ParserUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ps.customer.event.avro.DomicileLockChangedEvent;

@EnableScheduling
@Component
public class DomicileLockKakfaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(DomicileLockKakfaProducer.class);

    @Value("${spring.topic.domicilelock.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, DomicileLockChangedEvent> kafkaTemplate;

    @Autowired
    private KafkaConfiguration kafkaConfiguration;

    @Scheduled(fixedRate = 100000)
    public void sendMessage(){

        // copied from avro examples
        DomicileLockChangedEvent domicileLockChangedEvent = ParserUtils.loadDocmiDomicileLockChangedEvent();

        ProducerRecord<String, DomicileLockChangedEvent> producerRecord = new ProducerRecord<String, DomicileLockChangedEvent>(
                topicName, domicileLockChangedEvent
        );

        LOG.debug("Domicile Lock object {}", domicileLockChangedEvent);

        kafkaTemplate.send(producerRecord);
    }
}
