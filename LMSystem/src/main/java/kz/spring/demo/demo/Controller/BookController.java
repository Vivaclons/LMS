package kz.spring.demo.demo.Controller;

import kz.spring.demo.demo.entity.Book;
import kz.spring.demo.demo.entity.User;
import kz.spring.demo.demo.repository.BookRepository;
import kz.spring.demo.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{quantity}")
    public List<Book> getBooksByQuantityIsNot(@PathVariable("quantity") Integer quantity) {
        return bookRepository.getBooksByQuantityIsNot(quantity);
    }

    @GetMapping("/{name}")
    public List<Book> getBooksByNameContaining(@PathVariable("name") String name) {
        return bookRepository.getBooksByNameContaining(name);
    }

    @GetMapping("/{author}")
    public List<Book> getBooksByAuthorContaining(@PathVariable("author") String author) {
        return bookRepository.getBooksByAuthorContaining(author);
    }

    @GetMapping("/{description}")
    public List<Book> getBooksByDescriptionContaining(@PathVariable("description") String description) {
        return bookRepository.getBooksByDescriptionContaining(description);
    }

    @GetMapping("/{}")
    public List<Book> getBooksByDescriptionContaining() {
        return bookRepository.getAllBy();
    }



    public List<Book> getBooksIsAvailability() {
        return bookService.getBooksIsAvailability();
    }

    public List<Book> getBooksByName(String name) {
        return bookService.getBooksByName(name);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookService.getBooksByAuthor(author);
    }

    public List<Book> getBooksByDescription(String description) {
        return bookService.getBooksByDescription(description);
    }

    public void decBookQuantitySave(Book book) {
        book.setQuantity(book.getQuantity() - 1);
        bookService.updateBook(book);
    }

    public void incBookQuantitySave(Book book) {
        book.setQuantity(book.getQuantity() + 1);
        bookService.updateBook(book);
    }

    public List<Book> getAllBook() {
        return bookService.getAll();
    }
}
