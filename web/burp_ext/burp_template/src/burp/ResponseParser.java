package burp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.*;

public class ResponseParser {
	private static final String CRLF = "\r\n";

	public static void main(String[] args) throws IOException {
		String json = new String(Files.readAllBytes(Paths.get("response")));
		JSONArray jArr = new JSONArray(json);
		JSONObject obj = jArr.getJSONObject(0);
		if(obj.getString("actionStatus").equals("SUCCESS")){
			String newResponse = obj.getString("responseStatusLine") + CRLF;
			JSONArray headers = obj.getJSONArray("responseHeaders");
			for(int i = 0; i < headers.length(); i++) {
				if (!headers.getJSONObject(i).getString("name").equals("Transfer-Encoding")) {
					newResponse += headers.getJSONObject(i).getString("name") + ": ";
					newResponse += headers.getJSONObject(i).getString("value") + CRLF;
				}
			}
			newResponse += CRLF + obj.getString("responsePayload");
			
			System.out.println(newResponse);
		}
	}
}
