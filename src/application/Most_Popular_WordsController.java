package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Most_Popular_WordsController extends BorderPane {
	
	@FXML public ScrollPane websitesPane;
	@FXML public Button addButton;

	@FXML
	void initialize() {}
	
	public void handleAddButton() {
		VBox websiteVBox = new VBox();
		TextField websiteNameTF = new TextField();
		TextField websiteSearchCriteriaTF = new TextField();
		Button deleteButton = new Button();
		
	}
}