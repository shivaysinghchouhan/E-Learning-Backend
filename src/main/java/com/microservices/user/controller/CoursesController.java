package com.microservices.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservices.user.entity.TtCourse;
import com.microservices.user.service.CourseService;

@RestController
@RequestMapping("/course/apis") 
public class CoursesController {

    @Autowired
    private CourseService courseService;

    /**
     * Get all courses (fetch from DB)
     */
    @GetMapping(value = "/getCourses", produces = "application/json")
    public List<Map<String,Object>> getCourses() {
        //List<TtCourse> courses = null;//courseService.getAllCourses(); // ✅ Fetch from DB
        List<Map<String,Object>> courses= courseService.getCourseDetails();
        return courses; // Return JSON response
    }

    /**
     * Save a new course
     */
    @PostMapping(value = "/saveCourseDetails", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveCourseDetails(@RequestBody TtCourse ttCourse) {
        Integer courseId = courseService.saveCourseDetails(ttCourse);

        if (courseId != null) {
            return ResponseEntity.status(200).body("Course saved successfully with ID: " + courseId);
        } else {
            return ResponseEntity.status(500).body("Failed to save course");
        }
    }
    
    
    @GetMapping(value = "/getCoursesById", produces = "application/json")
    public List<Map<String,Object>> getCoursesById(@RequestParam("courseId")Integer courseId) {
        HashMap<String,String> courses= courseService.getCourseDetailsById(courseId);
        return courses; // Return JSON response
    }
}
