package pl.sda.spring.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.spring.demo.model.Role;
import pl.sda.spring.demo.service.RoleService;

@RestController
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/addRole")
    public Role createNewRole(String roleName){
       return roleService.saveRole(roleName);
    }
}
