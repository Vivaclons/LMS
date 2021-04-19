package kz.spring.demo.demo.service;

import kz.spring.demo.demo.Controller.ICollerctionService;
import kz.spring.demo.demo.entity.Collection;
import kz.spring.demo.demo.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService implements ICollerctionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public void save(Collection collection) {
        collectionRepository.save(collection);
    }
}
