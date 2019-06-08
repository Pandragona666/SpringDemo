package pl.sda.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.spring.demo.model.Role;
import pl.sda.spring.demo.model.User;
import pl.sda.spring.demo.repository.RoleRepository;
import pl.sda.spring.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User saveUser(String email, String password) {
        User user = new User(email, password);
        //INSERT INTO user VALUES (default, T, T, default, default);
        userRepository.save(user);
        setRole(user.getId(),1L);
        userRepository.save(user);
        return user;
    }

    public User confirmUser(Long id) {
        User user = userRepository.getOne(id);
        user.setActivityFlag(true);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.getOne(id);
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public String getUser(Long id) {
       if (userRepository.findById(id).isPresent()){
           User user = userRepository.getOne(id);
           return user.toString();
       }
       return "Nie ma takiego użytkownika";
    }

    public User setRole(Long id, Long roleId) {
        if (userRepository.findById(id).isPresent() && roleRepository.findById(roleId).isPresent()){
            User user = userRepository.getOne(id);
            Role role = roleRepository.getOne(roleId);
            user.addRole(role);
            return userRepository.save(user);
        }
        return new User();
    }
}
