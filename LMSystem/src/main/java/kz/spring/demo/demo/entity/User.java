package kz.spring.demo.demo.entity;

import javax.persistence.Query;
import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String name;


    public User() {}

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Notice> notifications;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<javax.persistence.Query> requests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Notice> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notice> notifications) {
        this.notifications = notifications;
    }

    public Set<javax.persistence.Query> getRequests() {
        return requests;
    }

    public void setRequests(Set<Query> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", notifications=" + notifications +
                ", requests=" + requests +
                '}';
    }
}
