package com.meow.repositories;

import com.meow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select * from user where (:email is null or email = :email) and (:phone is null or phone = :phone)")
    Optional<User> findByEmailAndPhone(
            @Param("email") String email,
            @Param("phone") String phone
    );
}
