package application;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
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
import javafx.scene.text.TextAlignment;

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

	// Write to file
	static void saveFile(String path, List<String> content) throws Exception {
		Files.write(Paths.get(path), content);
	}

	// Read from file
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] temp = Files.readAllBytes(Paths.get(path));
		return new String(temp, encoding);
	}

	// create an ordered word frequency map
	static LinkedHashMap<String, Integer> wordsMap(String[] rawData){
		HashMap<String, Integer> temp = new HashMap<>();
		for (String iterator : rawData) {
			Integer count = temp.get(iterator);
			if (count == null)
				count = 0;
			temp.put(iterator, count + 1);
		}
		LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
		for (int i = 0; i < temp.size(); i++) {
			Entry<String, Integer> maxEntry = null;
			for (Entry<String, Integer> entry : temp.entrySet()) {
				if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
					maxEntry = entry;
				}
			}
			result.put(maxEntry.getKey(), maxEntry.getValue());
			temp.remove(maxEntry.getKey());
		}
		return result;
	}
	
	// Display chart and word list in a tab
	public void displayInTab(String[] wordList, int tab) {
		ScrollPane newSP = new ScrollPane();
		Label newLabel = new Label(wordsMap(wordList).toString());
		newLabel.maxWidthProperty().bind(newSP.widthProperty().subtract(15));
		newLabel.setWrapText(true);
		newLabel.setTextAlignment(TextAlignment.JUSTIFY);
		newSP.setContent(newLabel);
		tabs.getTabs().get(tab).setContent(newSP);
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
		int numberOfWebsites = websitesVBox.getChildren().size();
		tabs.getTabs().clear();
		tabs.getTabs().add(generalTab);
		for (int i = 2; i < numberOfWebsites; i++) {
			VBox currentVBox = (VBox) websitesVBox.getChildren().get(i);
			TextField websiteNameTF = (TextField) currentVBox.getChildren().get(0);
			String websiteName = websiteNameTF.getText();
			if (websiteName.equals("") || websiteName.equals(System.getProperty("line.separator"))) {
				showErrorWindow("You need to enter website address!!!");
				return;
			} else {
				websitesList.add(websiteName);
			}
			tabs.getTabs().add(new Tab(websiteName));
			TextField websiteSearchCriteriaTF = (TextField) currentVBox.getChildren().get(1);
			String websiteSearchCriteria = websiteSearchCriteriaTF.getText();
			if (websiteSearchCriteria.equals("")
					|| websiteSearchCriteria.equals(System.getProperty("line.separator"))) {
				showErrorWindow("You need to enter search criteria!!!");
				return;
			} else {
				searchCriteriaList.add(websiteSearchCriteria);
			}
		}

		// Scan websites for chosen elements

		int websitesListSize = websitesList.size();
		String[] rawText = new String[websitesListSize];
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
			for (String word : rawText[i].split("\\s+")) {
				words.add(word);
			}
			words = words.parallelStream().map(w -> w.replaceAll("[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]", ""))
					.filter(w -> w.length() > 3).map(String::toLowerCase).collect(Collectors.toList());
			websitesWords.add(words);
		}

		// Save to first file

		List<String> firstStep = new ArrayList<>();
		for (int i = 0; i < websitesWords.size(); i++) {
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
			secondStep = readFile("./popular_words.txt", StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			showErrorWindow("Error reading popular_words.txt!!!");
		}

		// Analyze file content and display results in tabs

		String eol = System.getProperty("line.separator");
		String generalTabContentTemp = secondStep.replaceAll("\\[(.*?)\\]", "");
		String[] generalTabContent = generalTabContentTemp.split(eol);
		displayInTab(generalTabContent, 0);
		String[] websitesTabsContent = secondStep.split("\\[(.*?)\\]");
		for(String temp : websitesTabsContent) {
			System.out.println(temp + "*");
		}
		int numberOfTabs = websitesTabsContent.length;
		System.out.println(numberOfTabs);
		String[][] websitesWordsMaps = new String[numberOfTabs][];
		for(int i=1;i<numberOfTabs;i++) {
			websitesWordsMaps[i] = websitesTabsContent[i].split(eol);
			displayInTab(websitesWordsMaps[i], i);
		}
	}
}