package application;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Most_Popular_WordsController extends BorderPane {

	@FXML
	public VBox websitesVBox;
	@FXML
	public Button scanButton;
	@FXML
	public Button addButton;
	@FXML
	public TabPane tabs;
	@FXML
	public Tab generalTab;

	@FXML
	void initialize() {
	}

	// Show error messages
	public void showErrorWindow(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(null);
		alert.setContentText(errorMessage);
		alert.showAndWait();
	}
	
	static void saveFile(String path, List<String> content) throws Exception {
		Files.write(Paths.get(path), content);
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] temp = Files.readAllBytes(Paths.get(path));
		return new String(temp, encoding);
	}

	// Add website button handler
	public void handleAddButton() {
		VBox websiteVBox = new VBox();
		TextField websiteNameTF = new TextField();
		// websiteNameTF.setPromptText("add website...");
		websiteNameTF.setText("http://www.onet.pl");
		websiteNameTF.setPrefWidth(135);
		TextField websiteSearchCriteriaTF = new TextField();
		// websiteSearchCriteriaTF.setPromptText("add search criteria...");
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
		for(int i=2;i<numberOfWebsites;i++) {
			VBox currentVBox = (VBox) websitesVBox.getChildren().get(i);
			TextField websiteNameTF = (TextField) currentVBox.getChildren().get(0);
			String websiteName=websiteNameTF.getText();
			if(websiteName.equals("")||websiteName.equals(System.getProperty("line.separator"))) {
				showErrorWindow("You need to enter website address!!!");
				return;
			}else {
				websitesList.add(websiteName);
			}
			tabs.getTabs().add(new Tab(websiteName));
			TextField websiteSearchCriteriaTF = (TextField) currentVBox.getChildren().get(1);
			String websiteSearchCriteria=websiteSearchCriteriaTF.getText();
			if(websiteSearchCriteria.equals("")||websiteSearchCriteria.equals(System.getProperty("line.separator"))) {
				showErrorWindow("You need to enter search criteria!!!");
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
			try {
				Connection connect = Jsoup.connect(website);
				System.out.println("Connecting to: " + website + "...");
				rawText[i] = "";
				Document document = connect.get();
				Elements links = document.select(searchCriteriaList.get(i));
				for (Element elem : links) {
					rawText[i] += elem.text() + " ";
				}
			} catch (Exception e) {
				e.printStackTrace();
				showErrorWindow("Problem connecting to " + website + "!!!");
			}
			
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
		
		List<String> firstStep = new ArrayList<>();
		for(int i=0;i<websitesWords.size();i++) {
			firstStep.add("[" + websitesList.get(i) + "]");
			firstStep.addAll(websitesWords.get(i));
		}
		try {
			saveFile("./popular_words.txt", firstStep);
		} catch (IOException e) {
			showErrorWindow("File write error!");
			e.printStackTrace();
		} catch (Exception e) {
			showErrorWindow("Incorrect search criteria!");
		}
		
		// Read from first file
		
		String secondStep = "";
		try {
			secondStep = readFile("./popular_words.txt",StandardCharsets.UTF_8);
		}catch(IOException e) {
			e.printStackTrace();
			showErrorWindow("Error reading popular_words.txt!!!");
		}
	}
}