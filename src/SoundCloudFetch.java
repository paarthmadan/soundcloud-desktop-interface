import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;



public class SoundCloudFetch {
	
	//API KEYS
	final String BASE_URL = "https://api.soundcloud.com/tracks";
	final String CLIENT_ID = "8149d80e337d953a45f37a534404868f";
	
	//Method called from UI Class to Fetch Tracks with given query and limit
	
	public ArrayList<String> fetchTracks(String query, int limit)  throws ClientProtocolException, IOException {
		
		//Empty ArrayList to store urls
		ArrayList<String> permaLinkUrls = new ArrayList<String>();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//Creating request URL with given information
		String composedUrl = BASE_URL + "?client_id=" + CLIENT_ID + "&q=" + query.replaceAll(" ", "%20") + "&limit=" + limit;
		
		HttpGet getRequest = new HttpGet(composedUrl);
		CloseableHttpResponse response = httpClient.execute(getRequest);
		
		String json = "";
		
		try {
		    System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
		    
		    String line = null;
		    
		    //converting entity response into String "json"
		    while((line = in.readLine()) != null){
		    	json += line;
		    }
		    
		    //calls method to parse permaLinkUrls and assigns value to the ArrayList
		    permaLinkUrls = permaLinkParser(json, permaLinkUrls);
		    
		    EntityUtils.consume(entity);
		    
		} finally {
		    response.close();
		}
		
		//returns final ArrayList to UI Class
		return permaLinkUrls;
	}
	
	
	//using Gson Google Library, parses JSON for permalink, stores it in ArrayList and returns
	private ArrayList<String> permaLinkParser(String response, ArrayList<String> permaLinkUrls){
		JsonElement element = new JsonParser().parse(response);
		JsonArray array = element.getAsJsonArray();
		
		for(int i = 0; i < array.size(); i++){
			JsonObject currentObject = array.get(i).getAsJsonObject().getAsJsonObject();
			
			String key = currentObject.get("permalink_url").toString().replaceAll("\"", "");
			permaLinkUrls.add(key);
		}
		
		return permaLinkUrls;
		
	}
	
}
