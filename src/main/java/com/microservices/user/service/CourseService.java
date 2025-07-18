package com.microservices.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microservices.user.entity.TtCourse;

public interface CourseService {
	public Integer saveCourseDetails(TtCourse ttCourse);
	 public List<Map<String,Object>>  getCourseDetails();
	 public HashMap<String,String> getCourseDetailsById(Integer courseId);

}
