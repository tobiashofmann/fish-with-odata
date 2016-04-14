/**
 * 
 */
package de.itsfullofstars.iot;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Tobias Hofmann
 *
 */
public class Client {

	private static final Logger log = Logger.getLogger(Client.class.getName());
	
	private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {

		// create sensor
		IoTSensor sensor = new IoTSensor();
		try {
			sensor.createSensor();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ScheduledExecutorService.html
		final Runnable weather = new Runnable() {
			public void run() {
				log.log(Level.INFO, "IoT client started.");
				try {
					simulateData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		final ScheduledFuture<?> weatherHandle = scheduler.scheduleAtFixedRate(weather, 1, 10, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				weatherHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}

	/**
	 * Convert received temperature to JSON object
	 * 
	 * We want to have this as a result: 
	 * {
	 *  "Unit":"Celsius",
	 *   "Value": "9", 
	 *   "SensorDetails": {
	 *    "__metadata": {
	 *    	"uri":"http://localhost:7080/iotserver/olingo.svc/Sensors(1L)" } 
	 *   } 
	 * }
	 * 
	 * @param temp
	 * @return
	 */
	public static JSONObject convertToJson(double temp) {
		log.log(Level.FINEST, "convertToJson");
		JSONObject metadata = new JSONObject();
		metadata.put("uri", "http://localhost:7080/iotserver/olingo.svc/Sensors(1L)");
		JSONObject sensorDetails = new JSONObject();
		sensorDetails.put("__metadata", metadata);
		JSONObject jo = new JSONObject();
		jo.put("Unit", "Celsius");
		jo.put("Value", temp);
		jo.put("SensorDetails", sensorDetails);
		log.log(Level.INFO, jo.toString(2));
		return jo;
	}

	/**
	 * Invoke the weather gathering function as well as send the data to the HCP service
	 * @throws IOException
	 */
	public static void simulateData() throws IOException {
		WeatherData wd = new WeatherData();
		double temp = wd.read();
		IoTClient iotClient = new IoTClient();
		iotClient.sendData(convertToJson(temp));
	}

}
