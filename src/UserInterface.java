import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import javafx.application.Application;
import javafx.stage.Stage;

public class UserInterface extends Application{
	
	public static void main(String [] args) throws ClientProtocolException, IOException{
		
		SoundCloudFetch soundCloud = new SoundCloudFetch();
		ArrayList<String> links = soundCloud.fetchTracks("Yaara Da Truck Baliye", 50);
		
		for(int i = 0; i < links.size(); i++){
			System.out.println("Track " + i + " : " + links.get(i));
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

	}
	
	
	
}
