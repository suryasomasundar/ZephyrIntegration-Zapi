package zapi.DOM.Folder;

import java.util.HashMap;

import org.json.JSONObject;

public class ExecuteTestDOM {

	public int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int statusCode) {
		this.status = statusCode;
	}

	public String executeTestCase() {
		HashMap<String, Object> jsoninput = new HashMap();

		jsoninput.put("status", status);

		JSONObject json = new JSONObject(jsoninput);
		String input = json.toString();
		return input;
	}

}
