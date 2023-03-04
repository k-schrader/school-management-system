package jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
//import jpa.entitymodels.Student;

public class CourseService implements CourseDAO{
	//Create session to establish connection to DB
	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	final Session session = factory.openSession();
	
	//Method to retrieve all courses with an HQL query 
	public List<Course> getAllCourses() {
		List<Course> allCourses = new ArrayList<Course>();
		Query<Course> query = session.createQuery("from Course", Course.class);
		allCourses.addAll(query.getResultList());
		return allCourses;
	}
	//Method to retrieve course by ID with an HQL query 
	public List<Course> GetCourseById(int number) {
		List<Course> lc = new ArrayList<Course>();
		Query<?> query = session.createQuery("from Course c where c.cId = :cId", Course.class);
		query.setParameter("cId", number);
		lc = (List<Course>) query.getResultList();
		return  lc;
	}
}
