package br.com.poc.producer.kafka.avro.configuration;

import br.com.poc.producer.kafka.avro.utils.Constants;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    private final KafkaProperties properties;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.schema.registry.url}")
    private String schemaRegistryUrl;

    public KafkaConfiguration (final KafkaProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ProducerFactory<?,?> kafkaProducerFactory(KafkaProperties properties){

        Map<String, Object> props = properties.buildConsumerProperties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        props.put(Constants.ACKS, Constants.ACKS_ALL);
        props.put(Constants.RETRIES, Constants.RETRIES_NUMBER);
        // avro part
        props.put(Constants.KEY_SERIALIZER, StringSerializer.class.getName());
        props.put(Constants.KAFKA_AVRO_SERIALIZER, KafkaAvroSerializer.class.getName());
        props.put(Constants.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

        return new DefaultKafkaProducerFactory<>(props);

    }
}
