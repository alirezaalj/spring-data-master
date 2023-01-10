package ir.alirezaalijani.spring.data.audit.kafka.config.kafka.service;

import ir.alirezaalijani.spring.data.audit.kafka.config.kafka.data.KafkaAudit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/14/22 - 12:11 PM
 * @project MasterSpringDataAll
 */
@Profile("dev")
@Slf4j
@RequiredArgsConstructor
@Component
public class EntityAuditProducer implements KafkaProducer<String, KafkaAudit>{

    private final KafkaTemplate<String, KafkaAudit> kafkaTemplate;

    public void send(String topicName, String key, KafkaAudit message) {
        ListenableFuture<SendResult<String, KafkaAudit>> kafkaResultFuture =kafkaTemplate.send(topicName,key, message);
        addCallback(topicName, message, kafkaResultFuture);
    }

    private void addCallback(String topicName, KafkaAudit message,ListenableFuture<SendResult<String, KafkaAudit>> kafkaResultFuture) {
        kafkaResultFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }
            @Override
            public void onSuccess(SendResult<String, KafkaAudit> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.debug("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            }
        });
    }

}
