package kz.spring.demo.demo.service;

import kz.spring.demo.demo.Controller.IUserService;
import kz.spring.demo.demo.entity.User;
import kz.spring.demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkByLoginAndPassword(String login, String password) {
        return userRepository.existsUserByLoginAndPassword(login, password);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
