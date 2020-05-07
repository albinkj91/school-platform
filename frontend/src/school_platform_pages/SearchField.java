package school_platform_pages;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchField extends TextField {

	private final VBox root;
	private final ContextMenu contextMenu = new ContextMenu();
	private final List<MenuItem> menuItems = new LinkedList<>();

	public SearchField(VBox root){
		this.root = root;
	}

	public void setSearch(List<Person> personList, String filterType){
		root.getChildren().add(this);
		personList = personList.stream()
				.filter(p -> p.getType().equals(filterType))
				.collect(Collectors.toList());

		personList.forEach(p -> {
			MenuItem item = new MenuItem(p.getName());
			menuItems.add(item);
		});

		this.setContextMenu(contextMenu);

		textProperty().addListener(observable -> {
			contextMenu.getItems().clear();
			contextMenu.getItems().addAll(menuItems);
			contextMenu.getItems().removeAll(
					contextMenu.getItems()
							.stream()
							.filter(i -> !i.getText().toLowerCase().contains(getText().toLowerCase()))
							.collect(Collectors.toList()));

			if (!contextMenu.isShowing() && !getText().equals("")) {
				contextMenu.show(this, Side.BOTTOM, 0, 1);
			}else if(getText().equals("")){
				contextMenu.hide();
			}
		});
	}

	public List<MenuItem> getMenuItems(){
		return menuItems;
	}
}
