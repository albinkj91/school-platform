package school_platform_pages.teacher;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Student;

import java.util.ArrayList;
import java.util.List;

public class AttendanceTab extends Tab {

	private final BorderPane borderPane = new BorderPane();
	private final GridPane gridPane = new GridPane();
	private final List<CheckBox> checkBoxes = new ArrayList<>();

	private List<Student> students;

	public AttendanceTab(List<Student> students){
		this.students = students;
		setText("Närvaro");
	}


	public void initialize(){
		setAttendanceTab();
		setGridPane();
		setCheckBoxes();
	}

	private void setAttendanceTab(){
		borderPane.setCenter(gridPane);
		setContent(borderPane);
	}

	private void setGridPane(){
		gridPane.setHgap(20);
		gridPane.setVgap(20);
		gridPane.setPadding(new Insets(40));
		gridPane.getStyleClass().add("grid-pane");
	}

	private void setCheckBoxes(){
		int checkBoxIndex = 0;
		for (int i = 0; i < students.size(); i++) {
			checkBoxes.add(new CheckBox(students.get(i).getName()));
			if(i % 2 == 0){
				gridPane.add(checkBoxes.get(i), 0, checkBoxIndex);
			}else {
				gridPane.add(checkBoxes.get(i), 1, checkBoxIndex++);
			}
		}
	}
}
