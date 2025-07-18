package com.microservices.user.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.microservices.user.entity.TtCourse;

@Repository
public class CourseRepositoryImpl implements CourseRepository{
	@Autowired
    @Qualifier("mysqlSessionFactory")
    private SessionFactory mysqlSessionFactory;

	@Override
	public Integer saveCourseDetails(TtCourse ttCourse) {
		 Session session = mysqlSessionFactory.getCurrentSession();
		 Integer courseId =null;
	        try {
	        	courseId= (Integer)session.save(ttCourse);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return courseId;
	}
	
	
	public List<Object[]> getCourseDetails() {
	    Session session = mysqlSessionFactory.getCurrentSession();
	    String sql = "SELECT courseId, course_description, course_name, course_title, course_url " +
	                 "FROM tt_course tc where active_course ='YES' ";   
	    Query query = session.createNativeQuery(sql);
	    return query.getResultList();
	}
	
	
	public TtCourse getCourseDetailsById(Integer courseId) {
	    Session session = mysqlSessionFactory.getCurrentSession();
	    String sql = "SELECT * FROM tt_course tc where active_course ='YES' and courseId=:courseId ";   
	    Query query = session.createNativeQuery(sql,TtCourse.class).setParameter("courseId", courseId);
	    return (TtCourse) query.getSingleResult();
	}



	
	
}
