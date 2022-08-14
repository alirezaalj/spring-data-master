package ir.alirezaaliajni.practice.spring.data.movieboxproject.repositories;

import ir.alirezaaliajni.practice.spring.data.movieboxproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
