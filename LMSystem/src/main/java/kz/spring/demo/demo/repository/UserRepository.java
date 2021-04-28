package kz.spring.demo.demo.repository;

import kz.spring.demo.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByLoginAndPassword(String login, String password);
    User getUserByLogin(String login);

    User findByLogin(String login);
}
