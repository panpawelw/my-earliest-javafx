package application;
	
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
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
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}