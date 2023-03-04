package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

//Mapping annotation to create tables in DB
@Entity
@Table(name = "Student")
public class Student {
	//Establish ManyToMany relationship
	//Cascade all to let all operations happen to both entities 
	@ManyToMany(cascade = CascadeType.ALL)
	//custom names for JoinTables and JoinColumns
	@JoinTable(name = "Student_Course", joinColumns = { @JoinColumn(name = "email") }, inverseJoinColumns = {
			@JoinColumn(name = "id") })
	List<Course> course = new ArrayList<Course>();
	
	//@Id to make sEmail PK, added constraints and names 
	//to all columns
	@Id
	@Column(nullable = false, length = 50, name = "Email", unique = true)
	private String sEmail;
	@Column(nullable = false, length = 50, name = "Name")
	private String sName;
	@Column(nullable = false, length = 50, name = "Password")
	private String sPass;

	// Constructors
	public Student(String sEmail, String sName, String sPass, List<Course> course) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.course = course;
	}
	public Student() {
		this.sEmail = "";
		this.sName = "";
		this.sPass = "";
		this.course = new ArrayList<Course>();
	}

	// Getters and Setters
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getStudentPassword() {
		return sPass;
	}

	public String getStudentEmail() {
		return sEmail;

	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<Course> getcourse() {
		return course;
	}

	public void setcourse(List<Course> course) {
		this.course = course;
	}

	public void addCourse(Course c) {
		course.add(c);
	}

	// Overridden toString method for testing
	@Override
	public String toString() {
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPass=" + sPass + ", course=" + course + "]";
	}

	// Overridden hashCode method for testing
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((sEmail == null) ? 0 : sEmail.hashCode());
		result = prime * result + ((sName == null) ? 0 : sName.hashCode());
		result = prime * result + ((sPass == null) ? 0 : sPass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.toString().trim().equals(obj.toString().trim())) {
			return true;
		}
		if (obj instanceof Student) {
			Student other = (Student) obj;
			boolean sameCourse =(this.course==other.getcourse());
			boolean sameEmail=(this.sEmail==other.getStudentEmail());
			boolean sameName=(this.sName==other.getsName());
			boolean samePassword=(this.sPass==other.getStudentPassword());
			if(sameCourse && sameEmail && sameName && samePassword)
				return true;
		}
		return false;

	}

}

