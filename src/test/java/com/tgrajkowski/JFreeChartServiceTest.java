package com.tgrajkowski;

import com.tgrajkowski.service.JFreeChartServiceTime;
import com.tgrajkowski.service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JFreeChartServiceTest {

    @Autowired
    private JFreeChartServiceTime jFreeChartServiceTime;

    @Autowired
    private ReportService reportService;

    @Test
    public void test1() {
//        jFreeChartService.createChart2();
     InputStream inputStream = jFreeChartServiceTime.create();
        reportService.generate(inputStream);

    }

}
