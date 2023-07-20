package com.stateless.security.example.controller;

import com.stateless.security.example.annotation.InstructorOnly;
import com.stateless.security.example.annotation.StudentOnly;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/courses")
public class CourseRestController {

    @GetMapping("/{course-id}")
    @InstructorOnly
    public Integer createCourse(@PathVariable("course-id") Integer courseId){
        return courseId;
    }


}
