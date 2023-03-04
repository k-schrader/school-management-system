package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;

public interface CourseDAO {
	
	//DAO interface to set abstract methods that are expected 
	public List<Course> getAllCourses();

}
