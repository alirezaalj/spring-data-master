package ir.alirezaalijani.spring.data.audit.kafka.config.kafka.data;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/14/22 - 12:12 PM
 * @project MasterSpringDataAll
 */
@Data
@Builder
public class KafkaAudit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String entityIdentity;
    private String entityClass;
    private String createAt;
    private String modifyAt;
    private String auditor;
    private String operation;
    private String entityJson;

    public KafkaAudit() {
    }

    public KafkaAudit(String entityIdentity, String entityClass, String createAt, String modifyAt, String auditor, String operation, String entityJson) {
        this.entityIdentity = entityIdentity;
        this.entityClass = entityClass;
        this.createAt = createAt;
        this.modifyAt = modifyAt;
        this.auditor = auditor;
        this.operation = operation;
        this.entityJson = entityJson;
    }
}
