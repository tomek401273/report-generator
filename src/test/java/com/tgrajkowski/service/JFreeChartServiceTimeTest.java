package com.tgrajkowski.service;

import com.tgrajkowski.model.job.JobDaoProxy;
import com.tgrajkowski.model.job.JobDto;
import org.jfree.data.time.Day;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class JFreeChartServiceTimeTest {
    @InjectMocks
    private JFreeChartServiceTime jFreeChartServiceTime;

    @Mock
    private JobDaoProxy jobDaoProxy;

    @Test
    public void create() throws IOException {
        // Given
        List<JobDto> jobDtos = new ArrayList<>();
        Calendar calendar= Calendar.getInstance();
        // When
        jobDtos.add(new JobDto(calendar.getTime(), 8));
        calendar.add(Calendar.DATE, -1);
        jobDtos.add(new JobDto(calendar.getTime(), 4));
        calendar.add(Calendar.DATE, -1);
        jobDtos.add(new JobDto(calendar.getTime(), 2));
        Mockito.when(jobDaoProxy.findDataForMonthlyChart()).thenReturn(jobDtos);

        ByteArrayOutputStream byteArrayOutputStream = jFreeChartServiceTime.create();
        OutputStream outputStream = new FileOutputStream(new File("src/test/java/resources/test-chart.jpg"));
        byteArrayOutputStream.writeTo(outputStream);
        byteArrayOutputStream.close();
        outputStream.close();
    }
}
