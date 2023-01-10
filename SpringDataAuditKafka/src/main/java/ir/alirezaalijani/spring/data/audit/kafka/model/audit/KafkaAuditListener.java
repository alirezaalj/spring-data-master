package ir.alirezaalijani.spring.data.audit.kafka.model.audit;

import ir.alirezaalijani.spring.data.audit.kafka.config.kafka.service.EntityAuditProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/13/22 - 10:45 PM
 * @project MasterSpringDataAll
 */
@Profile("dev")
@Slf4j
@Component
public class KafkaAuditListener {

    @Autowired
    private EntityAuditProducer entityAuditProducer;

    @PostPersist
//    @PrePersist
    private void beforePersist(Object entity) {
        kafkaAudit(entity, KafkaAuditOp.PERSIST);
    }

    @PreUpdate
    private void beforeUpdate(Object entity) {
        kafkaAudit(entity, KafkaAuditOp.UPDATE);
    }

    @PreRemove
    private void beforeRemove(Object entity) {
        kafkaAudit(entity,KafkaAuditOp.REMOVE);
    }

    private void kafkaAudit(Object entity, KafkaAuditOp op) {
//        if (entity instanceof BaseEntity baseEntity){
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            log.info("Auditing Op[{}] ,auditor:",op.name());
//            var kafkaAuditBuilder= KafkaAudit.builder();
//            kafkaAuditBuilder
//                    .entityIdentity(baseEntity.getIdentity())
//                    .entityClass(entity.getClass().getName())
//                    .modifyAt(new Date().toString())
//                    .operation(op.name());
//            if (authentication!=null) {
//                kafkaAuditBuilder.auditor(authentication.toString());
//            }else {
//                kafkaAuditBuilder.auditor("non");
//            }
//            if (op.equals(KafkaAuditOp.PERSIST)){
//                kafkaAuditBuilder.createAt(new Date().toString());
//            }
//            var kafkaAudit=kafkaAuditBuilder.build();
//            String key=entity.getClass().getName().concat("-").concat(baseEntity.getIdentity());
//            entityAuditProducer.send("entity-audit-topic",key,kafkaAudit);
//        }

    }
}
