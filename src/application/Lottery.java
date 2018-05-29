package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lottery extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {										// DECLARE BASIC SCENE ELEMENTS
		primaryStage.setTitle("Lottery");
		VBox root = new VBox(4);
		GridPane pane = new GridPane();
		Label upperlab = new Label("Select 6 more to Draw!");
		Label middlelab = new Label();
		Label lowerlab = new Label();
		root.setAlignment(Pos.CENTER);
		lowerlab.setUserData(new ArrayList<Integer>());
		ToggleButton[] toggleButtons = new ToggleButton[49];
		for (int i = 0; i < toggleButtons.length; i++) {
			toggleButtons[i] = new ToggleButton("" + (i + 1));
			toggleButtons[i].setPrefWidth(30);
		}
		Button button = new Button();
		button.setPrefWidth(30);
		button.setStyle("-fx-background-color: Black; -fx-text-fill: Orange; -fx-border-color: White;");
																				// TOGGLE BUTTONS HANDLER
		EventHandler<ActionEvent> handlerTBs = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				@SuppressWarnings("unchecked")
				ArrayList<Integer> alreadySelected = (ArrayList<Integer>) lowerlab.getUserData();
				Integer newSelection = Integer.parseInt(((ToggleButton) event.getSource()).getText());
				if (((ToggleButton) event.getSource()).isSelected()) {			// has the toggle button been pressed or de-pressed?
					if (alreadySelected.size() < 5) {							// how many buttons have been selected already?
						alreadySelected.add(newSelection);						// if less then 5, select a new one and add to the array list
						button.setText("");
						upperlab.setText("Select " + (6 - alreadySelected.size()) + " more to Draw!");
					} else if (alreadySelected.size() == 5) {					// if exactly 5 then select a next one and enable Draw button
						alreadySelected.add(newSelection);
						button.setText("D");
						upperlab.setText("Select D button to Draw!");
					} else if (alreadySelected.size() > 5) {					// more than five then don't allow any more toggle buttons
						((ToggleButton) event.getSource()).setSelected(false);	// to be pressed
						button.setText("D");
						upperlab.setText("Select D button to Draw!");
					}
				} else {
					while (alreadySelected.remove(newSelection)) {				// if toggle button is already selected then de-press it and
					}															// remove selection from the list
					button.setText("");
					upperlab.setText("Select " + (6 - alreadySelected.size()) + " more to Draw!");
				}
				Collections.sort(alreadySelected);								// sort the list
				lowerlab.setUserData(alreadySelected);							// store it as user-data in lower label element
				lowerlab.setText(alreadySelected.toString());					// and display it

			}
		};
																				// DRAW BUTTON HANDLER
		EventHandler<ActionEvent> handlerD = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int[] drawnNumbers = new int[6];
				@SuppressWarnings("unchecked")
				ArrayList<Integer> chosenNumbers = (ArrayList<Integer>) lowerlab.getUserData();
				Integer[] arr = new Integer[49];								// create an array of 49 consecutive numbers
				for (int i = 0; i < arr.length; i++) {
					arr[i] = i + 1;
				}
				Collections.shuffle(Arrays.asList(arr));						// shuffle it and draw first 6
				for (int i = 0; i < 6; i++) {
					drawnNumbers[i] = arr[i];
				}
				Arrays.sort(drawnNumbers);										// sort it and display it in upper label element
				upperlab.setText(Arrays.toString(drawnNumbers));
				int numberOfHits = 0;
				ArrayList<Integer> hits = new ArrayList<>();					// check for the number of hits
				for (int i = 0; i < 6; i++) {
					int currentNumber = chosenNumbers.get(i);
					for (int j = 0; j < 6; j++) {
						if (currentNumber == drawnNumbers[j]) {
							numberOfHits++;
							hits.add(currentNumber);
						}
					}
				}
				middlelab.setText(numberOfHits + " hits: " + hits.toString());	// display the hits in middle label
			}
		};
																				// ADD ELEMENTS, HANDLERS, SHOW SCENE
		for (int i = 0; i < 49; i++) {											// multiple handlers for toggle buttons
			toggleButtons[i].setOnAction(handlerTBs);
		}
		button.setOnAction(handlerD);
		root.getChildren().add(upperlab);
		root.getChildren().add(middlelab);
		root.getChildren().add(lowerlab);										// add toggle buttons to the grid
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
		
		Scene scene = new Scene(root, 300, 190);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // add CSS sheet
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}