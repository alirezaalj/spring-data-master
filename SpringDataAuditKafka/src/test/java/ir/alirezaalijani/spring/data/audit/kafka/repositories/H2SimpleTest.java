package ir.alirezaalijani.spring.data.audit.kafka.repositories;

import ir.alirezaalijani.spring.data.audit.kafka.model.Employee;
import ir.alirezaalijani.spring.data.audit.kafka.model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/11/22 - 12:44 AM
 * @project SpringDataPostgrsqlMovieBoxProject
 */
//@DataJpaTest
@SpringBootTest
@ActiveProfiles({"test"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class H2SimpleTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void employeeTest(){
        var emp= Employee.builder()
                .name("Jon")
                .position("Developer")
                .salary(120000.0)
                .build();
        var empResult =employeeRepository.save(emp);
        System.out.println("employee:"+empResult);
    }

}
