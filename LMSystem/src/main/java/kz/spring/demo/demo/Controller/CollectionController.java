package kz.spring.demo.demo.Controller;

import kz.spring.demo.demo.entity.Book;
import kz.spring.demo.demo.entity.Collection;
import kz.spring.demo.demo.repository.CollectionRepository;
import kz.spring.demo.demo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/collections")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionRepository collectionRepository;

    public void saveNewCollection(Collection collection) {
        collectionService.save(collection);
    }
}
