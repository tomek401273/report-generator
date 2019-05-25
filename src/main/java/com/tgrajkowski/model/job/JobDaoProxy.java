package com.tgrajkowski.model.job;

import com.tgrajkowski.model.job.active.title.ActiveTitle;
import com.tgrajkowski.model.job.active.user.ActiveUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "cloud-repository")
public interface JobDaoProxy {
    @RequestMapping(value = "/job/data/monthly/chart", method = RequestMethod.GET)
    List<JobDto> findDataForMonthlyChart();

    @RequestMapping(value = "/job/the/most/active/users", method = RequestMethod.GET)
    List<ActiveUser> findTheMostActiveUsers();

    @RequestMapping(value = "/job/the/most/active/title", method = RequestMethod.GET)
    List<ActiveTitle> findTheMostActiveTitle();
}
