package com.demo.project.repository;

import com.demo.project.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
