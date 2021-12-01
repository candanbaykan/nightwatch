package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @EntityGraph("ManagerDetails")
    Optional<Manager> findDetailsById(Long id);

}
