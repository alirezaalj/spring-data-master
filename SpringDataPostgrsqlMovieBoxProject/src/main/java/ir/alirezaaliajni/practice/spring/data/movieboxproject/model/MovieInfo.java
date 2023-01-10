package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/15/22 - 4:21 PM
 * @project MasterSpringDataAll
 */
@Entity
@Table(name = "movie_info")
public class MovieInfo {
    @Id
    private Long id;
    private String info;
    @OneToOne(mappedBy = "info")
    private Movie movie;
}
