package com.perscholas.SMS;

import static java.lang.System.out;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentCourseService;
import jpa.service.StudentService;

/**
 * 1
 * 
 * @author Harry
 *
 */
public class SMSRunner {

	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private StudentCourseService scService; 
	private Student currentStudent;

	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
		scService = new StudentCourseService();
	}

	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		sms.run();
	}

	private void run() {
		// Login or quit
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			} 
			break;
		case 2:
			out.println("Goodbye!");
			break;
		case 3:
			System.out.println("Please enter a valid number");
		default:
		}
	}

	private int menu1() throws InputMismatchException{
		try{sb.append("\n1.Student Login\n2.Quit Application\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
		}catch(Exception e) {
		}return 3;
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		List<Student> students = studentService.getStudentByEmail(email);
		if (students.size()!=0) {
			currentStudent = students.get(0);
		}else {
			System.out.println("Wrong Credentials!");
		return retValue;
		}

		if (students.size()!=0 && currentStudent.getStudentPassword().equals(password)) {
			List<Course> courses = studentService.getStudentCourses(email);
			out.println("My Classes");
			if(courses.size()==0) {
				System.out.println("You are not registered to any courses yet!");
			}
			else{for (Course course : courses) {
				
			
				out.println(course);
			}
			}
			retValue = true;
		} else {
			out.println("Wrong Credentials!");
		}
		return retValue;
	}

	private void registerMenu() throws InputMismatchException{
		try{sb.append("\n1.Register a class\n2.Logout\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		switch (sin.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();
			List<Course> studentCourses = scService.getAllStudentCourses(currentStudent.getStudentEmail());
			allCourses.removeAll(studentCourses);
			out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
			for (Course course : allCourses) {
				out.println(course);
			}
			out.println();
			out.print("Enter Course Number: ");
			int number = sin.nextInt();
			Course newCourse = courseService.GetCourseById(number).get(0);

			if (newCourse != null) {
				Student temp = new Student();
				if(!scService.isInCourse(newCourse, currentStudent.getStudentEmail())) {
				studentService.registerStudentToCourse(currentStudent.getStudentEmail(), newCourse);
				temp = studentService.getStudentByEmail((String) currentStudent.getStudentEmail()).get(0);
				}else {
					System.out.println("You are already registered for this course!");
				}
				List<Course> sCourses = scService.getAllStudentCourses(temp.getStudentEmail());
				

				out.println("My Classes:");
				for (Course course : sCourses) {
					out.println(course);
				}
			}
			registerMenu();
			break;
		case 2:out.println("Goodbye!");
		default:
		}
		}catch (Exception e) {
			System.out.println("Please enter a valid number");
		}
		}
	}
