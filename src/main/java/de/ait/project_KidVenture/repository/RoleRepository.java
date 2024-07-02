package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByTitle(String title);
}
