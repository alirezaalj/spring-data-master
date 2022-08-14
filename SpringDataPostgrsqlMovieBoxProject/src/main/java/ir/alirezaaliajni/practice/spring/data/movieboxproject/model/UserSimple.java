package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/11/22 - 12:32 AM
 * @project SpringDataPostgrsqlMovieBoxProject
 */
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
@Entity
public class UserSimple {
    @Id
    private Integer id;
    private String username;
    private String email;
}
