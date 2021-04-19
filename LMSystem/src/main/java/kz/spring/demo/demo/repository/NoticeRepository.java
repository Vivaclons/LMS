package kz.spring.demo.demo.repository;

import kz.spring.demo.demo.entity.Notice;
import kz.spring.demo.demo.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Integer countNotificationsByUser_LoginAndStatus(String login, Status status);
    List<Notice> getNotificationsByUser_LoginAndStatus(String login, Status status);
}
