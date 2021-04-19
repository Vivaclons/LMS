package kz.spring.demo.demo.repository;


import kz.spring.demo.demo.entity.Query;
import kz.spring.demo.demo.entity.RStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    List<Query> getRequestByUser_LoginAndStatusIs(String login, RStatus status);
    List<Query> getRequestsByUser_LoginAndStatusIsAndLastDateLessThan(String login, RStatus status, Date date);
    List<Query> getRequestsByStatusAndLastDateLessThan(RStatus status, Date date);
    List<Query> getRequestByStatus(RStatus status);
}
