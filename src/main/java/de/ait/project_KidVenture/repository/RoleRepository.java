package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByTitle(String title);
}
