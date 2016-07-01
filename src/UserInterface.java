import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface extends Application{
	
	//constants
	final String TITLE = "Soundcloud Desktop Interface";
	final String INPUT_LABEL = "Enter a search query related to the track of interest:";
	final String LIMIT_LABEL = "Choose the amount of tracks you would like to see:";
	final String SEARCH_BUTTON_TEXT = "Search!";
	final String CLEAR_BUTTON_TEXT = "Clear";
	
	final int WIDTH = 500;
	final int HEIGHT = 350;
	final int LIMIT = 50;
	
	final Insets textFieldInsets = new Insets(0, 55, 0, 55);
	final Insets listViewInsets = new Insets(0, 55, 10, 55);
	
	//Layouts and Containers
	Stage window;
	Scene mainScene;
	VBox verticalLayout;
	HBox horizontalButtonLayout;
	BorderPane mainLayout;
	
	//Components
	TextField userInput;
	ChoiceBox<Integer> dropDownLimits;
	Button searchButton;
	Button clearButton;
	ListView<String> linksView;
	
	
	public static void main(String [] args) throws ClientProtocolException, IOException{
		
		SoundCloudFetch soundCloud = new SoundCloudFetch();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		window.setTitle(TITLE);
		window.setResizable(false);
		
		mainLayout = new BorderPane();
		
		verticalLayout = new VBox(15);
		horizontalButtonLayout = new HBox(15);
		
		//Labels
		Label userInputLabel = new Label(INPUT_LABEL);
		Label limitLabel = new Label(LIMIT_LABEL);
		
		//Buttons
		searchButton = new Button();
		searchButton.setText(SEARCH_BUTTON_TEXT);
		
		clearButton = new Button();
		clearButton.setText(CLEAR_BUTTON_TEXT);
		
		//Text Field
		userInput = new TextField();
		userInput.setPromptText("Search Query");		
		VBox.setMargin(userInput, textFieldInsets);
		
		//ChoiceBox Dropdown
		dropDownLimits = new ChoiceBox<Integer>();
		dropDownLimits.getItems().addAll(10, 20, 30, 40, 50);
		dropDownLimits.getSelectionModel().selectFirst();
		
		//ListView
		linksView = new ListView<String>();
		linksView.setMaxSize(WIDTH, 130);
		VBox.setMargin(linksView, listViewInsets);
		
		horizontalButtonLayout.setAlignment(Pos.CENTER);
		horizontalButtonLayout.getChildren().addAll(searchButton, clearButton);
		
		//Adding elements to vertical layout
		verticalLayout.setAlignment(Pos.TOP_CENTER);
		verticalLayout.getChildren().addAll(userInputLabel, userInput, limitLabel,
				dropDownLimits, horizontalButtonLayout, linksView);

		//Embedding vertical and horizontal layout in mainLayout
		mainLayout.setTop(verticalLayout);
		
		mainLayout.setPadding(new Insets(10, 10, 10, 10));
		
		//Creating scene
		mainScene = new Scene(mainLayout, WIDTH, HEIGHT);

		//Setting and showing window
		window.setScene(mainScene);
		window.show();
	}
	
	
	
}
