package application;

import javafx.application.Application;
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
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Guess Numbers #2");
		VBox root = new VBox(5);
		Label upperLab = new Label("Think of a number between 0 and 1000.");
		Label middleUpperLab = new Label("I bet I can guess it in no more than 10 attempts!");
		Label middleLowerLab = new Label();
		Label lowerLab = new Label();
		TilePane tile = new TilePane();
		tile.setPrefColumns(3);
		Button button1 = new Button("  TOO MUCH  ");
		Button button2 = new Button("  CORRECT  ");
		Button button3 = new Button(" NOT ENOUGH ");
		root.setAlignment(Pos.CENTER);
		tile.getChildren().add(button1);
		tile.getChildren().add(button2);
		tile.getChildren().add(button3);
		
		
		root.getChildren().add(upperLab);
		root.getChildren().add(middleUpperLab);
		root.getChildren().add(middleLowerLab);
		root.getChildren().add(lowerLab);
		root.getChildren().add(tile);
		Scene scene = new Scene(root,300,150);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}