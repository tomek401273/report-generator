package com.tgrajkowski.model.user;

import com.tgrajkowski.model.job.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String login;

    @OneToMany(
            targetEntity = Job.class,
            mappedBy = "users",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Job> jobList = new ArrayList<>();
}
