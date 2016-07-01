import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class SoundCloudFetch {
	
	final String BASE_URL = "https://api.soundcloud.com/tracks";
	final String CLIENT_ID = "8149d80e337d953a45f37a534404868f";

	
	public ArrayList<String> fetchTracks(String query, int limit)  throws ClientProtocolException, IOException {
		
		ArrayList<String> permaLinkUrls = new ArrayList<String>();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		String finalUrl = BASE_URL + "?client_id=" + CLIENT_ID + "&q=" + query.replaceAll(" ", "%20") + "&limit=" + limit;
		HttpGet getRequest = new HttpGet(finalUrl);
		CloseableHttpResponse response = httpClient.execute(getRequest);
		String json = "";
		
		try {
		    System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
		    
		    String line = null;
		    
		    while((line = in.readLine()) != null){
		    	System.out.println(line);
		    	json += line;
		    }
		    
		    //call JSON Parser
		    
		    EntityUtils.consume(entity);
		    
		    
		} finally {
		    response.close();
		}
		
		return permaLinkUrls;
		
		
	}
	
}
