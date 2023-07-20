package com.stateless.security.example.controller;

import com.stateless.security.example.annotation.StudentOnly;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/students")
public class StudentRestController {

    @GetMapping("/{student-id}")
    @StudentOnly
    public Integer getStudent(@PathVariable("student-id") Integer studentId){
        return studentId;
    }

}
