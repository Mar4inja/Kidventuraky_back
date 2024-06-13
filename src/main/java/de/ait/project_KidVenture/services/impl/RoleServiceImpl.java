package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Role;
import de.ait.project_KidVenture.repository.RoleRepository;
import de.ait.project_KidVenture.services.RoleService;
import lombok.AllArgsConstructor;

import javax.management.relation.RoleNotFoundException;

@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role getRole() {
        Role role = roleRepository.findByTitle("ROLE_USER");
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        return role;
    }
}
