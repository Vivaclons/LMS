package kz.spring.demo.demo.Controller;

import kz.spring.demo.demo.entity.User;
import kz.spring.demo.demo.repository.UserRepository;
import kz.spring.demo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userName}")
    public User getUserByLogin(@PathVariable("userName") String userName) {
        return userRepository.getUserByLogin(userName);
    }

    // /users/find/?name=asdadas&age=20
    @GetMapping("")
    public boolean existsUserByLoginAndPassword(@RequestParam String userName,
                                                @RequestParam("password") String password) {
        return userRepository.existsUserByLoginAndPassword(userName, password);
    }

    @PostMapping("/response")
    public User createUser(@RequestBody User user) {
        return userRepository.saveAndFlush(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {
        user.setId(id);
        return userRepository.saveAndFlush(user);
    }

    @PatchMapping("/{id}")
    public User updateUserAge(@PathVariable Long id,
                              @RequestParam String name) {
        User user = userRepository.findById(id).get();
        user.setName(name);
        return userRepository.saveAndFlush(user);
    }

    public boolean check(String login, String password) {
        return userService.checkByLoginAndPassword(login, password);
    }

    public User getUser(String login) {
        return userService.getByLogin(login);
    }
}
