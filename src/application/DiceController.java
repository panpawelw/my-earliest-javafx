package application;

import java.util.Random;

import javafx.event.ActionEvent;
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
	@FXML
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
	
	public void myGroupAction(ActionEvent action)
    {
      System.out.println("Toggled: " + diceTypeTG.getSelectedToggle().getUserData().toString());
    }
	
	@FXML
	void initialize() {
		multiplierL.setUserData(1);
		modifierL.setUserData(0);
//		diceTypeTG.
		
		
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
		try {
			int multiplier = Integer.parseInt(multiplierTF.getText());
			if(multiplier<1) {
				multiplierTF.setText("1");
				multiplierL.setUserData(1);
			}else {
				multiplierL.setUserData(multiplier);
			}
		}catch(Exception e){
			multiplierTF.setText("1");
			multiplierL.setUserData(1);
		}
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
		try {
			int modifier = Integer.parseInt(modifierTF.getText());
			modifierL.setUserData(modifier);
		}catch(Exception e){
			modifierTF.setText("1");
			modifierL.setUserData(1);
		}
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