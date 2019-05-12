package com.tgrajkowski.model.job;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JobDaoIml {
    @PersistenceContext
    private EntityManager em;

    static final String STATEMENT_SQLMAP = "Statement-SQL-Mapping";

    public List<JobDto> findPipelinedStatements() {
        Query query = em.createNativeQuery(
                "select date_trunc('day', date), count(id) from jobs group by date_trunc('day', date) ORDER BY date_trunc('day', date) DESC",
                STATEMENT_SQLMAP);
        return query.getResultList();
    }
}
