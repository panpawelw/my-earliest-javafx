package application;

import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class DiceController extends GridPane{

	public Label infoLabel;
	public Label rollResultLabel;
	public Button rollButton;
	public ToggleGroup diceTypeTG;
	
	public void handleRollButton() {
		Random rand = new Random(); // dice roll and result output
		int multiplier = 0;
		int modifier = 0;
		int faces = 0;
		long finalResult = 0;
		for (int l = 1; l < multiplier; l++) {
			int result = rand.nextInt(faces) + 1;
			finalResult = finalResult + result;
		}
		finalResult = finalResult + modifier;
		rollResultLabel.setText("Select all parameters or enter expression!");
	}
}