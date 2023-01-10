package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/11/22 - 12:32 AM
 * @project SpringDataPostgrsqlMovieBoxProject
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name="UserSimpleResultMap",
        classes={
                @ConstructorResult(
                        targetClass=UserSimple.class,
                        columns={
                                @ColumnResult(name="id", type=Integer.class),
                                @ColumnResult(name="username", type=String.class),
                                @ColumnResult(name="email",type = String.class)
                        })
        })
@NamedNativeQuery(name = "findSimpleUserBuyNative", resultClass = UserSimple.class, resultSetMapping ="UserSimpleResultMap",
        query = "SELECT id,email,username from users where username= :u")
@NamedNativeQuery(name = "findAllSimpleUserBuyNative", resultClass = UserSimple.class, resultSetMapping ="UserSimpleResultMap",
        query = "SELECT id,email,username from users")
@NamedNativeQuery(name = "updateSimpleNative",query = "update users set email_verification= :bool where id = :id")
@Entity
public class UserSimple {
    @Id
    private Integer id;
    private String username;
    private String email;

    @PrePersist
    public void logNewUserAttempt() {
        log.info("Attempting to add new user with username: " + username);
    }

    @PostPersist
    public void logNewUserAdded() {
        log.info("Added user '{}' with ID: {}" ,username, id);
    }

    @PreRemove
    public void logUserRemovalAttempt() {
        log.info("Attempting to delete user: {}" , username);
    }

    @PostRemove
    public void logUserRemoval() {
        log.info("Deleted user: " + username);
    }

    @PreUpdate
    public void logUserUpdateAttempt() {
        log.info("Attempting to update user: {}" , username);
    }

    @PostUpdate
    public void logUserUpdate() {
        log.info("Updated user: {}" , username);
    }

    @PostLoad
    public void logUserLoad() {
//        fullName = firstName + " " + lastName;
        log.info("After load userSimple: {}",username);
    }
}
