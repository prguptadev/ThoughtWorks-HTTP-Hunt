import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;

public class RequestCaller {

	public static String sendGET() throws IOException, JSONException {
		URL obj = new URL(Constants.GET_URL);

		// with proxy
		// Proxy proxy = new Proxy(Proxy.Type.HTTP, new
		// InetSocketAddress("10.0.0.0",8080)); // if using proxy
		// HttpURLConnection con = (HttpURLConnection)obj.openConnection(proxy);

		// - without proxy
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// set Headers
		con.setRequestMethod("GET");
		con.setRequestProperty("UserID", Constants.UserID);
		con.setRequestProperty("Content-Type", Constants.CONTENT_TYPE);
		con.setRequestProperty("Accept", "application/json");
		con.setUseCaches(false);
		con.setDoInput(true);
		con.setDoOutput(true);
		int responseCode = con.getResponseCode();

		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			String formattedJson = PlayGame.jsonBeautify(response.toString());
			System.out.println(formattedJson);
			return response.toString();
		} else {
			System.out.println("GET request not worked");
			return null;
		}
	}

	public static void sendPOST() throws IOException, JSONException {
		URL obj = new URL(Constants.POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("userId", "-MQ2A1r8");
		con.setRequestProperty("Content-Type", Constants.CONTENT_TYPE);
		// For POST - START
		String formatstr = PlayGame.jsonBeautify(PlayGame.stageOne(sendGET()).toString());
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		osw.write(formatstr.toString());
		osw.flush();
		osw.close();
		// For POST - END
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}

}
