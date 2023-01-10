package ir.alirezaalijani.spring.data.audit.kafka;

import ir.alirezaalijani.spring.data.audit.kafka.model.Employee;
import ir.alirezaalijani.spring.data.audit.kafka.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataAuditKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataAuditKafkaApplication.class, args);
    }


}
