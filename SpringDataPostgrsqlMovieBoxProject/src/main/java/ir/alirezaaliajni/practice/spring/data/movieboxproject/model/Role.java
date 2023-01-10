package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Persister;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "role")
public class Role extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50,nullable = false,unique = true)
    private String name;

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate() {
        if (id == 0) {
            log.info("[ROLE AUDIT] About to add a role");
        } else {
            log.info("[ROLE AUDIT] About to update/delete role: " + id);
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate() {
        log.info("[ROLE AUDIT] add/update/delete complete for role: " +id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastModifiedDate=" + lastModifiedDate +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
