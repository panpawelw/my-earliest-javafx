package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Most_Popular_WordsController extends BorderPane {
	
	@FXML public VBox websitesVBox;
	@FXML public Button scanButton;
	@FXML public Button addButton;
	@FXML public TabPane tabs;
	@FXML public Tab generalTab;

	@FXML
	void initialize() {}
	
	// Show error messages
	public void showErrorWindow(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(null);
		alert.setContentText(errorMessage);
		alert.showAndWait();
	}
	
	// add website button handler
	public void handleAddButton() {
		VBox websiteVBox = new VBox();
		TextField websiteNameTF = new TextField();
//		websiteNameTF.setPromptText("add website...");
		websiteNameTF.setText("http://www.onet.pl");
		websiteNameTF.setPrefWidth(135);
		TextField websiteSearchCriteriaTF = new TextField();
//		websiteSearchCriteriaTF.setPromptText("add search criteria...");
		websiteSearchCriteriaTF.setText("span.title");
		websiteSearchCriteriaTF.setPrefWidth(135);
		Button deleteButton = new Button("delete website");
		deleteButton.setPrefWidth(135);
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				websitesVBox.getChildren().remove(websiteVBox);
			}
		});
		websiteVBox.getChildren().addAll(websiteNameTF, websiteSearchCriteriaTF, deleteButton);
		websitesVBox.getChildren().add(websiteVBox);
	}
	
	// Scan websites button handler
	public void handleScanButton() {
		
		// Get website names and search criteria from UI
		
		List<String> websitesList = new ArrayList<>();
		List<String> searchCriteriaList = new ArrayList<>();
		int numberOfWebsites=websitesVBox.getChildren().size();
		tabs.getTabs().clear();
		tabs.getTabs().add(generalTab);
		System.out.println(numberOfWebsites-2);
		for(int i=2;i<numberOfWebsites;i++) {
			VBox currentVBox = (VBox) websitesVBox.getChildren().get(i);
			TextField websiteNameTF = (TextField) currentVBox.getChildren().get(0);
			String websiteName=websiteNameTF.getText();
			System.out.println(websiteName);
			if(websiteName.equals(null)||websiteName.equals("")||websiteName.equals(System.getProperty("line.separator"))) {
				showErrorWindow("You have to enter website address!!!");
				return;
			}else {
				websitesList.add(websiteName);
			}
			tabs.getTabs().add(new Tab(websiteName));
			TextField websiteSearchCriteriaTF = (TextField) currentVBox.getChildren().get(1);
			String websiteSearchCriteria=websiteSearchCriteriaTF.getText();
			System.out.println(websiteSearchCriteria);
			if(websiteSearchCriteria.equals(null)||websiteSearchCriteria.equals("")||websiteSearchCriteria.equals(System.getProperty("line.separator"))) {
				showErrorWindow("You have to enter search criteria!!!");
				return;
			}else {
				searchCriteriaList.add(websiteSearchCriteria);
			}
		}

		// Scan websites for chosen elements
		
		int websitesListSize = websitesList.size();
		String [] rawText = new String[websitesListSize];
		List<List<String>> websitesWords = new ArrayList<>();
		for (int i = 0; i < websitesListSize; i++) {
			String website = websitesList.get(i);
			Connection connect = Jsoup.connect(website);
			System.out.println("Connecting to: " + website + "...");
			rawText[i] = "";
			try {
				Document document = connect.get();
				Elements links = document.select(searchCriteriaList.get(i));
				for (Element elem : links) {
					rawText[i] += elem.text() + " ";
				}
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			
			System.out.println(websitesListSize + " " + i);
			List<String> words = new ArrayList<>();
			for(String word : rawText[i].split("\\s+")) {words.add(word);}
			words = words.parallelStream()
					.map(w -> w.replaceAll("[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]", ""))
					.filter(w -> w.length()>3)
					.map(String::toLowerCase)
					.collect(Collectors.toList());
			websitesWords.add(words);
		}
		
		System.out.println(websitesWords);
		
		// Save to first file
		
		System.out.println("Saving all popular words to popular_words.txt...");
		List<String> firstStep = new ArrayList<>();
		for(int i=0;i<websitesWords.size();i++) {
			firstStep.add("[" + websitesList.get(i) + "]");
			firstStep.addAll(websitesWords.get(i));
		}
		Path firstFile = Paths.get("./popular_words.txt");
		try {
			Files.write(firstFile, firstStep);
		} catch (IOException e) {
			showErrorWindow("File write error!");
			e.printStackTrace();
		} catch (Exception e) {
			showErrorWindow("Incorrect search criteria!");
		}
	}
}