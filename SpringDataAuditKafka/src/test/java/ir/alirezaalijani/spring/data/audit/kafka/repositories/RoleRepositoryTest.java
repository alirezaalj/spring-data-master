package ir.alirezaalijani.spring.data.audit.kafka.repositories;

import ir.alirezaalijani.spring.data.audit.kafka.model.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles({"test"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoleRepositoryTest extends PostgresBaseContainer{

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void roleCompareTest(){
        var role1=new Role(0,"role");
        var role2=new Role(1,"role");
        assertThat(role1.equals(role2)).isTrue();
    }
    @Test
    @Order(1)
    @Rollback(false)
    void insertRoleTest(){
        var role= new Role(0,"ROLE_USER");
        role = roleRepository.save(role);
        assertThat(role.getId()).isGreaterThan(0);
    }

    @Test
    @Order(1)
    void findByNameTest() {
        roleRepository.save(new Role(0,"ROLE_USER"));
        var role=roleRepository.findByName("ROLE_USER").get();
        assertThat(role).isNotNull();
        assertThat(role.getName()).isEqualTo("ROLE_USER");
    }

    @Test
    @Order(2)
    @Rollback(false)
    void updateRoleTest(){
        var role= roleRepository.findAll().get(0);
        assertThat(role).isNotNull();
        role.setName("ROLE_USERG");
        role = roleRepository.save(role);
        assertThat(role.getId()).isGreaterThan(0);
        assertThat(role.getName()).isEqualTo("ROLE_USERG");
    }

    @Test
    @Order(3)
    @Rollback(false)
    void findRoleTest(){
        var roles= roleRepository.findAll();
        assertThat(roles).hasSize(1);
        assertThat(roles.get(0).getName()).isEqualTo("ROLE_USERG");
    }

    @Test
    @Order(4)
    @Rollback(false)
    void deleteRoleTest(){
        var role=roleRepository.findAll().get(0);
        roleRepository.delete(role);
        var nullRole=roleRepository.findAll();
        assertThat(nullRole).hasSize(0);
    }

    @AfterAll
    static void afterAll(){
        postgreSQLContainer.close();
        postgreSQLContainer.stop();
    }


}