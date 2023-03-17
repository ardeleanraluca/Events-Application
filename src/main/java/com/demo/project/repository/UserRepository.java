package com.demo.project.repository;

import com.demo.project.entity.RoleEntity;
import com.demo.project.entity.UserAccEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccEntity, Long> {
    Boolean existsByEmail(String email);

    Optional<UserAccEntity> findByEmail(String email);

    Optional<List<UserAccEntity>> findAllByRole(RoleEntity role);

    void deleteById(Long id);
}
