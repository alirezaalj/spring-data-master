package ir.alirezaalijani.spring.data.audit.kafka.model.audit;

import ir.alirezaalijani.spring.data.audit.kafka.model.Auditable;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/17/22 - 1:43 PM
 * @project MasterSpringDataAll
 */
@Slf4j
public class CustomAuditListener {

    @PostPersist
//    @PrePersist
    private void beforePersist(Auditable<String,Long> entity) {
        kafkaAudit(entity, KafkaAuditOp.PERSIST);
    }

    private void kafkaAudit(Auditable<String, Long> entity, KafkaAuditOp kafkaAuditOp) {
        entity.getIdentity();
        log.info("entity: {}, op: {}",entity,kafkaAuditOp);
    }

    @PreUpdate
    private void beforeUpdate(Auditable<String,Long> entity) {
        kafkaAudit(entity, KafkaAuditOp.UPDATE);
    }

    @PreRemove
    private void beforeRemove(Auditable<String,Long> entity) {
        kafkaAudit(entity,KafkaAuditOp.REMOVE);
    }
}
