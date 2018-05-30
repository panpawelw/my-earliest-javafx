package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Numbers_Guessing2 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
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
				int min = (int) upperLab.getUserData();
				int max = (int) middleLowerLab.getUserData();
				middleUpperLab.setUserData(max);
				int guess = ((max-min)/2)+min;
				middleLowerLab.setUserData(guess);
				middleLowerLab.setText("Guess: " + guess);
			}
		};
		
		EventHandler<ActionEvent> correctHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				lowerLab.setText("I WON!!!");
			}
		};
		
		EventHandler<ActionEvent> notEnoughHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int min = (int) middleLowerLab.getUserData();
				int max = (int) middleUpperLab.getUserData();
				upperLab.setUserData(min);
				int guess = ((max-min)/2)+min;
				middleLowerLab.setUserData(guess);
				middleLowerLab.setText("Guess: " + guess);
			}
		};
		
		EventHandler<ActionEvent> startHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				button1.setText("   TOO MUCH   ");
				button2.setText("  CORRECT!  ");
				button3.setText(" NOT ENOUGH ");
				button1.setOnAction(tooMuchHandler);
				button2.setOnAction(correctHandler);
				button3.setOnAction(notEnoughHandler);
				int min = 0;
				int max = 1000;
				int guess = 500;
				int attempt = 1;
				upperLab.setUserData(min);
				middleUpperLab.setUserData(max);
				middleLowerLab.setUserData(guess);
				lowerLab.setUserData(attempt);
				middleLowerLab.setText("Guess: " + middleLowerLab.getUserData());
				lowerLab.setText("Attempt: " + lowerLab.getUserData());

			}
		};
		
		button2.setOnAction(startHandler);
		root.getChildren().add(upperLab);
		root.getChildren().add(middleUpperLab);
		root.getChildren().add(middleLowerLab);
		root.getChildren().add(lowerLab);
		root.getChildren().add(tile);
		Scene scene = new Scene(root,300,130);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}