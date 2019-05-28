package com.tgrajkowski.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${cloud-repository.own.value}")
    private String myString;

    @GetMapping(value = "/test")
    public String tes(){
        return myString;
    }
}