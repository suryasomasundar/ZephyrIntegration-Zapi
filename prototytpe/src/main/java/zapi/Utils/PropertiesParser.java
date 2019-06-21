package zapi.Utils;


import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesParser {

	public static final String JIRA_URL = "https://jira.myvest.com:8443";
	public static final String JIRA_PORT = "8443";
	public static final String JIRA_HOST = "jira.myvest.com";

	static Logger logger = Logger.getLogger(PropertiesParser.class.getName());
	static Properties properties;
	static InputStream inputStream;

	public static String getJiraUrl() {
		return JIRA_URL;
	}
	public static String getJiraPort() {
		return JIRA_PORT;
	}
	public static String getJiraHost() {
		return JIRA_HOST;
	}
}