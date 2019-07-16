package com.tgrajkowski.service;

import com.tgrajkowski.model.job.JobDaoProxy;
import com.tgrajkowski.model.job.JobDto;
import com.tgrajkowski.model.job.active.title.ActiveTitle;
import com.tgrajkowski.model.job.active.user.ActiveUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReportServiceTest {
    @Autowired
    private ReportService reportService;


    @InjectMocks
    private JFreeChartServiceTime jFreeChartServiceTime;

    @InjectMocks
    private PdfService pdfService;

    @Mock
    private JobDaoProxy jobDaoProxy;

    @Test
    public void createRepart() throws IOException {
        // Given
        List<JobDto> jobDtos = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        jobDtos.add(new JobDto(calendar.getTime(), 8));
        calendar.add(Calendar.DATE, -1);
        jobDtos.add(new JobDto(calendar.getTime(), 4));
        calendar.add(Calendar.DATE, -1);
        jobDtos.add(new JobDto(calendar.getTime(), 2));
        Mockito.when(jobDaoProxy.findDataForMonthlyChart()).thenReturn(jobDtos);

        List<ActiveUser> activeUsersFound = new ArrayList<>();
        activeUsersFound.add(new ActiveUser("tomek", 100));
        activeUsersFound.add(new ActiveUser("tomek2", 102));
        activeUsersFound.add(new ActiveUser("tomek3", 103));
        Mockito.when(jobDaoProxy.findTheMostActiveUsers()).thenReturn(activeUsersFound);

        List<ActiveTitle> theMostActiveTitle = new ArrayList<>();
        theMostActiveTitle.add(new ActiveTitle("title1", 10));
        theMostActiveTitle.add(new ActiveTitle("title1", 20));
        theMostActiveTitle.add(new ActiveTitle("title1", 30));
        Mockito.when(jobDaoProxy.findTheMostActiveTitle()).thenReturn(theMostActiveTitle);


        // When
        ByteArrayOutputStream byteArrayOutputStream = reportService.createRepart();
        System.out.println(byteArrayOutputStream.size());
//        OutputStream outputStream= new FileOutputStream(new File("src/test/java/resources/test-report.pdf"));
//        byteArrayOutputStream.writeTo(outputStream);
//        byteArrayOutputStream.close();
//        outputStream.close();
    }
}
