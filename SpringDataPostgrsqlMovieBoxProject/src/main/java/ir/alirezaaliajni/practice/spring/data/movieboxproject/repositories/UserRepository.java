package ir.alirezaaliajni.practice.spring.data.movieboxproject.repositories;

import ir.alirezaaliajni.practice.spring.data.movieboxproject.model.User;
import ir.alirezaaliajni.practice.spring.data.movieboxproject.model.UserSimple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(nativeQuery = true , name = "findSimpleUserBuyNative")
    UserSimple findByUsernameNative(@Param("u") String username);

    @Query(nativeQuery = true , name = "findAllSimpleUserBuyNative")
    List<UserSimple> findAllByUsernameNative();

    @Modifying
    @Query(nativeQuery = true, name = "updateSimpleNative")
    Integer updateSimpleNative(@Param("bool") boolean enable,@Param("id") int id);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
