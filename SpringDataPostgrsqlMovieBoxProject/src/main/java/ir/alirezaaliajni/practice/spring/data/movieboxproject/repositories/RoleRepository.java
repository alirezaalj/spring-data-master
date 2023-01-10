package ir.alirezaaliajni.practice.spring.data.movieboxproject.repositories;

import ir.alirezaaliajni.practice.spring.data.movieboxproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);

    @Transactional
    @Modifying
    @Query("update Role r set r.name = :name where r.id= :id")
    void updateWithQuery(@Param("name") String name,@Param("id") int id);

}
