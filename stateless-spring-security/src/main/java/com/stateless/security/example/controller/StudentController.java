package com.stateless.security.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {

    @GetMapping("/{student-id}")
    public Integer getStudent(@PathVariable("student-id") Integer studentId){
        return studentId;
    }

}
