package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @EntityGraph("UserDetails")
    Optional<User> findDetailsById(Long id);

    @EntityGraph("UserDetails")
    Optional<User> findByUsername(String username);

    boolean existsByIdAndUsername(Long id, String username);

}
