package kz.spring.demo.demo.service;

import kz.spring.demo.demo.Controller.IBookService;
import kz.spring.demo.demo.entity.Book;
import kz.spring.demo.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooksIsAvailability() {
        return bookRepository.getBooksByQuantityIsNot(0);
    }

    @Override
    public List<Book> getBooksByName(String name) {
        return bookRepository.getBooksByNameContaining(name);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthorContaining(author);
    }

    @Override
    public List<Book> getBooksByDescription(String description) {
        return bookRepository.getBooksByDescriptionContaining(description);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAllBy();
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }
}