package kz.spring.demo.demo.service;

import kz.spring.demo.demo.Controller.INoticeService;
import kz.spring.demo.demo.entity.Notice;
import kz.spring.demo.demo.entity.Status;
import kz.spring.demo.demo.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService implements INoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public void saveNotification(Notice notice) {
        noticeRepository.save(notice);
    }

    @Override
    public int getUnreadNotificationSizeByMemberLogin(String login) {
        return noticeRepository.countNotificationsByUser_LoginAndStatus(login, Status.UNREAD);
    }

    @Override
    public List<Notice> getByUserLoginAndStatus(String login, Status status) {
        return noticeRepository.getNotificationsByUser_LoginAndStatus(login, status);
    }

    @Override
    public void saveAll(List<Notice> notice) {
        noticeRepository.saveAll(notice);
    }
}
