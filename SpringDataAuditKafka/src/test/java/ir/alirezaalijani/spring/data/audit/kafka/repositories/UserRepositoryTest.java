package ir.alirezaalijani.spring.data.audit.kafka.repositories;

import ir.alirezaalijani.spring.data.audit.kafka.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles({"test"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest extends PostgresBaseContainer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Order(1)
    void insertNewUserTest(){
        final var user = getTestUser("user1");
        user.setUsername("this-username-is-longer-than-50-characters-@size-error");

        assertThatThrownBy(()->{
            userRepository.save(user);
        }).isInstanceOf(Exception.class);

        user.setUsername("username-user1");
        var newUser = userRepository.save(user);
        assertThat(newUser.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Sql("classpath:data/test/insertRole.sql")
    void insertUserWithRoles(){
        var user=getTestUser("user1");
        var roleUser = roleRepository.findByName("ROLE_USER").get();
        assertThat(roleUser.getName()).isEqualTo("ROLE_USER");
        user.addRole(roleUser);
        user.addRole(roleUser);
        assertThat(user.getRoles()).hasSize(1);
        user=userRepository.save(user);
        assertThat(user.getId()).isGreaterThan(0);
        // find saved user
        var existUser=userRepository.findById(user.getId());
        assertThat(existUser.isPresent()).isTrue();
        assertThat(existUser.get().getRoles()).hasSize(1);
        // add new role
        var roleAdmin = roleRepository.findByName("ROLE_ADMIN").get();
        assertThat(roleAdmin.getName()).isEqualTo("ROLE_ADMIN");
        var user1 = existUser.get();
        user1.addRole(roleAdmin);
        var updatedUser=userRepository.save(user1);
        assertThat(updatedUser.getId()).isEqualTo(user1.getId());
        // find updated user
        var userWithTowRole=userRepository.findById(updatedUser.getId());
        assertThat(userWithTowRole.isPresent()).isTrue();
        assertThat(userWithTowRole.get().getRoles()).hasSize(2);
    }

    @Test
    @Order(3)
    void findByEmailAndUsernameTest() {
        var user=getTestUser("user1");
        user=userRepository.save(user);
        assertThat(user.getId()).isGreaterThan(0);
        var findByEmail=userRepository.findByEmail(user.getEmail()).get();
        assertThat(findByEmail.getEmail()).isEqualTo(user.getEmail());
        var findByUsername=userRepository.findByUsername(user.getUsername()).get();
        assertThat(findByUsername.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    @Order(4)
    void existsByEmailAndUsernameTest() {
        var user=getTestUser("user1");
        user=userRepository.save(user);
        assertThat(user.getId()).isGreaterThan(0);

        assertThat(userRepository.existsByEmail(user.getEmail())).isTrue();
        assertThat(userRepository.existsByEmail("some@email.com")).isFalse();

        assertThat(userRepository.existsByUsername(user.getUsername())).isTrue();
        assertThat(userRepository.existsByUsername("some-user")).isFalse();
    }

    @AfterAll
    static void afterAll(){
        postgreSQLContainer.close();
        postgreSQLContainer.stop();
    }

    static User getTestUser(String name){
       return User.builder()
                .id(0)
                .email(name+"@gmail.com")
                .password("123456")
                .username("username-"+name)
               .build();
    }

}