package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

//Mapping annotations to create table in DB
@Entity
@Table
public class Course {
	
//Mapping annotations and variable declarations
	//cId annotated to be auto generated and the PK
	//columns renamed and not null constraint added
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "Id")
private int cId;
@Column(nullable=false, name="Name")
private String cName;
@Column(nullable=false, name="Instructor")
private String cInstructorName;

//mappedBy to specify that course does not own the relationship
//prevents 2 join tables from being created 
@ManyToMany(mappedBy = "course")
private List<Student> student = new ArrayList<Student>();


//Constructors 
public Course(String cName, String cInstructorName) {
	this.cName = cName;
	this.cInstructorName = cInstructorName;
}
public Course() {
	this.cId=0;
	this.cName="";
	this.cInstructorName="";
}

//Getters and Setters 
public int getcId() {
	return cId;
}
public void setcId(int cId) {
	this.cId = cId;
}
public String getcName() {
	return cName;
}
public void setcName(String cName) {
	this.cName = cName;
}
public String getcInstructorName() {
	return cInstructorName;
}
public void setcInstructorName(String cInstructorName) {
	this.cInstructorName = cInstructorName;
}

//Overridden toString method for testing 
@Override
public String toString() {
	return  "Course ID: " +cId + " " + cName + " -" + cInstructorName;
}

//Overridden hashCode method for testing 
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + cId;
	result = prime * result + ((cInstructorName == null) ? 0 : cInstructorName.hashCode());
	result = prime * result + ((cName == null) ? 0 : cName.hashCode());
	return result;
}

//Overridden equals method for testing
@Override
public boolean equals(Object obj) {
    if (obj == this) {
        return true;
    }
    if (!(obj instanceof Course)) {
        return false;
    }
    Course other = (Course) obj;
    return Objects.equals(this.getcId(), other.getcId()) &&
           Objects.equals(this.getcName(), other.getcName()) &&
           Objects.equals(this.getcInstructorName(), other.getcInstructorName());
}

}
