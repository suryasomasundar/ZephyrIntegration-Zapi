package zapi.Utils;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class ResultParser {
	public static int iStatus_PASS = 1;
	public static int iStatus_FAIL = 2;
	public static int statusCode = 0;

	public static int parse() {
		String path = ("/Users/sps/Desktop/Hackathon-Workspace/prototytpe/test-output/testng-results.xml");
		;
		File testNgResultXmlFile = new File(path);

		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// Build Document
		Document document = null;
		try {
			document = builder.parse(testNgResultXmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Normalize the XML Structure;
		document.getDocumentElement().normalize();

		String Total = document.getDocumentElement().getAttribute("total");
		String Passed = document.getDocumentElement().getAttribute("passed");
		String Failed = document.getDocumentElement().getAttribute("failed");
		String Skipped = document.getDocumentElement().getAttribute("Skipped");

		System.out.println("Total : " + Total);
		System.out.println("Passed : " + Passed);
		System.out.println("Failed : " + Failed);
		System.out.println("Skipped : " + Skipped);

		if (Passed.contentEquals("1")) {
			return statusCode = iStatus_PASS;
		}
		if (Failed.contentEquals("1")) {
			return statusCode = iStatus_FAIL;
		}

		NodeList tMethods = document.getElementsByTagName("test-method");

		for (int temp = 0; temp < tMethods.getLength(); temp++) {
			Node node = tMethods.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				// Get parent element to capture suite name
				Element suiteElement = (Element) eElement.getParentNode();
				System.out.println("Suite: " + suiteElement.getAttribute("name"));
				// Get test case name
				System.out.println("Name: " + eElement.getAttribute("name"));
				// Get test case status
				System.out.println("Status: " + eElement.getAttribute("status"));
				// Get test case duration
				System.out.println("Duration: " + eElement.getAttribute("duration-ms"));
				// Get exception message if exist
				Node eNode = eElement.getElementsByTagName("exception").item(0);
				Element exceptionNode = (Element) eNode;
				if (exceptionNode != null) {
					System.out.println("Error Message: " + exceptionNode.getAttribute("class"));
				} else {
					System.out.println("Error Message: ");
				}

			}
		}
		return statusCode;
	}

	public static void main(String[] args) {
		parse();
		System.out.println(statusCode);

	}

}
