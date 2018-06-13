package application;

import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class DiceController extends GridPane{

	@FXML
	public Label infoLabel;
	@FXML
	public TextField expressionTF;
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
	public ToggleButton tBD3;
	public ToggleButton tBD4;
	public ToggleButton tBD6;
	public ToggleButton tBD8;
	public ToggleButton tBD10;
	public ToggleButton tBD12;
	public ToggleButton tBD20;
	public ToggleButton tBD100;
	
	@FXML
	void initialize() {
		multiplierL.setUserData((long) 1);
		modifierL.setUserData((long) 0);
		diceTypeL.setUserData(0);
		tBD3.setUserData(3);
		tBD4.setUserData(4);
		tBD6.setUserData(6);
		tBD8.setUserData(8);
		tBD10.setUserData(10);
		tBD12.setUserData(12);
		tBD20.setUserData(20);
		tBD100.setUserData(100);
		multiplierTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(!newValue) {
		            handleMultiplierTF();
		        }
		    }
		});
		modifierTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(!newValue) {
		            handleModifierTF();
		        }
		    }
		});
	}
	
	public void handleRollButton() {
		String expression = expressionTF.getText();
		System.out.println(expression.matches("\\d*d"));
		System.out.println(expression);
		try {
		Random rand = new Random(); // dice roll and result output
		long multiplier = (long) multiplierL.getUserData();
		long modifier = (long) modifierL.getUserData();
		int diceType = (int) diceTypeL.getUserData();
		long finalResult = 0;
		for (long l = 0; l < multiplier; l++) {
			long result = rand.nextInt(diceType) + 1;
			finalResult = finalResult + result;
		}
		finalResult = finalResult + modifier;
		rollResultLabel.setText("" + finalResult);
		}catch(Exception e){
			e.printStackTrace();
			rollResultLabel.setText("Invalid parameters!");
			String multiplier = multiplierL.getUserData().toString();
			String modifier = modifierL.getUserData().toString();
			String diceType = diceTypeL.getUserData().toString();
			System.out.println(multiplier + " " + modifier + " " + diceType);
		}
	}
	
	public void handleMultiplierTF() {
		try {
			long multiplier = Long.parseLong(multiplierTF.getText());
			if(multiplier<1) {
				multiplierTF.setText("1");
				multiplierL.setUserData(1);
			}else {
				multiplierL.setUserData(multiplier);
			}
		}catch(Exception e){
			multiplierTF.setText("1");
			multiplierL.setUserData((long) 1);
		}
	}
	
	public void handleMultiplierPlusB() {
		long multiplier = (long) multiplierL.getUserData();
		multiplier++;
		multiplierTF.setText("" + multiplier);
		multiplierL.setUserData(multiplier);
	}
	
	public void handleMultiplierMinusB() {
		long multiplier = (long) multiplierL.getUserData();
		if(multiplier>1) {
			multiplier--;
			multiplierTF.setText("" + multiplier);
			multiplierL.setUserData(multiplier);
		}
	}
	
	public void handleModifierTF() {
		try {
			long modifier = Long.parseLong(modifierTF.getText());
			modifierL.setUserData(modifier);
		}catch(Exception e){
			modifierTF.setText("0");
			modifierL.setUserData((long) 0);
		}
	}
	
	public void handleModifierPlusB() {
		long modifier = (long) modifierL.getUserData();
		modifier++;
		modifierTF.setText("" + modifier);
		modifierL.setUserData(modifier);
	}
	
	public void handleModifierMinusB() {
		long modifier = (long) modifierL.getUserData();
		modifier--;
		modifierTF.setText("" + modifier);
		modifierL.setUserData(modifier);
	}
	
	public void handleToggleButtons() {
		if (diceTypeTG.getSelectedToggle()==null) {
			diceTypeL.setUserData(0);
		}else {
			diceTypeL.setUserData(diceTypeTG.getSelectedToggle().getUserData());
		}
	}
}