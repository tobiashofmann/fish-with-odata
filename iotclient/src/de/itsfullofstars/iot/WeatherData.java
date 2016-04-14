package de.itsfullofstars.iot;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class WeatherData {

	private static final Logger log = Logger.getLogger(WeatherData.class.getName());
	
	CloseableHttpClient httpclient = HttpClients.createDefault();
	HttpGet httpGet = null;
	
	private static final String SCHEME = "http";
	private static final String HOST = "api.openweathermap.org";
	private static final int 	PORT = 80;
	private static final String PATH = "/data/2.5/weather";
	private static final String CITY = "3451190";
	private static final String UNIT = "metric";
	private static final String APPID = "insert your own key";
	
	/**
	 * 
	 */
	public WeatherData() {
		URI uri;
		try {
			uri = new URIBuilder()
			        .setScheme(SCHEME)
			        .setHost(HOST)
			        .setPort(PORT)
			        .setPath(PATH)
			        .setParameter("id", CITY)
			        .setParameter("units", UNIT)
			        .setParameter("appid", APPID)
			        .build();
			this.httpGet = new HttpGet(uri);
			log.log(Level.INFO, "URI: {0}", this.httpGet.getURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Reads the weather data from the JSON REST API
	 * 
	 * @return temperature of city, Rio de Janeiro
	 * @throws IOException
	 */
	protected double read() throws IOException {
		log.log(Level.FINEST, "read");
		double retVal = 0;
		
		try {
			
			CloseableHttpResponse response = httpclient.execute(this.httpGet);

			log.log(Level.FINEST, "Protocol version: {0}", response.getProtocolVersion());
			log.log(Level.FINEST, "Status line: {0}", response.getStatusLine().toString());
			log.log(Level.FINEST, "Status code: {0}", response.getStatusLine().getStatusCode());
			log.log(Level.FINEST, "Reason phrase: {0}", response.getStatusLine().getReasonPhrase());

			try {
				HttpEntity entity = response.getEntity();
				 if (entity != null) {
				        long len = entity.getContentLength();
				        if (len != -1 && len < 2048) {
				        	String jsonResponse = EntityUtils.toString(entity);
				        	log.log(Level.FINEST, "JSON response: {0}", jsonResponse);
				            
				            JSONObject jo = new JSONObject(jsonResponse);
				            log.log(Level.INFO, "JSON response: {0}", jo.toString(2));
				            log.log(Level.FINEST, "JSON main: {0}", jo.getJSONObject("main").toString(2));
				            log.log(Level.FINEST, "JSON temp: {0}", jo.getJSONObject("main").get("temp"));
				            
				            String sTemp = (String) jo.getJSONObject("main").get("temp").toString();
				            log.log(Level.INFO, "JSON temperature: {0}", sTemp);
				            double temp = new Double(sTemp).doubleValue();
				            
//				            log.log(Level.FINEST, "temperature: ", sTemp);
//				            log.log(Level.FINEST, "humidity: ", humidity);
//				            log.log(Level.FINEST, "windSpeed: ", windSpeed);
				            
				            retVal = temp;
				        } else {}
				    }
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.close();
		}
		return retVal;
	}
}
