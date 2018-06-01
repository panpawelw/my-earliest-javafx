package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Numbers_Guessing2 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Guess Numbers #2");
		VBox root = new VBox(5);
		Label upperLab = new Label("Think of a number between 0 and 1000.");
		Label middleUpperLab = new Label("I bet I can guess it in no more than 10 attempts!");
		Label middleLowerLab = new Label();
		Label lowerLab = new Label();
		TilePane tile = new TilePane();
		tile.setPrefColumns(3);
		Button button1 = new Button("                     ");
		Button button2 = new Button("        START!      ");
		Button button3 = new Button("                     ");
		root.setAlignment(Pos.CENTER);
		tile.getChildren().add(button1);
		tile.getChildren().add(button2);
		tile.getChildren().add(button3);

		EventHandler<ActionEvent> tooMuchHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (nextAttempt(lowerLab) > 10) {
					waitForExit(button1, button2, button3, lowerLab, "I lost...just kidding, you liar!");
				} else {
					middleUpperLab.setUserData((int) middleLowerLab.getUserData());
					makeGuess(middleLowerLab, (int) upperLab.getUserData(), (int) middleLowerLab.getUserData());
				}
			}
		};

		EventHandler<ActionEvent> correctHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				waitForExit(button1, button2, button3, lowerLab, "I WON!");
			}
		};

		EventHandler<ActionEvent> notEnoughHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (nextAttempt(lowerLab) > 10) {
					waitForExit(button1, button2, button3, lowerLab, "I lost...just kidding, you liar!");
				} else {
					upperLab.setUserData((int) middleLowerLab.getUserData());
					makeGuess(middleLowerLab, (int) middleLowerLab.getUserData(), (int) middleUpperLab.getUserData());
				}
			}
		};

		EventHandler<ActionEvent> startHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				button2.setOnAction(null);
				setButtons(button1, "   TOO MUCH   ", button2, "   CORRECT!   ", button3, " NOT ENOUGH ");
				setButtonHandlers(button1, tooMuchHandler, button2, correctHandler, button3, notEnoughHandler);
				upperLab.setUserData(0);// set guess minimum
				middleUpperLab.setUserData(1000);// set guess maximum
				middleLowerLab.setUserData(500);// set current guess value
				lowerLab.setUserData(1);// set attempt number
				middleLowerLab.setText("Guess: " + middleLowerLab.getUserData());
				lowerLab.setText("Attempt: " + lowerLab.getUserData());
			}
		};

		setButtonHandlers(button1, null, button2, startHandler, button3, null);
		button2.setOnAction(startHandler);
		root.getChildren().add(upperLab);
		root.getChildren().add(middleUpperLab);
		root.getChildren().add(middleLowerLab);
		root.getChildren().add(lowerLab);
		root.getChildren().add(tile);
		Scene scene = new Scene(root, 300, 130);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	static void setButtons(Button button1, String string1, Button button2, String string2, Button button3, String string3) {
		button1.setText(string1);
		button2.setText(string2);
		button3.setText(string3);
	}
	
	static void setButtonHandlers(Button b1, EventHandler<ActionEvent> b1Hr,
			Button b2, EventHandler<ActionEvent> b2Hr, Button b3, EventHandler<ActionEvent> b3Hr) {
		b1.setOnAction(b1Hr);
		b2.setOnAction(b2Hr);
		b3.setOnAction(b3Hr);
	}
	
	static void waitForExit(Button button1, Button button2, Button button3, Label label, String string) {
		label.setText(string);
		setButtonHandlers(button1, null, button2, e -> Platform.exit(), button3, null);
		setButtons(button1, "                         ", button2, "         EXIT!        ", button3, "                         ");
	}

	static int nextAttempt(Label label) {
		int attempt = (int) label.getUserData();
		attempt++;
		label.setUserData(attempt);
		label.setText("Attempt: " + attempt);
		return attempt;
	}
	
	static void makeGuess(Label label, int min, int max) {
		int guess = ((max - min) / 2) + min;
		label.setUserData(guess);
		label.setText("Guess: " + guess);
	}
}