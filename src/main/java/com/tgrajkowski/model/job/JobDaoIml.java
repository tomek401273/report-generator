package com.tgrajkowski.model.job;

import com.tgrajkowski.model.job.active.title.ActiveTitle;
import com.tgrajkowski.model.job.active.user.ActiveUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JobDaoIml {
    @PersistenceContext
    private EntityManager em;

    static final String STATEMENT_SQLMAP = "Statement-SQL-Mapping";
    static final String THE_MOST_ACTIVE_USER ="The-Most-Active-User";
    static final String THE_MOST_ACTIVE_TITLE ="The-Most-Active-Title";

    public List<JobDto> findDataForMonthlyChart() {
        Query query = em.createNativeQuery(
                "SELECT DATE_TRUNC('day', date) AS date_trunc, count(id) " +
                        "FROM jobs " +
                        "WHERE date >= DATE_TRUNC('month', NOW()) " +
                        "GROUP BY date_trunc " +
                        "ORDER BY date_trunc DESC",
                STATEMENT_SQLMAP);
        return query.getResultList();
    }

    public List<ActiveUser> findTheMostActiveUsers() {
        Query query = em.createNativeQuery(
                "SELECT u.login, count(*) count " +
                        "FROM users u " +
                        "INNER JOIN jobs j ON u.id = j.user_id " +
                        "WHERE date >= DATE_TRUNC('month', NOW()) " +
                        "GROUP BY u.login ORDER BY count DESC LIMIT 10;",
                THE_MOST_ACTIVE_USER
        );
        return query.getResultList();
    }

    public List<ActiveTitle> findTheMostActiveTitle() {
        Query query =  em.createNativeQuery(
                "SELECT j.title, count(*) count " +
                        "FROM users u " +
                        "INNER JOIN jobs j ON u.id = j.user_id " +
                        "WHERE date >= DATE_TRUNC('month', NOW()) " +
                        "GROUP BY j.title " +
                        "ORDER BY count DESC LIMIT 10;", THE_MOST_ACTIVE_TITLE);
        return query.getResultList();
    }
}
