package kz.spring.demo.demo.Controller;

import kz.spring.demo.demo.entity.Book;
import kz.spring.demo.demo.entity.Notice;
import kz.spring.demo.demo.entity.Status;
import kz.spring.demo.demo.repository.NoticeRepository;
import kz.spring.demo.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notices")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/{}")
    public Integer countNotificationsByUser_LoginAndStatus(@PathVariable String login, @PathVariable("status") Status status) {
        return noticeRepository.countNotificationsByUser_LoginAndStatus(login, status);
    }

    @GetMapping("/{}")
    public List<Notice> getNotificationsByUser_LoginAndStatus(@PathVariable String login, @PathVariable("status") Status status) {
        return noticeRepository.getNotificationsByUser_LoginAndStatus(login, status);
    }

    @PostMapping("")
    public void saveNotification(@PathVariable Notice notice){
        noticeService.saveNotification(notice);
    }


    public int getCountUnreadByUser_Login(String login) {
        return noticeService.getUnreadNotificationSizeByMemberLogin(login);
    }

    public List<Notice> getByUserLoginAndStatus(String login, Status status) {
        return noticeService.getByUserLoginAndStatus(login, status);
    }

    public void changeNotificationsStatusToRead(List<Notice> notifications) {
        for (int i = 0; i < notifications.size(); i++) {
            notifications.get(i).setStatus(Status.READ);
        }

        noticeService.saveAll(notifications);
    }
}
