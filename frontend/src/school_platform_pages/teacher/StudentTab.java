package school_platform_pages.teacher;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Student;

import java.util.List;

public class StudentTab extends Tab {

	private final BorderPane borderPane = new BorderPane();
	private final TableView<Student> table = new TableView<>();

	private final List<Student> students;

	public StudentTab(List<Student> students){
		this.students = students;
		setText("Elever");
	}


	public void initialize(){
		setStudentTab();
		setTable();
	}

	private void setStudentTab(){
		setClosable(false);
		borderPane.setLeft(table);
		setContent(borderPane);
	}

	private void setTable(){
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getItems().addAll(students);
		TableColumn<Student, String> name = new TableColumn<>("Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		table.getColumns().add(name);

		table.getSelectionModel().selectedItemProperty().addListener(l -> {
			System.out.println(table.getSelectionModel().getSelectedItem().getName());
		});
	}
}
