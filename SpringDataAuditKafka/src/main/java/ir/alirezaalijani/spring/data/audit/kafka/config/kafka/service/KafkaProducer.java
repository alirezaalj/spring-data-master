package ir.alirezaalijani.spring.data.audit.kafka.config.kafka.service;

import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V extends Serializable> {
    void send(String topicName, K key, V message);
}
