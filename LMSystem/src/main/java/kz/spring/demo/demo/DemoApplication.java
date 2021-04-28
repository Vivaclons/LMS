package kz.spring.demo.demo;

import kz.spring.demo.demo.configuration.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class, args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SecurityConfig.class);

//        BookRepository bookRepository = (BookRepository) ctx.getBean(Book.class);
//        CollectionRepository collectionRepository = (CollectionRepository) ctx.getBean(Collection.class);
//        QueryRepository queryRepository = (QueryRepository) ctx.getBean(Query.class);
//        UserRepository userRepository = (UserRepository) ctx.getBean(User.class);
//        NoticeRepository noticeRepository = (NoticeRepository) ctx.getBean(Notice.class);
//
//        Book book = new Book();
//        book.setId(1L);
//        book.setName("World war");
//        book.setAuthor("World");
//        book.setDescription("Horror");
//        book.setYear(1981);
//        book.setQuantity(7);
//
//        Book book1 = new Book();
//        book.setId(2L);
//        book.setName("Moon");
//        book.setAuthor("Moon");
//        book.setDescription("Advantage");
//        book.setYear(1991);
//        book.setQuantity(6);
//
//        Book book2 = new Book();
//        book.setId(3L);
//        book.setName("2020");
//        book.setAuthor("2020");
//        book.setDescription("History");
//        book.setYear(2020);
//        book.setQuantity(10);
//
//        Collection collection = new Collection();
//        collection.setId(1L);
//        collection.setName("History");
//
//        Collection collection1 = new Collection();
//        collection.setId(1L);
//        collection.setName("Advantage");
//
//        Collection collection2 = new Collection();
//        collection.setId(1L);
//        collection.setName("Horror");
//
//        Query query = new Query();
//        query.setId(1L);
//        query.setIssuedDate(1212121212121L);
//        query.setLastDate(1212121212121L);
//        query.setReturnDate(1212121212121L);
//
//        Set<Book> bookSet = new HashSet<>();
//        bookSet.add(book2);
//        collection.setBooks(bookSet);
//
//        Set<Book> bookSet1 = new HashSet<>();
//        bookSet.add(book);
//        collection.setBooks(bookSet);
//
//        Set<Book> bookSet2 = new HashSet<>();
//        bookSet.add(book1);
//        collection.setBooks(bookSet);

        LMSFacade lmsFacade = ctx.getBean("lmsFacade", LMSFacade.class);
        lmsFacade.show();
    }

}
