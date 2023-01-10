package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/15/22 - 4:35 PM
 * @project MasterSpringDataAll
 */
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "emp_workstation",
            joinColumns =
                    { @JoinColumn(name = "employee_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "workstation_id", referencedColumnName = "id") })
    private WorkStation workStation;

}
