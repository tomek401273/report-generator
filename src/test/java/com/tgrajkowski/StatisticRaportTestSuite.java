//package com.tgrajkowski;
//
//import com.tgrajkowski.model.job.JobDaoIml;
//import com.tgrajkowski.model.job.active.title.ActiveTitle;
//import com.tgrajkowski.model.job.active.user.ActiveUser;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class StatisticRaportTestSuite {
//    @Autowired
//    private JobDaoIml jobDaoIml;
//
//    @Test
//    public void getThemostActiveUsers() {
//        List<ActiveUser> theMostActiveUsers = jobDaoIml.findTheMostActiveUsers();
//        List<ActiveUser> expected = new ArrayList<>();
//        expected.add(new ActiveUser("tomek6", 2));
//        expected.add(new ActiveUser("tomek2", 1));
//        expected.add(new ActiveUser("tomek", 1));
//        expected.add(new ActiveUser("tomek5", 1));
//        expected.add(new ActiveUser("tomek3", 1));
//        expected.add(new ActiveUser("tomek371240@gmail.com", 1));
//        expected.add(new ActiveUser("tomek8", 1));
//        Assert.assertEquals(7, theMostActiveUsers.size());
//        theMostActiveUsers.forEach(System.out::println);
//        theMostActiveUsers.removeAll(expected);
//        theMostActiveUsers.forEach(System.out::println);
////        Assert.assertTrue(theMostActiveUsers.equals(expected));
//    }
//
//    @Test
//    public void getTheMostActiveBook() {
//        List<ActiveTitle> activeTitles = jobDaoIml.findTheMostActiveTitle();
//        activeTitles.forEach(System.out::println);
//
//    }
//}
