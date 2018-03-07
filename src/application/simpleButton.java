package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class simpleButton extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		StackPane root = new StackPane();
		
		VBox table = new VBox(2);
		
		HBox row = new HBox(2);
		
        primaryStage.setTitle("Simple Button");
        
        Button btnPrime = new Button();
        btnPrime.setText("Let show something on console");
        btnPrime.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ala ma kota");
            }
            
        });
        
        
        row.getChildren().add(btnPrime);
        
        
        Label lab = new Label("Miejsce na twoj napis");
        
        Button btnSecundo = new Button();
        btnSecundo.setText("Let show something on label");
        btnSecundo.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                lab.setText("Kupuj!!!!!!!!!");
            }
            
        });
        
        
        row.getChildren().add(btnSecundo);
        
        table.getChildren().add(row);
        
        table.getChildren().add(lab);
        
        root.getChildren().add(table);
        
        primaryStage.setScene(new Scene(root, 600, 250));
        
        primaryStage.show();
		
	}
}