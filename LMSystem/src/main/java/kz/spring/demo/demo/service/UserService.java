package kz.spring.demo.demo.service;

import kz.spring.demo.demo.Controller.IUserService;
import kz.spring.demo.demo.entity.User;
import kz.spring.demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkByLoginAndPassword(String login, String password) {
        return userRepository.existsUserByLoginAndPassword(login, password);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
