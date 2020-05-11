package school_platform_pages.teacher;

import javafx.scene.control.CheckBox;
import models.Student;

public class StudentCheckBox extends CheckBox {

	private Student student;

	public StudentCheckBox(Student student){
		this.student = student;
		setText(student.getName());
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
