package jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import jpa.entitymodels.*;

public class StudentCourseService {
	//Create session to establish connection to DB
	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	Session session = factory.openSession();
	//Create new service objects 
	StudentService ss = new StudentService();
	CourseService cs = new CourseService();

	//Method to get all courses belonging to a student
	public List<Course> getAllStudentCourses(String email) {
		List<Course> allStudentCourses =null;
		Query<?> query = session.createQuery(
				"SELECT c FROM Course c INNER JOIN c.student s WHERE s.sEmail = :email",null);
		allStudentCourses = (List<Course>) query.setParameter("email", email).getResultList();
		return allStudentCourses;
	}

	//Method to check if a student is already registered to a course 
	public boolean isInCourse(Course newCourse, String email) {
		boolean result = false;
		List<Course> nl = getAllStudentCourses(email);
			if(nl.contains(newCourse)) {
				result=true;
			}
		return result;
	}

}
