package zapi.prototytpe;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import zapi.DOM.Cycle.CreateTestCycleDOM;
import zapi.DOM.Folder.AddTestCaseToFolderDOM;
import zapi.DOM.Folder.CreateTestFolderDOM;
import zapi.DOM.Folder.ExecuteTestDOM;
import zapi.Utils.ConnectionUtils;

public class ConnectionManager {
	static final String jiraUrl = "https://jira.myvest.com:8443";
	static final String createCycle = "/rest/zapi/latest/cycle";
	static final String createFolder = "/rest/zapi/latest/folder/create";
	static final String getListOfFolders = "/rest/zapi/latest/cycle/";
	static final String getTestCaseID = "/rest/api/2/issue/";
	static final String addTestCaseToCycle = "/rest/zapi/latest/execution/";
	static final String executeTests = "/rest/zapi/latest/execution/";
	static final String folderURLParameters = "/folders\\?projectId=10000&versionId=-1";
	public static String cycleID = "";
	public static Integer folderId = 0;
	public static String jiraId = "";
	public static String testId = "";
	public static Integer executionID = 0;
	public static String executionKey = "";
	static JsonNode executionValue = null;

	private static ConnectionManager connectionManager = new ConnectionManager();
	private static Gson gson = new Gson();
	private static URIBuilder uriBuilder = new URIBuilder();
	static CloseableHttpClient client;
	static Logger logger = Logger.getLogger(ConnectionManager.class.getName());

	ConnectionManager() {
		client = HttpClients.createDefault();
	}

	public static ConnectionManager getInstance() {
		return connectionManager;
	}

	public static void refreshSession() throws IOException {
		closeConnection();
		client = HttpClients.createDefault();
	}

	public static void closeConnection() throws IOException {
		uriBuilder = null;
		uriBuilder = new URIBuilder();
		client.close();
	}

	public static Gson getGson() {
		return gson;
	}

