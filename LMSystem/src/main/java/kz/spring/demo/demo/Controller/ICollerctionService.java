package kz.spring.demo.demo.Controller;


import kz.spring.demo.demo.entity.Collection;
import org.springframework.stereotype.Service;

@Service
public interface ICollerctionService {
    void save(Collection collection);
}
