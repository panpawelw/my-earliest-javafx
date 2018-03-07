package application;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Numbers_Guessing1 extends Application {

	static int numberToGuess;

	public static void main(String[] args) {

		Random random = new Random();
		numberToGuess = random.nextInt(101);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		StackPane root = new StackPane();

		VBox rows = new VBox(4);

		primaryStage.setTitle("Guess Numbers #1");

		Label upperlab = new Label("Enter a number between 1 and 100:");
		Label lowerlab = new Label("...");

		TextField userInputTextField = new TextField();
		userInputTextField.setMinWidth(100);

		Button button = new Button();
		button.setText("Enter number");

		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {												 // Local class to handle both identical event handlers
			
			@Override
			public void handle(ActionEvent event) {
				String guess = userInputTextField.getText();
				String answer = "";
				int numericGuess = 0;
				try {
					numericGuess = Integer.parseInt(guess);
				} catch (NumberFormatException e) {
					answer = "is not a number, try again!";
				}
				if (numericGuess < 1 || numericGuess > 100) {
					answer = "is outside the given range!";
				}
				if (numericGuess < numberToGuess) {
					answer = "is too little!";
				} else if (numericGuess > numberToGuess) {
					answer = "is too much!";
				} else {
					upperlab.setText("!!!!!!!!!!!SPOT ON!!!!!!!!!!!!");
					button.setText("QUIT");
					button.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							Platform.exit();
						}
					});
				}
				lowerlab.setText(guess + " " + answer);
				userInputTextField.clear();
			}
		}; 																	// The end

		userInputTextField.setOnAction(handler);

		button.setOnAction(handler);

		rows.getChildren().add(upperlab);
		rows.getChildren().add(userInputTextField);
		rows.getChildren().add(button);
		rows.getChildren().add(lowerlab);

		root.getChildren().add(rows);

		primaryStage.setScene(new Scene(root, 250, 100));

		primaryStage.show();

	}
}