package com.example.studentclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class StudentServiceDelegate {

  @Autowired
  RestTemplate restTemplate;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @HystrixCommand(fallbackMethod = "callStudentServiceFallback")
  String callStudentService(String id) {
    System.out.println("Call Student Service with id: " + id);

    String response = restTemplate
        .exchange("http://student-service/student/{id}", HttpMethod.GET, null,
            new ParameterizedTypeReference<String>() {
            }, id).getBody();

    System.out.println("Response received: " + response + " - " + new Date());
    return "OK - " + response + " - " + new Date();
  }

  private String callStudentServiceFallback(String id) {
    System.out.println("Call Student Service Fallback with id: " + id);
    return "KO - Fallback" + " - " + new Date();
  }

}
