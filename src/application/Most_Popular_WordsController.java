package application;

import java.io.IOException;
import java.nio.charset.Charset;
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

	@FXML
	void initialize() {}
	
	public void handleAddButton() {
		VBox websiteVBox = new VBox();
		TextField websiteNameTF = new TextField();
		websiteNameTF.setPromptText("add website...");
		websiteNameTF.setPrefWidth(135);
		TextField websiteSearchCriteriaTF = new TextField();
		websiteSearchCriteriaTF.setPromptText("add search criteria...");
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
	
	public void handleScanButton() {
		
		// Get website names and search criteria from UI
		
		List<String> websitesList = new ArrayList<>();
		List<String> searchCriteriaList = new ArrayList<>();
		int numberOfWebsites=websitesVBox.getChildren().size();
		tabs.getTabs().clear();
		System.out.println(numberOfWebsites-2);
		for(int i=2;i<numberOfWebsites;i++) {
			VBox currentVBox = (VBox) websitesVBox.getChildren().get(i);
			TextField websiteNameTF = (TextField) currentVBox.getChildren().get(0);
			String websiteName=websiteNameTF.getText();
			websitesList.add(websiteName);
			tabs.getTabs().add(new Tab(websiteName));
			TextField websiteSearchCriteriaTF = (TextField) currentVBox.getChildren().get(1);
			searchCriteriaList.add(websiteSearchCriteriaTF.getText());
		}

		// Scan websites for chosen elements
		
//		List<String> firstStep = new ArrayList<>();
		int websitesListSize = websitesList.size();
		String [] rawText = new String[websitesListSize];
		List<List<String>> websitesWords = new ArrayList<>();
		for (int i = 0; i < websitesListSize; i++) {
			String website = websitesList.get(i);
			Connection connect = Jsoup.connect(website);
			System.out.println("Connecting to: " + website + "...");
			try {
				Document document = connect.get();
				Elements links = document.select(searchCriteriaList.get(i));
				for (Element elem : links) {
					rawText[i] += " " + elem.text();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(websitesListSize + " " + i);
			List<String> words = new ArrayList<>();
			for(String word : rawText[i].split("\\s+")) {words.add(word);}
			words = words.stream().map(word -> word.replaceAll("[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]", "")).map(String::toLowerCase).collect(Collectors.toList());
			websitesWords.add(words);
		}
		
		System.out.println(websitesWords);
		
		// Remove everything but letters and spaces
		
		
		
		// Save to first file
		
		System.out.println("Saving all popular words to popular_words.txt...");
//		Path firstFile1 = Paths.get("./popular_words.txt");

//		try {
//			Files.write(firstFile1, "[onet]" + words);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}