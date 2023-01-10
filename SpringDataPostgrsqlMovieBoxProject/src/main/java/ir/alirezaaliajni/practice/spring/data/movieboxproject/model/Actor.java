package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ActorInfo actorInfo;
}
