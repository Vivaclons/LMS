package kz.spring.demo.demo.Controller;


import kz.spring.demo.demo.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {
    List<Book> getBooksIsAvailability();
    List<Book> getBooksByName(String name);
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByDescription(String description);
    List<Book> getAll();
}
