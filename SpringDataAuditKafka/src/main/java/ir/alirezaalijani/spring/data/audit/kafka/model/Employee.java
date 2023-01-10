package ir.alirezaalijani.spring.data.audit.kafka.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/16/22 - 11:56 PM
 * @project MasterSpringDataAll
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@Getter
@Setter
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Auditable<String,Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String position;
    private Double salary;

    @Override
    public String toString() {
        return "Employee{" +
                "createdBy=" + getCreatedBy() +
                ", createdDate=" + getCreationDate() +
                ", lastModifiedBy=" + getLastModifiedBy() +
                ", lastModifiedDate=" + getLastModifiedDate() +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public Long getIdentity() {
        return id;
    }
}
