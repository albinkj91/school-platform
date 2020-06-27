package school_platform_pages.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Employee;
import models.Person;

import java.util.LinkedList;
import java.util.List;

public class EmployeeListingTab extends Tab {

	private final TableView<Employee> table = new TableView<>();
	private ObservableList<Employee> employees;

	public void setEmployees(ObservableList<Person> persons){
		this.employees = (ObservableList<Employee>) filterPersons(persons);
	}


	public void setEmployeeListingTab(){
		setText("Vuxna");
		setTable();
		this.setContent(table);
	}

	public void setTable() {
		table.setItems(employees);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Employee, Object> name = new TableColumn<>("Namn");
		TableColumn<Employee, Object> ssn = new TableColumn<>("Personnr");
		TableColumn<Employee, Object> type = new TableColumn<>("Typ");
		TableColumn<Employee, Object> email = new TableColumn<>("E-post");
		TableColumn<Employee, Object> phone = new TableColumn<>("Telefon");

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

		table.getColumns().add(name);
		table.getColumns().add(ssn);
		table.getColumns().add(type);
		table.getColumns().add(email);
		table.getColumns().add(phone);
	}

	private List<Employee> filterPersons(ObservableList<Person> persons){
		 List<Employee> employeeList = new LinkedList<>();
		 persons.forEach(p -> {
		 	if(!p.getType().equalsIgnoreCase("STUDENT")){
			 employeeList.add((Employee) p);
			}
		 });
		 return FXCollections.observableArrayList(employeeList);
	}

	public ObservableList<Employee> getEmployees() {
		return employees;
	}
}
