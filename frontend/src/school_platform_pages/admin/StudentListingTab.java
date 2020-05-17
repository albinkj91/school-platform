package school_platform_pages.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Person;
import models.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentListingTab extends Tab {

	private final TableView<Student> table = new TableView<>();
	private ObservableList<Student> students;

	public void setEmployees(ObservableList<Person> persons){
		students = (ObservableList<Student>) filterPersons(persons);
	}


	public void setStudentListingTab(){
		setText("Elever");
		setTable();
		this.setContent(table);
	}

	public void setTable() {
		table.setItems(students);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Student, Object> name = new TableColumn<>("Namn");
		TableColumn<Student, Object> ssn = new TableColumn<>("Personnr");
		TableColumn<Student, Object> type = new TableColumn<>("Typ");

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));

		table.getColumns().add(name);
		table.getColumns().add(ssn);
		table.getColumns().add(type);
	}

	private List<Student> filterPersons(ObservableList<Person> persons){
		List<Student> studentList = new LinkedList<>();
		persons.forEach(p -> {
			if(p.getType().equalsIgnoreCase("STUDENT")){
				studentList.add(new Student(
						p.getName(),
						p.getSsn(),
						p.getType()));
			}
		});
		return FXCollections.observableArrayList(studentList);
	}

	public ObservableList<Student> getStudents() {
		return students;
	}
}
