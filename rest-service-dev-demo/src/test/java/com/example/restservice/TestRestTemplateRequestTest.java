package com.example.restservice;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestRestTemplateRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    //example with assertJ library
    @Test
    public void greetingShouldReturnDefaultMessageAssertJ() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting",
                String.class)).contains("Hello, World");
    }

    //example with standard JUnit assertions
    @Test
    public void greetingShouldReturnDefaultMessageJunit() throws Exception {
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/greeting",
                String.class);
        Assertions.assertTrue(response.contains("Hello, World"), "The response from the service does not contain expected Hello, World");
    }



}

