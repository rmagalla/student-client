package com.example.studentclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/")
public class StudentService {

  @Autowired
  StudentServiceDelegate studentServiceDelegate;

  @GetMapping(value = "/studentService/{id}")
  public String getStudentService(@PathVariable String id) {
    System.out.println("Call Service Student...");
    return studentServiceDelegate.callStudentService(id);
  }
}
