package ir.alirezaalijani.spring.data.audit.kafka.repositories;

import ir.alirezaalijani.spring.data.audit.kafka.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
