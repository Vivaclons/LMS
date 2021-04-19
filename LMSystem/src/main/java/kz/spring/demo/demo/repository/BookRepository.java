package kz.spring.demo.demo.repository;

import kz.spring.demo.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getBooksByQuantityIsNot(Integer quantity);
    List<Book> getBooksByNameContaining(String name);
    List<Book> getBooksByAuthorContaining(String author);
    List<Book> getBooksByDescriptionContaining(String description);
    List<Book> getAllBy();
}
