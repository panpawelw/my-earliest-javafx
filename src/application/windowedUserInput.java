package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class windowedUserInput extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		StackPane root = new StackPane();
		
		VBox rows = new VBox(3);
		
        primaryStage.setTitle("Simple Button");
        
        Label lab = new Label("Miejsce na twoj napis");
        
        TextField userInputTextField = new TextField();
        userInputTextField.setMinWidth(235);
        
        Button button = new Button();
        button.setText("Show input in label");
        button.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                lab.setText(userInputTextField.getText());
            }
            
        });
        
        rows.getChildren().add(userInputTextField);
        rows.getChildren().add(button);
        rows.getChildren().add(lab);
        
        root.getChildren().add(rows);
        
        primaryStage.setScene(new Scene(root, 300, 250));
        
        primaryStage.show();
		
	}
}