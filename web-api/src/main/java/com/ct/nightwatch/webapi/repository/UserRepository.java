package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph("UserDetails")
    Optional<User> findDetailsById(Long id);

}
