import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserInterface extends Application{
	
	final String TITLE = "Soundcloud Desktop Interface";
	final String LABEL_TEXT = "Enter a search query related to the track of interest:";
	
	final int WIDTH = 500;
	final int HEIGHT = 300;
	final int LIMIT = 50;
	
	Stage window;
	Scene mainScene;
	GridPane gridLayout;
	
	TextField userInput;
	ChoiceBox<Integer> dropDownLimits;
	Button searchButton;
	
	
	public static void main(String [] args) throws ClientProtocolException, IOException{
		
		SoundCloudFetch soundCloud = new SoundCloudFetch();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		gridLayout = new GridPane();

		window.setTitle(TITLE);
		window.setResizable(false);
		
		window.show();
	}
	
	
	
}
