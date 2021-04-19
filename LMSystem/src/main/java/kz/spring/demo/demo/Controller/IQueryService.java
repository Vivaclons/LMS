package kz.spring.demo.demo.Controller;

import kz.spring.demo.demo.entity.Query;
import kz.spring.demo.demo.entity.RStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface IQueryService {
    void makeNewRequest(Query request);
    List<Query> getByUserLoginAndStatus(String login, RStatus status);
    List<Query> getByUserLoginAndStatusIsAndLastDateLessThan(String login, RStatus status, Date date);
    List<Query> getStatusAndLastDateLessThan(RStatus status, Date date);
    List<Query> getByStatus(RStatus status);
    void updateRequest(Query query);
}
