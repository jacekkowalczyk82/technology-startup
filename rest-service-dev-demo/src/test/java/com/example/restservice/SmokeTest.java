package com.example.restservice;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;  

@SpringBootTest
public class SmokeTest {

    @Autowired
    private GreetingController controller;

    @Test
    public void contextLoads() throws Exception {
        //org.assertj
        assertThat(controller).isNotNull();
    }

    @Test  
    public void contextLoads2() throws Exception {  
        //org.junit.jupiter.api.Assertions
       assertNotNull(controller);  
    } 

}
