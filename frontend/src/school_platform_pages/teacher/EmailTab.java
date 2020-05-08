package school_platform_pages.teacher;

import javafx.scene.control.Tab;

public class EmailTab extends Tab {



	public EmailTab(){
		setText("E-mail");
	}

	public void initialize(){
		setEmailTab();
	}

	private void setEmailTab(){
		setClosable(false);
	}
}
