package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Most_Popular_Words extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Most Popular Words");
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Most_Popular_Words.fxml"));
            Scene scene = new Scene(root,800,600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}