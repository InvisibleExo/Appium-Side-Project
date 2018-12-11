package AppiumDriverSetUp_Lib;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLMaker {
	
	
	
	public DocumentBuilderFactory docDriverSetup;
	
	public DocumentBuilder driverSetup;
	
	public int connectedDevices = 0;
	
	public Document doc;
	
	
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	
	Transformer transformer;
	
	
	public void setupDriverXMLFile(List <DesiredCapabilities> driverList) {
		
		System.out.println("List size: "+ driverList.size());
		try {
			
			docDriverSetup = DocumentBuilderFactory.newInstance();
		
			driverSetup = docDriverSetup.newDocumentBuilder();
			
			doc = driverSetup.newDocument();
			
			transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			
			Element suiteElement = doc.createElement("suite");
			suiteElement.setAttribute("name", "All-tests");
			
			for(DesiredCapabilities driverCap: driverList) {
			
				Element rootElement = doc.createElement("test");
				suiteElement.appendChild(rootElement);
				rootElement.setAttribute("name", (String) driverCap.getCapability("deviceId"));
				
				Element deviceNameEle = doc.createElement("parameter");
				deviceNameEle.setAttribute("name", "deviceName");
				deviceNameEle.setAttribute("value", (String) driverCap.getCapability("deviceId"));
				rootElement.appendChild(deviceNameEle);
				
				Element platformEle = doc.createElement("parameter");
				platformEle.setAttribute("name", "platform");
				platformEle.setAttribute("value", (String)driverCap.getCapability("platformName"));
				rootElement.appendChild(platformEle);
				
				Element udidEle = doc.createElement("parameter");
				udidEle.setAttribute("name", "udid");
				udidEle.setAttribute("value", (String) driverCap.getCapability("udid"));
				rootElement.appendChild(udidEle);
				
				Element urlPort = doc.createElement("parameter");
				urlPort.setAttribute("name", "URL");
				urlPort.setAttribute("value", (String)driverCap.getCapability("appiumURL"));
				rootElement.appendChild(urlPort);
				
				Element devicePort = doc.createElement("parameter");
				
				if(((String)driverCap.getCapability("platformName")).
						toLowerCase().contains("android")) {
						devicePort.setAttribute("name", "port");
						devicePort.setAttribute("value", driverCap.getCapability("systemPort")+"");
						
				}
				if (((String)driverCap.getCapability("platformName")).
						toLowerCase().contains("ios")) {
						devicePort.setAttribute("name", "port");
						devicePort.setAttribute("value", (String)driverCap.getCapability("wdaLocalPort"));
				}
				
				rootElement.appendChild(devicePort);
				
				Element packages = doc.createElement("packages");
				rootElement.appendChild(packages);
				Element packageName = doc.createElement("package");
				packageName.setAttribute("name", "BaseTest");
				packages.appendChild(packageName);
				
				
				connectedDevices++;
			}	
			suiteElement.setAttribute("parallel", "tests");
			suiteElement.setAttribute("thread-count", connectedDevices+"");
			doc.appendChild(suiteElement);
			
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public void createDriverFile() throws TransformerConfigurationException, InterruptedException, IOException {
		
		DOMSource source = new DOMSource(doc);
		
		FileOutputStream streamNewFile = new FileOutputStream("./drivers.xml");
		PrintWriter pw = new PrintWriter(streamNewFile);
		StreamResult result = new StreamResult(pw);
		
		
		try {
			
			transformer.transform(source, result);
			result.getWriter().close();
			
			System.out.println("File Updated");
	
			
		
		} catch (TransformerException e) {
			e.printStackTrace();
			System.out.println("Error updating the file");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to close output stream");
		}
		
	}
	
}
