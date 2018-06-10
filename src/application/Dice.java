package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Dice extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Dice throw simulator");
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Dice.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
//	
//import java.util.Random;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.HPos;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleButton;
//import javafx.scene.layout.GridPane;
//
//public class Dice extends Application {
//	@Override
//	public void start(Stage primaryStage) {
//		
//		
//		
//		
//		GridPane root = new GridPane();
//		Label label1 = new Label("Multiplier");
////		label1.setStyle("-fx-border-color: black");
//		TextField multiplier = new TextField();
//		multiplier.setPrefSize(50, 50);
//		Button button1 = new Button("+");
//		Button button2 = new Button("-");
//		GridPane diceType = new GridPane();
//		Label diceTypeDescription = new Label("Dice type");
//		ToggleButton d3 = new ToggleButton("d3");
//		ToggleButton d4 = new ToggleButton("d4");
//		ToggleButton d6 = new ToggleButton("d6");
//		ToggleButton d8 = new ToggleButton("d8");
//		ToggleButton d10 = new ToggleButton("d10");
//		ToggleButton d12 = new ToggleButton("d12");
//		ToggleButton d20 = new ToggleButton("d20");
//		ToggleButton d100 = new ToggleButton("d100");
//		root.add(label1, 0, 0, 2, 1);
//		root.add(multiplier, 0, 1);
//		root.add(button1, 0, 2);
//		root.add(button2, 1, 2);
//		GridPane.setColumnSpan(label1, 2);
//		GridPane.setHalignment(label1, HPos.CENTER);
//		GridPane.setColumnSpan(multiplier, 2);
//		diceType.add(diceTypeDescription, 0, 0);
//		root.add(diceType, 3, 0);
//		Scene scene = new Scene(root,400,400);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//}