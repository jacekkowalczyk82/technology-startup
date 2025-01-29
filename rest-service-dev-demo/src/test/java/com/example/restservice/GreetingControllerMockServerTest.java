package com.example.restservice;  
  
import org.junit.jupiter.api.AfterEach;  
import org.junit.jupiter.api.BeforeEach;  
import org.junit.jupiter.api.Test;  
import org.mockserver.integration.ClientAndServer;  
import org.mockserver.model.HttpRequest;  
import org.mockserver.model.HttpResponse;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.test.context.SpringBootTest;  
import org.springframework.boot.test.web.client.TestRestTemplate;  
import org.springframework.boot.test.web.server.LocalServerPort;  
  
import static org.junit.jupiter.api.Assertions.assertNotNull;  
import static org.junit.jupiter.api.Assertions.assertTrue;  
  
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  
public class GreetingControllerMockServerTest {  
  
    @LocalServerPort  
  private int port;  
  
    @Autowired  
  private TestRestTemplate restTemplate;  
  
  private ClientAndServer mockServer;  
  
    @BeforeEach  
  public void startMockServer() {  
        mockServer = ClientAndServer.startClientAndServer(1080);  
    }  
  
    @AfterEach  
  public void stopMockServer() {  
        mockServer.stop();  
    }  
  
    @Test  
  public void greetingShouldReturnDefaultMessage() {  
        mockServer.when(  
                HttpRequest.request()  
                        .withMethod("GET")  
                        .withPath("/greeting")  
        ).respond(  
                HttpResponse.response()  
                        .withStatusCode(200)  
                        .withBody("{\"id\":1,\"content\":\"Hello, World!\"}")  
        );  
  
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/greeting", String.class);  
        assertTrue(response.contains("Hello, World!"));  
    }  
  
    @Test  
  public void greetingShouldReturnCustomMessage() {  
        mockServer.when(  
                HttpRequest.request()  
                        .withMethod("GET")  
                        .withPath("/greeting")  
                        .withQueryStringParameter("name", "Jacek")  
        ).respond(  
                HttpResponse.response()  
                        .withStatusCode(200)  
                        .withBody("{\"id\":1,\"content\":\"Hello, Jacek!\"}")  
        );  
  
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/greeting?name=Jacek", String.class);  
        assertTrue(response.contains("Hello, Jacek!"));  
    }  
}