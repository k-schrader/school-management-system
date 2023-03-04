package jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import org.hibernate.Transaction;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {
	//Create session to establish connection to DB
	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	Session session = factory.openSession();
	
	//Method to retrieve a student by their email using HQL
	public List<Student> getStudentByEmail(String email) {
		List<Student> studentByEmail = new ArrayList<Student>();
		Query q = session.createQuery("from Student s where s.sEmail = :email", Student.class);
		q.setParameter("email", email);
		studentByEmail = q.getResultList();
		return studentByEmail;
	}
	//Method to retrieve all students using HQL
	public List<Student> getAllStudents() {
		List<Student> allStudents = new ArrayList<Student>();
		Query<Student> query = session.createQuery("from Student", Student.class);
		allStudents.addAll(query.getResultList());
		return allStudents;
	}
	//Unused method that the rubric document asked for 
	public boolean validateStudent(String email, String password) {
		boolean login = false;
		String query = "Select email, password from Student where email = :email AND password = :password";
		Query q = session.createQuery(query, Student.class);
		q.setParameter("email", email);
		q.setParameter("password", password);
		if (q.getSingleResult() != null) {
			login = true;
		}
		return login;
	}
	//Method to get courses from a student by email using HQL
	public List<Course> getStudentCourses(String email) {
		Student student = getStudentByEmail(email).get(0);
		return student.getcourse();
	}
	
	/*Method to register a student to a course 
	Checks that a student is not already registered
	Commits the values of the students email and the courses id 
	to the joined table*/
	public void registerStudentToCourse(Object object, Course course) {
			Transaction t = session.beginTransaction();
			Query<?> qry = session.createQuery("FROM Student s WHERE s.sEmail=?1",null);
			Student stud1 = (Student) qry.setParameter(1, object).getSingleResult();
			if(!stud1.getcourse().contains(course)) {
			String hqlInsert = "insert into student_course (email, id) VALUES (?,?)";
			session.createNativeQuery(hqlInsert, Course.class).setParameter(1, object).setParameter(2, course.getcId())
					.executeUpdate();
			
			t.commit();
			
			System.out.println(stud1.getsName() + " is now registered to course: " + course.getcName());
			} else {
				System.out.println("You are already registered to this course.");
			}
	}
}
