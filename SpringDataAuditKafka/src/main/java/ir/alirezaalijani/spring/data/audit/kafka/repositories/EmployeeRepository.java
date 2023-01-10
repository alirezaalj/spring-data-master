package ir.alirezaalijani.spring.data.audit.kafka.repositories;

import ir.alirezaalijani.spring.data.audit.kafka.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/17/22 - 12:02 AM
 * @project MasterSpringDataAll
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
