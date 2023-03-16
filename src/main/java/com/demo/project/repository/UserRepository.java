package com.demo.project.repository;

import com.demo.project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
