package com.tgrajkowski.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class TestController {
    @Value("${cloud-repository.own.value}")
    private String myString;

    @GetMapping(value = "/test")
    public String tes() throws UnknownHostException {
        System.out.println("myString: "+myString);
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        System.out.println("Host Name:- " + inetAddress.getHostName());
        return myString;
    }
}
