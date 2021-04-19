package kz.spring.demo.demo.Controller;


import kz.spring.demo.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    boolean checkByLoginAndPassword(String login, String password);
    User getByLogin(String login);
}
