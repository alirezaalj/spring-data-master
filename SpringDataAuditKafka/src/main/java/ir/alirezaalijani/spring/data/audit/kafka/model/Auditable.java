package ir.alirezaalijani.spring.data.audit.kafka.model;

import ir.alirezaalijani.spring.data.audit.kafka.model.audit.CustomAuditListener;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/16/22 - 10:02 PM
 * @project MasterSpringDataAll
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class, CustomAuditListener.class})
public abstract class Auditable<T,I> {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private T createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private T lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public abstract I getIdentity();
}