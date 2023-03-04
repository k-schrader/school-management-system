package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public interface StudentDAO {
	
	//DAO interface to set abstract methods that are expected 
	public List<Student> getAllStudents();
	public List<Student> getStudentByEmail(String email);
	public boolean validateStudent(String email, String password);
	public void registerStudentToCourse(Object object, Course course);
	public List<Course> getStudentCourses(String email);

}
