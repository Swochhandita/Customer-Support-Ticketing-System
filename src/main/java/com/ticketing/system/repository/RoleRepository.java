package com.ticketing.system.repository;

import com.ticketing.system.entity.Role;
import com.ticketing.system.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);

}
