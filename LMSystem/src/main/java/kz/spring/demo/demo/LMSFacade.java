package kz.spring.demo.demo;

import kz.spring.demo.demo.Controller.*;
import kz.spring.demo.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component("lmsFacade")
public class LMSFacade {
    @Autowired
    private UserController userController;
    @Autowired
    private BookController bookController;
    @Autowired
    private QueryController queryController;
    @Autowired
    private NoticeController noticeController;
    @Autowired
    private CollectionController collectionController;

    private Scanner in;

    private BufferedReader reader;

    private boolean isAuth;
    private String currentUser;

    public LMSFacade() {
    }

    public void login() {
        while (true) {
            System.out.println("\n1 - User");
            System.out.print("input: ");
            String op = in.next();

            if (op.equals("1")) {
                System.out.print("\nLogin: ");
                String login = in.next();

                System.out.print("Password: ");
                String password = in.next();

                boolean status = userController.check(login, password);

                if (status) {
                    currentUser = login;
                    isAuth = true;
                    break;
                } else {
                    System.out.println("Login or password is not correct!");
                }
            }
        }
    }

    public void show() throws IOException {
        menu();
    }
    private void menu() throws IOException {
        while (true) {
            if (!isAuth) {
                System.out.println("Please authorize:");
                login();
            } else {
                System.out.println("\nUser: " + currentUser);
                System.out.println("1 - new issue request");
                System.out.println("2 - new return request");
                System.out.println("\n3 - search books by name");
                System.out.println("4 - search books by description");
                System.out.println("5 - search books by author");
                System.out.println("\n6 - show my issued books");
                System.out.println("7 - show my requested books");
                System.out.println("8 - show my books over due date");
                System.out.println("9 - Show books availability");
                if (getUnreadNotificationSize() != 0) {
                    System.out.println("10 - show my unread notifications");
                }
                System.out.println("\n0 - Logout");
                System.out.print("input: ");
                String choice = in.next();

                switch (choice) {
                    case "1":
                        makeNewIssueQuery();
                        break;
                    case "2":
                        makeNewReturnQuery();
                        break;
                    case "3":
                        searchBooks(1);
                        break;
                    case "4":
                        searchBooks(2);
                        break;
                    case "5":
                        searchBooks(3);
                        break;
                    case "6":
                        showMyIssuedBooks();
                        break;
                    case "7":
                        showQueryBooks();
                        break;
                    case "8":
                        showMyBooksOverDueDate();
                        break;
                    case "9":
                        showBooksAvailability();
                        break;
                    case "10":
                        showMyUnreadNotifications();
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nError, try again!");
                }
            }
        }
    }

    public int getUnreadNotificationSize() {
        return noticeController.getCountUnreadByUser_Login(currentUser);
    }

    public void makeNewIssueQuery() {
        List<Book> books = bookController.getBooksIsAvailability();

        System.out.println("--- Books ---");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ") " + books.get(i).toString());
        }
        System.out.print("input: ");
        int bookIndex = in.nextInt();

        Book book = books.get(bookIndex - 1);
        User user = userController.getUser(currentUser);

        Query query = new Query();
        query.setStatus(RStatus.IN_PROCESSION_ISSUE);
        query.setBook(book);
        query.setUser(user);

        queryController.makeNewRequest(query);
    }

    public void makeNewReturnQuery() {
        List<Query> queries = queryController.getRequestByUserLoginAndStatus(currentUser, RStatus.ISSUE);

        System.out.println("\n--- Request ISSUE ---");
        for (int i = 0; i < queries.size(); i++) {
            System.out.println((i + 1) + ") " + queries.get(i));
        }
        System.out.print("input: ");
        int requestIndex = in.nextInt();

        Query query = queries.get(requestIndex - 1);

        queryController.changeStatusToReturn(query);
        bookController.incBookQuantitySave(query.getBook());

        Notice notice = new Notice();
        notice.setDate(query.getIssuedDate());
        notice.setMessage("Your request (RETURN) has been accepted.\nBook: " + query.getBook().toString());
        notice.setUser(query.getUser());
        notice.setStatus(Status.UNREAD);
        noticeController.saveNotification(notice);
    }

    public void searchBooks(int type) throws IOException {
        System.out.println("\n--- Search Books ---");
        List<Book> books;

        if (type == 1) {
            System.out.print("input book name: ");
            books = bookController.getBooksByName(reader.readLine().trim());
        } else if (type == 2) {
            System.out.print("input book author: ");
            books = bookController.getBooksByAuthor(reader.readLine().trim());
        } else {
            System.out.print("input book description: ");
            books = bookController.getBooksByDescription(reader.readLine().trim());
        }

        System.out.println("\n--- Books ---");
        System.out.println("Books size: " + books.size());
        int i = 0;
        for (Book book : books) {
            System.out.println((i++ + 1) + ") " + book.toString());
        }
    }

    public void showMyIssuedBooks() {
        List<Query> requests = queryController.getRequestByUserLoginAndStatus(currentUser, RStatus.ISSUE);

        System.out.println("\n--- Request ISSUE ---");
        for (int i = 0; i < requests.size(); i++) {
            System.out.println((i + 1) + ") " + requests.get(i));
        }
    }

    public void showQueryBooks() {
        List<Query> queries = queryController.getRequestByUserLoginAndStatus(currentUser, RStatus.IN_PROCESSION_ISSUE);

        System.out.println("\n--- Requested Books ---");
        int i = 0;
        for (Query query : queries) {
            System.out.println((i++ + 1) + ") " + query.getBook().toString());
        }
    }

    private void showMyBooksOverDueDate() {
        Date date = new Date();
        List<Query> queries = queryController.getByUserLoginAndStatusIsAndLastDateLessThan(
                currentUser,
                RStatus.ISSUE,
                date
        );

        int i = 0;
        System.out.println("\n-- My Books Over DueDate ---");
        for (Query query : queries) {
            System.out.println((i++ + 1) + ") " + queries);
        }
    }

    public void showBooksAvailability() {
        List<Book> books = bookController.getBooksIsAvailability();

        int i = 0;
        System.out.println("\n--- Books Availability ---");
        for (Book book : books) {
            System.out.println((i++ + 1) + ") " + book.toString());
        }
    }

    public void showMyUnreadNotifications() {
        List<Notice> notices = noticeController.getByUserLoginAndStatus(currentUser, Status.UNREAD);

        System.out.println("\n--- My Unread Notifications ---");
        int i = 0;
        for (Notice notice : notices) {
            System.out.println((i++ + 1) + ") " + notice);
        }

        noticeController.changeNotificationsStatusToRead(notices);
    }
}
