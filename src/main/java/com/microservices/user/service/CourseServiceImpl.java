package com.microservices.user.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.user.entity.TtCourse;
import com.microservices.user.entity.Users;
import com.microservices.user.repository.CourseRepository;
import com.microservices.user.repository.UserRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
    @Transactional("mysqlTransactionManager")
	public Integer saveCourseDetails(TtCourse ttCourse) {
    	return courseRepository.saveCourseDetails(ttCourse);
	}
    
    @Transactional("mysqlTransactionManager")
    public List<Map<String,Object>> getCourseDetails(){
	    List<Map<String, Object>> courses = new ArrayList<>();
	   List<Object[]> listOfCourses =  courseRepository.getCourseDetails();
	    
	    for(Object[] listOfCourses1:listOfCourses) {
	    	Map<String, Object> course1 = new HashMap<>();
	    	course1.putIfAbsent("courseId",listOfCourses1[0]);
		    course1.put("title", listOfCourses1[3]);
		    course1.put("description", listOfCourses1[1]);
		    course1.put("courseUrl", listOfCourses1[4]);
		    courses.add(course1);
	    }	    
	    return courses;
    }

	@Override
	@Transactional("mysqlTransactionManager")
	public HashMap<String, String> getCourseDetailsById(Integer courseId) {
		TtCourse tc = courseRepository.getCourseDetailsById(courseId);
		HashMap<String,String> data = new HashMap<String,String>();
		if(tc!=null) {
			data.put("courseId", tc.getCourseId().toString());
			data.put("courseName", tc.getCourseName());
			data.put("courseTitle", tc.getCourseTitle());
			data.put("courseUrl", tc.getCourseUrl());
			data.put("activeCourseFlag", tc.getActiveCourse());
		}
		return null;
	}
    
 

}
