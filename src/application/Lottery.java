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
	public void start(Stage primaryStage) {
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
		button.setStyle("-fx-background-color: Red");
		
		EventHandler<ActionEvent> handlerTBs = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				@SuppressWarnings("unchecked")
				ArrayList<Integer> alreadySelected = (ArrayList<Integer>) lowerlab.getUserData();
				Integer newSelection = Integer.parseInt(((ToggleButton)event.getSource()).getText());
				if(((ToggleButton)event.getSource()).isSelected()) {
					if(alreadySelected.size()<5) {
						alreadySelected.add(newSelection);
						button.setText("");
						upperlab.setText("Select " + (6 - alreadySelected.size()) + " more to Draw!");
					}else if(alreadySelected.size()==5) {
						alreadySelected.add(newSelection);
						button.setText("D");
						upperlab.setText("Select D button to Draw!");
					}else if(alreadySelected.size()>5) {
						((ToggleButton)event.getSource()).setSelected(false);
						button.setText("D");
						upperlab.setText("Select D button to Draw!");
					}
				}else {
					while(alreadySelected.remove(newSelection)) {}
					button.setText("");
					upperlab.setText("Select " + (6 - alreadySelected.size()) + " more to Draw!");
				}
				Collections.sort(alreadySelected);
				lowerlab.setUserData(alreadySelected);
				lowerlab.setText(alreadySelected.toString());
				
			}
		};
		
		EventHandler<ActionEvent> handlerD = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int[] drawnNumbers = new int[6];
				@SuppressWarnings("unchecked")
				ArrayList<Integer> chosenNumbers = (ArrayList<Integer>) lowerlab.getUserData();
				Integer[] arr = new Integer[49];
				for (int i = 0; i < arr.length; i++) {
					arr[i] = i + 1;
				}
				Collections.shuffle(Arrays.asList(arr));
				for (int i = 0; i < 6; i++) {
					drawnNumbers[i] = arr[i];
				}
				Arrays.sort(drawnNumbers);
				upperlab.setText(Arrays.toString(drawnNumbers));
				int numberOfHits = 0;
				ArrayList<Integer> hits = new ArrayList<>();
				for (int i = 0; i < 6; i++) {
					int currentNumber = chosenNumbers.get(i);
					for (int j = 0; j < 6; j++) {
						if (currentNumber == drawnNumbers[j]) {
							numberOfHits++;
							hits.add(currentNumber);
						}
					}
				}
				middlelab.setText(numberOfHits + " hits: " + hits.toString());
			}
		};
		
		for(int i=0;i<49;i++) {
			toggleButtons[i].setOnAction(handlerTBs);
		}
		button.setOnAction(handlerD);
		root.getChildren().add(upperlab);
		root.getChildren().add(middlelab);
		root.getChildren().add(lowerlab);
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