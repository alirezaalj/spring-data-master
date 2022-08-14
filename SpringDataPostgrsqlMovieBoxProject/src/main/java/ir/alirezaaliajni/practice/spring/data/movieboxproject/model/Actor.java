package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/13/22 - 11:12 PM
 * @project MasterSpringDataAll
 */
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    private Integer id;
    private String name;
}
