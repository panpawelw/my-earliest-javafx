package application;
	
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Dice extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Dice throw simulator");
		
		EventHandler<ActionEvent> throwHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Random rand = new Random(); // dice throw and result output
				int multiplier = 0;
				int modifier = 0;
				int faces = 0;
				long finalResult = 0;
				for (int l = 1; l < multiplier; l++) {
					int result = rand.nextInt(faces) + 1;
					finalResult = finalResult + result;
				}
				finalResult = finalResult + modifier;
			}
		};
		
		GridPane root = new GridPane();
		Label label1 = new Label("Multiplier");
//		label1.setStyle("-fx-border-color: black");
		TextField multiplier = new TextField();
		multiplier.setPrefSize(50, 50);
		Button button1 = new Button("+");
		Button button2 = new Button("-");
		root.add(label1, 0, 0, 2, 1);
		root.add(multiplier, 0, 1);
		root.add(button1, 0, 2);
		root.add(button2, 1, 2);
		GridPane.setColumnSpan(label1, 2);
		GridPane.setHalignment(label1, HPos.CENTER);
		GridPane.setColumnSpan(multiplier, 2);
		
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}