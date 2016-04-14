/**
 * 
 */
package de.itsfullofstars.iot;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class IoTClient {

	private static final Logger log = Logger.getLogger(IoTClient.class.getName());
	
	CloseableHttpClient httpclient = HttpClients.createDefault();
	HttpPost httpPost = null;
	
	// POST data to URL: http://localhost:7080//iot/olingo.svc/Measurements
	private static final String SCHEME = "http";
	private static final String HOST = "localhost";
	private static final int 	PORT = 7080;
	private static final String PATH = "/iot/olingo.svc/Measurements";

	/**
	 * Constructor
	 */
	public IoTClient() {
		URI uri;
		try {
			uri = new URIBuilder().setScheme(SCHEME).setHost(HOST).setPort(PORT).setPath(PATH).build();
			this.httpPost = new HttpPost(uri);
			log.log(Level.INFO, "URI: {0}", this.httpPost.getURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send weather data to HCP IoT app
	 * 
	 * @param temp
	 * @return true if sending data worked. False otherwise
	 * @throws IOException 
	 */
	protected boolean sendData(JSONObject jo) throws IOException {
		boolean retVal = false;

		StringEntity entity = new StringEntity(jo.toString(), ContentType.create("application/json", Consts.UTF_8));
		log.log(Level.INFO, "Content Length: {0}", entity.getContentLength());
		log.log(Level.INFO, "Content type: {0}", entity.getContentType().getName());
		log.log(Level.INFO, "Content value: {0}", entity.getContentType().getValue());

		this.httpPost.setEntity(entity);

		try {
			CloseableHttpResponse response = httpclient.execute(this.httpPost);
			log.log(Level.FINEST, "Protocol version: {0}", response.getProtocolVersion());
			log.log(Level.FINEST, "Status line: {0}", response.getStatusLine().toString());
			log.log(Level.FINEST, "Status code: {0}", response.getStatusLine().getStatusCode());
			log.log(Level.FINEST, "Reason phrase: {0}", response.getStatusLine().getReasonPhrase());
			try {
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {
					long len = responseEntity.getContentLength();
					if (len != -1 && len > 0) {
						System.out.println(EntityUtils.toString(responseEntity));
						retVal = true;
					} else {
						// sonar todo
					}
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
