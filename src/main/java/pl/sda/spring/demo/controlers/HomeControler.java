package pl.sda.spring.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.spring.demo.model.User;
import pl.sda.spring.demo.service.UserService;

import java.util.List;

@RestController
public class HomeControler {

    UserService userService;
    @Autowired
    public HomeControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello świecie";
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return "Hello " + name + " :)";
    }

    @PostMapping("/register")
    public User register(String email, String password){
        return userService.saveUser(email, password);
    }

    @PutMapping("/confirm/{id}")
    public User confirm(@PathVariable Long id){
        return userService.confirmUser(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PostMapping("/setAdmin/{id}")
    public User setAdmin(@PathVariable Long id){
        return userService.setRole(id, 2L);
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

}
