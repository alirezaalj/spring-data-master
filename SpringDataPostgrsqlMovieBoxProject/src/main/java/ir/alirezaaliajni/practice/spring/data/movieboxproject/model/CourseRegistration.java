package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/15/22 - 5:29 PM
 * @project MasterSpringDataAll
 */
@Entity
public class CourseRegistration {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    LocalDateTime registeredAt;

    int grade;

    // additional properties
    // standard constructors, getters, and setters
}
