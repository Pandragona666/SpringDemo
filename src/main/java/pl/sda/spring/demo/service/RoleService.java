package pl.sda.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.spring.demo.model.Role;
import pl.sda.spring.demo.repository.RoleRepository;

@Service
public class RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(String roleName) {
        Role role = new Role(roleName);
        roleRepository.save(role);
        return role;
    }
}
