package kz.spring.demo.demo.Controller;

import kz.spring.demo.demo.entity.Notice;
import kz.spring.demo.demo.entity.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INoticeService {
    void saveNotification(Notice notice);
    int getUnreadNotificationSizeByMemberLogin(String login);
    List<Notice> getByUserLoginAndStatus(String login, Status status);
    void saveAll(List<Notice> notice);
}
