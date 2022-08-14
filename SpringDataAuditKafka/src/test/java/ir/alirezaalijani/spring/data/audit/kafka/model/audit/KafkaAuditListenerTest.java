package ir.alirezaalijani.spring.data.audit.kafka.model.audit;

import ir.alirezaalijani.spring.data.audit.kafka.model.User;
import ir.alirezaalijani.spring.data.audit.kafka.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/14/22 - 2:04 PM
 * @project MasterSpringDataAll
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@SpringBootTest
@ActiveProfiles({"test","dev"})
class KafkaAuditListenerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserTest(){
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
        var users=userRepository.findAll();
        users.forEach(System.out::println);

        System.out.println("----- end -----");
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