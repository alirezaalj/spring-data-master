package ir.alirezaalijani.spring.data.audit.kafka.initializer;

import ir.alirezaalijani.spring.data.audit.kafka.config.kafka.KafkaAdminClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user: alireza
 * @date: 8/5/22 - 2:34 AM
 * @project: exam
 */

@Slf4j
@RequiredArgsConstructor
@Component
@Profile(value = {"dev"})
public class AppStartupInitializerDevImpl implements AppStartupInitializer{

    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public void init() {
        log.debug("Dev Initializer");
        kafkaAdminClient.createTopics();
    }
}
