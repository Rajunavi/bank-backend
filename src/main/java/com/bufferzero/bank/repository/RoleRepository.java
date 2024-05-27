package com.bufferzero.bank.repository;

import com.bufferzero.bank.entities.ERole;
import com.bufferzero.bank.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
