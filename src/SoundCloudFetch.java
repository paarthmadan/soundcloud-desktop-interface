import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class SoundCloudFetch {
	
	static String baseUrl = "https://api.soundcloud.com/tracks";
	final static String CLIENT_ID = "8149d80e337d953a45f37a534404868f";
	static String searchQuery = "Drake";
	final static int LIMIT = 50;
	
	
	public static void main(String [] args) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		String finalUrl = baseUrl+ "?client_id=" + CLIENT_ID + "&q=" + searchQuery + "&limit=" + LIMIT;
		
		HttpGet getRequest = new HttpGet(finalUrl);
		CloseableHttpResponse response = httpClient.execute(getRequest);
		
		try {
		    System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
		    String line = null;
		    
		    while((line = in.readLine()) != null){
		    	System.out.println(line);
		    }
		    
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}

		
	}
}
