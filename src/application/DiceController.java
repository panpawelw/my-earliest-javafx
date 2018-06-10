package application;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class DiceController extends GridPane{

	EventHandler<ActionEvent> throwHandler = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			Random rand = new Random(); // dice throw and result output
			int multiplier = 0;
			int modifier = 0;
			int faces = 0;
			long finalResult = 0;
			for (int l = 1; l < multiplier; l++) {
				int result = rand.nextInt(faces) + 1;
				finalResult = finalResult + result;
			}
			finalResult = finalResult + modifier;
		}
	};
	
}