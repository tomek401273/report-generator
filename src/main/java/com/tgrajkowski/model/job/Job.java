package com.tgrajkowski.model.job;

import com.tgrajkowski.model.job.active.title.ActiveTitle;
import com.tgrajkowski.model.job.active.user.ActiveUser;
import com.tgrajkowski.model.user.Users;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@SqlResultSetMappings({
        @SqlResultSetMapping(name = JobDaoIml.STATEMENT_SQLMAP, classes = {
                @ConstructorResult(targetClass = JobDto.class,
                        columns = {

                                @ColumnResult(name = "date_trunc", type = Date.class),
                                @ColumnResult(name = "count", type = Integer.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = JobDaoIml.THE_MOST_ACTIVE_USER, classes = {
                @ConstructorResult(targetClass = ActiveUser.class,
                columns = {
                        @ColumnResult(name = "login", type = String.class),
                        @ColumnResult(name = "count", type = Integer.class)
                })
        }),
        @SqlResultSetMapping(name = JobDaoIml.THE_MOST_ACTIVE_TITLE, classes = {
                @ConstructorResult(targetClass = ActiveTitle.class,
                columns = {
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "count", type = Integer.class)
                })
        })
})


@Entity
@Table(name = "jobs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date date;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Users users;

}
