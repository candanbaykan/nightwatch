package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @NonNull
    @EntityGraph("UserDetails")
    List<User> findAll();

    @EntityGraph("UserDetails")
    Optional<User> findDetailsById(Long id);

}
