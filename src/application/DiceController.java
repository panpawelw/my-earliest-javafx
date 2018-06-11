package application;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class DiceController extends GridPane{

	@FXML
	public Label infoLabel;
	public Label rollResultLabel;
	public Button rollButton;
	public ToggleGroup diceTypeTG;
	public Label multiplierL;
	public Label diceTypeL;
	public Label modifierL;
	public TextField multiplierTF;
	public Button multiplierPlusB;
	public Button multiplierMinusB;
	public TextField modifierTF;
	public Button modifierPlusB;
	public Button modifierMinusB;
	
	@FXML
	void initialize() {
		multiplierL.setUserData(1);
		modifierL.setUserData(0);
		
	}
	
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
	
	public void handleMultiplierTF() {
		rollResultLabel.setText("multiplierTF");
	}
	
	public void handleMultiplierPlusB() {
		int multiplier = (Integer) multiplierL.getUserData();
		multiplier++;
		multiplierTF.setText("" + multiplier);
		multiplierL.setUserData(multiplier);
	}
	
	public void handleMultiplierMinusB() {
		int multiplier = (Integer) multiplierL.getUserData();
		if(multiplier>1) {
			multiplier--;
			multiplierTF.setText("" + multiplier);
			multiplierL.setUserData(multiplier);
		}
	}
	
	public void handleModifierTF() {
		rollResultLabel.setText("modifierTF");
	}
	
	public void handleModifierPlusB() {
		int modifier = (Integer) modifierL.getUserData();
		modifier++;
		modifierTF.setText("" + modifier);
		modifierL.setUserData(modifier);
	}
	
	public void handleModifierMinusB() {
		int modifier = (Integer) modifierL.getUserData();
		modifier--;
		modifierTF.setText("" + modifier);
		modifierL.setUserData(modifier);
	}
}