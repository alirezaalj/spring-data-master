package ir.alirezaaliajni.practice.spring.data.movieboxproject.repositories;

import ir.alirezaaliajni.practice.spring.data.movieboxproject.model.Role;
import ir.alirezaaliajni.practice.spring.data.movieboxproject.model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/11/22 - 12:44 AM
 * @project SpringDataPostgrsqlMovieBoxProject
 */
@DataJpaTest
@ActiveProfiles({"test"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class H2SimpleTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void saveUserTest() {
        var user = getTestUser("user1");
        user = userRepository.save(user);
        assertThat(user.getId()).isGreaterThan(0);

        user = getTestUser("user2");
        user = userRepository.save(user);
        assertThat(user.getId()).isGreaterThan(0);

        user = getTestUser("user3");
        user = userRepository.save(user);
        assertThat(user.getId()).isGreaterThan(0);

        var opUser = userRepository.findByUsername(user.getUsername());
        assertThat(opUser).isPresent();
        assertThat(opUser.get().getId()).isEqualTo(user.getId());

        System.out.println("----- all users -----");
        var users = userRepository.findAll();
        users.forEach(System.out::println);

        System.out.println("----- native single -----");
        var simpleUser = userRepository.findByUsernameNative(user.getUsername());
        System.out.println(simpleUser);

        System.out.println("----- native list -----");
        var simpleUsers = userRepository.findAllByUsernameNative();
        simpleUsers.forEach(System.out::println);

        System.out.println("---- native update ----");
        userRepository.updateSimpleNative(false, simpleUser.getId());

        System.out.println("----- end -----");
    }

    static User getTestUser(String name) {
        return User.builder()
                .id(0)
                .email(name + "@gmail.com").accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enable(true)
                .password("123456").emailVerification(true)
                .username("username-" + name)
                .build();
    }
}
