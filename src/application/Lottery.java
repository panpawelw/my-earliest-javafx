package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lottery extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Lottery");
		VBox root = new VBox(4);
		GridPane pane = new GridPane();

		// StackPane root = new StackPane();
		Label upperlab = new Label("upper");
		Label middlelab = new Label("middle");
		Label lowerlab = new Label("lower");
		// TextField userInputTextField = new TextField();
		// userInputTextField.setMinWidth(100);
		// final ToggleGroup group = new ToggleGroup();
		ToggleButton[] toggleButtons = new ToggleButton[49];
		for (int i = 0; i < toggleButtons.length; i++) {
			toggleButtons[i] = new ToggleButton("" + (i + 1));
			toggleButtons[i].setPrefWidth(30);
		}
		Button button = new Button("X");
		button.setPrefWidth(30);
		button.setStyle("-fx-background-color: Red");
		// ToggleButton tb1 = new ToggleButton("1");
		// tb1.setToggleGroup(group);
		// tb1.setSelected(true);
		// ToggleButton tb2 = new ToggleButton("2");
		// tb2.setToggleGroup(group);
		// ToggleButton tb3 = new ToggleButton("3");
		// tb3.setToggleGroup(group);
		// EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
		// @Override
		// public void handle(ActionEvent event) {}
		// };
		// userInputTextField.setOnAction(handler);
		// button.setOnAction(handler);
		root.getChildren().add(upperlab);
		root.getChildren().add(middlelab);
		root.getChildren().add(lowerlab);
		// rows.getChildren().add(userInputTextField);
		// rows.getChildren().add(button);
		// root.getChildren().add(rows);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 10; j++) {
				pane.add(toggleButtons[i * 10 + j], j, i);
			}
		}
		for (int i = 0; i < 9; i++) {
			pane.add(toggleButtons[40 + i], i, 4);
		}
		pane.add(button, 9, 4);
		root.getChildren().add(pane);

		primaryStage.setScene(new Scene(root, 300, 190));
		primaryStage.show();
	}
}