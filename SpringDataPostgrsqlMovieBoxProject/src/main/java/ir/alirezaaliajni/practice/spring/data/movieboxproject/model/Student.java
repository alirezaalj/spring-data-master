package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/15/22 - 5:29 PM
 * @project MasterSpringDataAll
 */
@Entity
public class Student {
    @Id
    private Long id;
    @OneToMany(mappedBy = "student")
    Set<CourseRegistration> registrations;

}