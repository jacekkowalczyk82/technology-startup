package com.example.restservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpClientParametrizedTest {

    public static final Logger logger = LoggerFactory.getLogger(HttpClientParametrizedTest.class);
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
    void setUp(TestInfo testInfo) throws Exception {
        logger.debug("TestInfo:: Running Test: " + testInfo.getTestMethod().get().getName() + " with DisplayName: " + testInfo.getDisplayName());
        baseEndpoint =  "http://localhost:" + port ;
        greetingEndpoint =  "http://localhost:" + port + "/greeting";
    }

    @BeforeAll
    public static void setUpBeforeClass () {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).followRedirects(HttpClient.Redirect.NORMAL).build();
    }

    //example with standard JUnit assertions
    @ParameterizedTest
    @CsvSource({"Jacek", "Kowalczyk", "Testing"})
    public void greetingShouldReturnDefaultMessageJunitFailingExample(String name) throws Exception {

        //Given
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(greetingEndpoint)).method(HTTP_METHOD_GET, HttpRequest.BodyPublishers.ofString(""))
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .timeout(Duration.ofSeconds(TIMEOUT_DEFAULT_SECONDS)).build();

        TestTools.debugHttpRequest(logger, request, "");
        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        TestTools.debugHttpResponse(logger, response);

        //Then
        Assertions.assertTrue(response.body().contains("Hello, "+ name), "The response from the service does not contain expected Hello, "+ name);
    }


    //example with standard JUnit assertions
    @ParameterizedTest
    @CsvSource({"Jacek", "Kowalczyk", "Testing"})
    public void greetingShouldReturnDefaultMessageJunitFailingExampleMoreInfo(String name) throws Exception {

        //Given
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(greetingEndpoint)).method(HTTP_METHOD_GET, HttpRequest.BodyPublishers.ofString(""))
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .timeout(Duration.ofSeconds(TIMEOUT_DEFAULT_SECONDS)).build();

        TestTools.debugHttpRequest(logger, request, "");
        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        TestTools.debugHttpResponse(logger, response);

        //Then
        Assertions.assertTrue(response.body().contains("Hello, "+ name), "The response from the service does not contain expected Hello, "+ name + ", The actual response was: "+ response.body());
    }


    //example with standard JUnit assertions
    @ParameterizedTest
    @CsvSource({"Jacek", "Kowalczyk", "Testing"})
    public void greetingShouldReturnDefaultMessageJunitFixed(String name) throws Exception {

        //Given
        String paremterizedEndpoint = greetingEndpoint+ "?name=" + name;

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(paremterizedEndpoint)).method(HTTP_METHOD_GET, HttpRequest.BodyPublishers.ofString(""))
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .timeout(Duration.ofSeconds(TIMEOUT_DEFAULT_SECONDS)).build();

        TestTools.debugHttpRequest(logger, request, "");
        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        TestTools.debugHttpResponse(logger, response);

        //Then
        Assertions.assertTrue(response.body().contains("Hello, "+ name), "The response from the service does not contain expected Hello, "+ name);
    }

    

}

