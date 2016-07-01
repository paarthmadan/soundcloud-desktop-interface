import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import javafx.application.Application;
import javafx.stage.Stage;

public class UserInterface extends Application{
	
	public static void main(String [] args) throws ClientProtocolException, IOException{
		
		SoundCloudFetch soundCloud = new SoundCloudFetch();
		soundCloud.fetchTracks("Drake", 10);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

	}
	
	
	
}
