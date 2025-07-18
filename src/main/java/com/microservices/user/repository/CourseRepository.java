package com.microservices.user.repository;

import java.util.List;

import com.microservices.user.entity.TtCourse;

public interface CourseRepository {
	public Integer saveCourseDetails(TtCourse ttCourse);
	public List<Object[]> getCourseDetails();
	public TtCourse getCourseDetailsById(Integer courseId);
}
