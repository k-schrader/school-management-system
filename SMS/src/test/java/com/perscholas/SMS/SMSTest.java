package com.perscholas.SMS;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;


public class SMSTest {
	Course c1;
	CourseService cs;
	StudentService ss;
	Student s1;
	List<Course> course;
	
	@Before
	public void setUp() throws Exception {
		course = new ArrayList<Course>();
		s1 = new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj", course);
		c1 = new Course ("Art", "Kingsly Doxsey");
		c1.setcId(10);
		cs = new CourseService();
		ss = new StudentService();
	}
		@Test
		public void getCourseByID() {
		    Course actual = cs.GetCourseById(c1.getcId()).get(0);
		    assertEquals(c1, actual);
		}
		@Test
		public void testgetStudentByEmail() {
			assertEquals(s1, ss.getStudentByEmail(s1.getStudentEmail()).get(0));
		}

	}