	public static HttpEntity makeRequest(HttpGet get) throws IOException {
		HttpResponse httpResponse = client.execute(get);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK_200) {
			return httpResponse.getEntity();
		} else {
			logger.severe("RESPONSE CODE : " + httpResponse.getStatusLine().getStatusCode() + "\nREASON : "
					+ httpResponse.getStatusLine().getReasonPhrase());
			return null;
		}
	}

	public static HttpEntity makeRequest(HttpPost post) throws IOException {
		HttpResponse httpResponse = client.execute(post);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK_200) {
			return httpResponse.getEntity();
		} else {
			logger.severe("RESPONSE CODE : " + httpResponse.getStatusLine().getStatusCode() + "\nREASON : "
					+ httpResponse.getStatusLine().getReasonPhrase());
			return null;
		}
	}

	public static HttpEntity makeRequest(HttpPut put) throws IOException {
		HttpResponse httpResponse = client.execute(put);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK_200) {
			return httpResponse.getEntity();
		} else {
			logger.severe("RESPONSE CODE : " + httpResponse.getStatusLine().getStatusCode() + "\nREASON : "
					+ httpResponse.getStatusLine().getReasonPhrase());
			return null;
		}
	}

	public static HttpGet setGenericParams(HttpGet httpGet) {
		httpGet.setHeader("Authorization", ConnectionUtils.getEncodedAuthKey());
		httpGet.setHeader("Content-Type", "application/json");
		return httpGet;
	}

	public static HttpPost setGenericParams(HttpPost httpPost) {
		httpPost.setHeader("Authorization", ConnectionUtils.getEncodedAuthKey());
		httpPost.setHeader("Content-Type", "application/json");
		return httpPost;
	}

	public static HttpPut setGenericParams(HttpPut httpPut) {
		httpPut.setHeader("Authorization", ConnectionUtils.getEncodedAuthKey());
		httpPut.setHeader("Content-Type", "application/json");
		return httpPut;
	}
	
	public static String getcycleID() {
		return cycleID;
	}
	

	// Create Jira Test Cycle

	public static String createTestCycle(String name) throws URISyntaxException, IOException {
		URI uri = uriBuilder.setScheme("https")
				.clearParameters()
				.setHost("jira.myvest.com")
				.setPath(":8443" + createCycle)
				.build();

		HttpPost httpPost = new HttpPost(uri);
		logger.info(httpPost.toString());
		setGenericParams(httpPost);

		CreateTestCycleDOM createCycle = new CreateTestCycleDOM();
		createCycle.setName(name);
		String json = createCycle.cycle();
		logger.info(json);

		httpPost.setEntity(new StringEntity(json));
		HttpEntity httpEntity = makeRequest(httpPost);
		String content = EntityUtils.toString(httpEntity);
		logger.info(httpEntity.toString());
		EntityUtils.consume(httpEntity);

		JSONObject cycleObject = new JSONObject(content);
		cycleID = cycleObject.getString("id");
		System.out.println("Test-Cycle-ID " + cycleID);

		return cycleID;
	}

	// Create Jira Test Folder under Created Cycle

	public static void createTestFolder() throws URISyntaxException, IOException {
		URI uri = uriBuilder.setScheme("https")
				.clearParameters()
				.setHost("jira.myvest.com")
				.setPath(":8443" + createFolder)
				.build();

		HttpPost httpPost = new HttpPost(uri);
		logger.info(httpPost.toString());
		setGenericParams(httpPost);

		CreateTestFolderDOM createFolder = new CreateTestFolderDOM();
		createFolder.setName("MyVest-UI-Tests");
		createFolder.setCycleId(cycleID);

		String json = createFolder.folder();
		logger.info(json);

		httpPost.setEntity(new StringEntity(json));
		HttpEntity httpEntity = makeRequest(httpPost);
		String content = EntityUtils.toString(httpEntity);
		logger.info(httpEntity.toString());
		EntityUtils.consume(httpEntity);
		logger.info(content);

	}
	
	public static Integer getFolderID() throws URISyntaxException, IOException {
		URI uri = uriBuilder.setScheme("https")
				.clearParameters()
				.setHost("jira.myvest.com")
				.setPath(":8443" + getListOfFolders + cycleID + "/folders")
				.addParameter("projectId", "10000")
				.addParameter("versionId", "18828")
				.build();

		HttpGet httpGet = new HttpGet(uri);
		logger.info(httpGet.toString());
		setGenericParams(httpGet);
		
		HttpEntity httpEntity = makeRequest(httpGet);
		String content = EntityUtils.toString(httpEntity);
		logger.info(httpEntity.toString());
		EntityUtils.consume(httpEntity);
		logger.info(content);

		JSONArray jsonarray = new JSONArray(content);
		for(int i=0; i<jsonarray.length(); i++) {
		
			JSONObject folderIdObject = jsonarray.getJSONObject(i);
			folderId = folderIdObject.getInt("folderId");
			System.out.println("Test-Folder-ID " + folderId);
		
		}
		return folderId;
	}
	
	
	public static String getTestID(String JiraId) throws URISyntaxException, IOException {
		URI uri = uriBuilder.setScheme("https")
				.clearParameters()
				.setHost("jira.myvest.com")
				.setPath(":8443" + getTestCaseID + JiraId)
				.build();

		HttpGet httpGet = new HttpGet(uri);
		logger.info(httpGet.toString());
		setGenericParams(httpGet);
		
		HttpEntity httpEntity = makeRequest(httpGet);
		String content = EntityUtils.toString(httpEntity);
		logger.info(httpEntity.toString());
		EntityUtils.consume(httpEntity);
		logger.info(content);

		//JSONArray jsonarray = new JSONArray(content);
		//for(int i=0; i<jsonarray.length(); i++) {
		
			JSONObject jiraIdObject = new JSONObject(content);
			jiraId = jiraIdObject.getString("key");
			testId = jiraIdObject.getString("id");
			System.out.println("AMMO#--> " + jiraId);
			System.out.println("TestID--> " + testId);
		
		
		return testId;
	}

	
	public static void addTestCaseToFolder() throws URISyntaxException, IOException {
		URI uri = uriBuilder.setScheme("https")
				.clearParameters()
				.setHost("jira.myvest.com")
				.setPath(":8443" + addTestCaseToCycle)
				.build();

		HttpPost httpPost = new HttpPost(uri);
		logger.info(httpPost.toString());
		setGenericParams(httpPost);

		AddTestCaseToFolderDOM addTest = new AddTestCaseToFolderDOM();
		addTest.setCycleId(cycleID);
		addTest.setFolderId(folderId);
		addTest.setIssueId(testId);
		
		String json = addTest.putTestCase();
		logger.info(json);

		httpPost.setEntity(new StringEntity(json));
		HttpEntity httpEntity = makeRequest(httpPost);
		String content = EntityUtils.toString(httpEntity);
		logger.info(httpEntity.toString());
		EntityUtils.consume(httpEntity);

		parse(content);
		System.out.println("Test-Execution-Id " + executionKey);
	}

	
	
	public static String parse(String json) throws JsonProcessingException, IOException  {
	       JsonFactory factory = new JsonFactory();

	       ObjectMapper mapper = new ObjectMapper(factory);
	       JsonNode rootNode = mapper.readTree(json);  

	       Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.getFields();
	       while (fieldsIterator.hasNext()) {

	           Map.Entry<String,JsonNode> field = fieldsIterator.next();
	           System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
	           executionKey = field.getKey();
	           logger.info("executionKey--> " + executionKey);
	       }
		
		return executionKey;
	}
	
	public static void executeTestStatus(int statusCode) throws URISyntaxException, IOException {
		URI uri = uriBuilder.setScheme("https")
				.clearParameters()
				.setHost("jira.myvest.com")
				.setPath(":8443" + executeTests + executionKey + "/execute")
				.build();
		
		 HttpPut httpPut = new HttpPut(uri);
	        setGenericParams(httpPut);
	        String json = "{\"status\":" + Integer.toString(statusCode) + "}";
	        logger.info(json);
	        httpPut.setEntity(new StringEntity(json));
	        logger.info(httpPut.toString());
//	        HttpResponse httpResponse = client.execute(httpPost);
	        HttpResponse  httpResponse= client.execute(httpPut);
	        //HttpEntity responseEntity = httpResponse.getEntity();
	        //HttpEntity httpEntity = makeRequest(httpPut);
	        EntityUtils.toString( httpResponse.getEntity());
	        InputStream is = httpResponse.getEntity().getContent();
	        //bytes = IOUtils.toByteArray(is);
	        is.close();
	        //EntityUtils.consume(httpResponse);
	        System.out.println("IS -->" + is);
	        System.out.println(httpResponse.getStatusLine().getStatusCode());
//
//		HttpPost httpPost = new HttpPost(uri);
//		logger.info(httpPost.toString());
//		setGenericParams(httpPost);
//
//		ExecuteTestDOM executetest = new ExecuteTestDOM();
//		executetest.setStatus(statusCode);
//		String json = executetest.executeTestCase();
//		logger.info(json);
//
//		
//		
//		httpPost.setEntity(new StringEntity(json));
//		HttpEntity httpEntity = makeRequest(httpPost);
//        InputStream is = httpEntity.getContent();
//        is.close();
//        EntityUtils.consume(httpEntity);
		
//		String content = EntityUtils.toString(httpEntity);
//		logger.info(httpEntity.toString());
//		logger.info(content);
//		EntityUtils.consume(httpEntity);
	}
	
	//public static void main(String[] args) throws IOException, URISyntaxException {
//		createTestCycle();
//		createTestFolder();
//		getFolderID();
//		getTestID("AMMO-76027");
//		addTestCaseToFolder();
//		executeTestStatus(1);
//		System.out.println("Test");
		
//	}

}
