package com.example.restservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpClientRequestTest {

    public static final int HTTP_STATUS_OK_200 = 200;
    public static final int HTTP_STATUS_UNAUTHORIZED_401 = 401;
    public static final int HTTP_STATUS_NOT_FOUND_404 = 404;
    public static final int HTTP_STATUS_ERROR_500 = 500;

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    public static final String APPLICATION_JSON = "application/json";
    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String HTTP_METHOD_PUT = "PUT";
    public static final String HTTP_METHOD_DELETE = "DELETE";
    public static final String HEADER_CLIENT = "Client";

    @LocalServerPort
    private int port;
    private String baseEndpoint;
    private String greetingEndpoint;

    private static HttpClient httpClient;
    private static long TIMEOUT_DEFAULT_SECONDS = 10;

    @BeforeEach
    public void setUp () {
        baseEndpoint =  "http://localhost:" + port ;
        greetingEndpoint =  "http://localhost:" + port + "/greeting";
    }

    @BeforeAll
    public static void setUpClass () {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).followRedirects(HttpClient.Redirect.NORMAL).build();
    }

    //example with assertJ library
    @Test
    public void greetingShouldReturnDefaultMessageAssertJ() throws Exception {
        //Given
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(greetingEndpoint)).method(HTTP_METHOD_GET, HttpRequest.BodyPublishers.ofString(""))
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .timeout(Duration.ofSeconds(TIMEOUT_DEFAULT_SECONDS)).build();


        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //Then
        assertThat(response.body()).contains("Hello, World");
    }

    //example with standard JUnit assertions
    @Test
    public void greetingShouldReturnDefaultMessageJunit() throws Exception {

        //Given
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(greetingEndpoint)).method(HTTP_METHOD_GET, HttpRequest.BodyPublishers.ofString(""))
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .timeout(Duration.ofSeconds(TIMEOUT_DEFAULT_SECONDS)).build();


        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //Then
        Assertions.assertTrue(response.body().contains("Hello, World"), "The response from the service does not contain expected Hello, World");
    }

    

}

