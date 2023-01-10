package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import javax.persistence.*;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/15/22 - 4:26 PM
 * @project MasterSpringDataAll
 */
@Entity
@Table(name = "actor_info")
public class ActorInfo {
    @Id
    @Column(name = "actor_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "actor_id")
    private Actor actor;
}
