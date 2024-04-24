package com.example.restservice;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestTools {

    public static void debugHttpRequest(org.slf4j.Logger log, HttpRequest request, String body) {
        //debug
        log.debug("-------------");
        log.debug("Sending HttpRequest: " + request.method() + " " + request.uri() + ", headers: " + request.headers() +
                ", body: " + body);
        //debug
    }

    public static void debugHttpResponse(org.slf4j.Logger log, HttpResponse response) {
        //debug
        log.debug("Received HttpResponse: " + response.statusCode() + " " + response.body());
        log.debug("-------------");
        //debug
    }

}
