package kz.spring.demo.demo.Controller;

import kz.spring.demo.demo.entity.Notice;
import kz.spring.demo.demo.entity.Query;
import kz.spring.demo.demo.entity.RStatus;
import kz.spring.demo.demo.entity.Status;
import kz.spring.demo.demo.repository.QueryRepository;
import kz.spring.demo.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/queries")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private QueryRepository queryRepository;

    @GetMapping("/{status}")
    public List<Query> getNotificationsByUser_LoginAndStatus(@PathVariable("status") RStatus status) {
        return queryRepository.getRequestByStatus(status);
    }

    @GetMapping("/{status}")
    public List<Query> getNotificationsByUser_LoginAndStatus(@PathVariable String login, @PathVariable("status") RStatus status) {
        return queryRepository.getRequestByUser_LoginAndStatusIs(login, status);
    }

//    @GetMapping("/{}")
//    public List<Query> getRequestsByStatusAndLastDateLessThan(@PathVariable("login") String login, @PathVariable("status") RStatus status, @PathVariable("date") Date date) {
//        return queryRepository.getRequestsByStatusAndLastDateLessThan(login, status, date);
//    }

//    @GetMapping("/{}")
//    public List<Query> getNotificationsByUser_LoginAndStatus(@PathVariable String login, @PathVariable("date") Date date) {
//        return queryRepository.getRequestsByStatusAndLastDateLessThan(login, date);
//    }


    public void makeNewRequest(Query query) {
        queryService.makeNewRequest(query);
    }

    public List<Query> getRequestByUserLoginAndStatus(String login, RStatus status) {
        return queryService.getByUserLoginAndStatus(login, status);
    }

    public List<Query> getByUserLoginAndStatusIsAndLastDateLessThan(String login, RStatus status, Date date) {
        return queryService.getByUserLoginAndStatusIsAndLastDateLessThan(login, status, date);
    }

    public List<Query> getStatusAndLastDateLessThan(RStatus status, Date date) {
        return queryService.getStatusAndLastDateLessThan(status, date);
    }

    public List<Query> getRequestByStatus(RStatus status) {
        return queryService.getByStatus(status);
    }

    public void changeStatusToIssue(Query query) {
        query.setStatus(RStatus.ISSUE);

        Date date = new Date();
        query.setIssuedDate(date);

        Date lasDay = new Date(query.getIssuedDate().getTime() + (60 * 60 * 24 * 14 * 1000));
        query.setLastDate(lasDay);

        queryService.updateRequest(query);
    }

    public void changeStatusToReturn(Query query) {
        query.setStatus(RStatus.RETURN);

        Date date = new Date();
        query.setReturnDate(date);

        queryService.updateRequest(query);
    }
}
