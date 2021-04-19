package kz.spring.demo.demo.service;

import kz.spring.demo.demo.Controller.IQueryService;
import kz.spring.demo.demo.entity.Query;
import kz.spring.demo.demo.entity.RStatus;
import kz.spring.demo.demo.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QueryService implements IQueryService {
    @Autowired
    private QueryRepository queryRepository;

    @Override
    public void makeNewRequest(Query query) {
        queryRepository.save(query);
    }

    @Override
    public List<Query> getByUserLoginAndStatus(String login, RStatus status) {
        return queryRepository.getRequestByUser_LoginAndStatusIs(login, status);
    }

    @Override
    public List<Query> getByUserLoginAndStatusIsAndLastDateLessThan(String login, RStatus status, Date date) {
        return queryRepository.getRequestsByUser_LoginAndStatusIsAndLastDateLessThan(login, status, date);
    }

    @Override
    public List<Query> getStatusAndLastDateLessThan(RStatus status, Date date) {
        return queryRepository.getRequestsByStatusAndLastDateLessThan(status, date);
    }

    @Override
    public List<Query> getByStatus(RStatus status) {
        return queryRepository.getRequestByStatus(status);
    }

    @Override
    public void updateRequest(Query query) {
        queryRepository.save(query);
    }
}
