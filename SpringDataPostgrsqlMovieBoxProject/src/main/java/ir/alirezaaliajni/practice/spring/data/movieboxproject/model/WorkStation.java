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
@Table(name = "workstation")
public class WorkStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "workStation")
    private Employee employee;

    //... getters and setters
}