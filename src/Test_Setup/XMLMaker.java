package Test_Setup;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
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
	
	protected DocumentBuilderFactory docDriverSetup;
	
	protected DocumentBuilder driverSetup;
	
	public int connectedDevices = 0;
	
	protected Document doc;
	
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
			
			Collections.shuffle(driverList);
			
			Element suiteElement = doc.createElement("suite");
			suiteElement.setAttribute("name", "All-tests");
			
			File[] testPkgList = null;
			int testPkgListSize = 0;
			int devicePerSplitLimit = -1;
			int perSplitCount = -1;
			
			
			if(System.getProperty("split") != null && System.getProperty("split").equalsIgnoreCase("yes")) {
				testPkgList = new File("./test/"+System.getProperty("package")).listFiles();
				testPkgListSize = testPkgList.length;
				System.out.println("test pkg size: "+ testPkgListSize);
				devicePerSplitLimit = testPkgList.length/driverList.size();
				perSplitCount = 0;
			}
			
			for(DesiredCapabilities driverCap: driverList) {
			
			
				Element testElement = doc.createElement("test");
				suiteElement.appendChild(testElement);
				testElement.setAttribute("name", (String) driverCap.getCapability("deviceId"));
				
				Element deviceNameEle = doc.createElement("parameter");
				deviceNameEle.setAttribute("name", "deviceName");
				deviceNameEle.setAttribute("value", (String) driverCap.getCapability("deviceId"));
				testElement.appendChild(deviceNameEle);
				
				Element platformEle = doc.createElement("parameter");
				platformEle.setAttribute("name", "platform");
				platformEle.setAttribute("value", (String)driverCap.getCapability("platformName"));
				testElement.appendChild(platformEle);
				
				Element udidEle = doc.createElement("parameter");
				udidEle.setAttribute("name", "udid");
				udidEle.setAttribute("value", (String) driverCap.getCapability("udid"));
				testElement.appendChild(udidEle);
				
				Element urlPort = doc.createElement("parameter");
				urlPort.setAttribute("name", "URL");
				urlPort.setAttribute("value", (String)driverCap.getCapability("appiumURL"));
				testElement.appendChild(urlPort);
				
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
				
				testElement.appendChild(devicePort);
				
				
				if(System.getProperty("testclass") != null) {
					Element classes = doc.createElement("classes");
					testElement.appendChild(classes);
					String[] classNames = System.getProperty("testclass").split(";");
					for(String className: classNames) {
						Element classTag = doc.createElement("class");
						classTag.setAttribute("name", className);
						classes.appendChild(classTag);
					}
				}
				
				else if(System.getProperty("split") != null && System.getProperty("split").equalsIgnoreCase("yes")) {
					Element classes = doc.createElement("classes");
					testElement.appendChild(classes);
					while(perSplitCount < devicePerSplitLimit) {
						Element classTag = doc.createElement("class");
						classTag.setAttribute("name", System.getProperty("package")+"."+testPkgList[testPkgList.length-testPkgListSize].getName().replace(".java", ""));
						classes.appendChild(classTag);
						testPkgListSize--;
						perSplitCount++;
					}
					perSplitCount = 0;
					if(driverList.size() == (connectedDevices + 1)){
						while(testPkgListSize-1 >= 0) {
							Element classTag = doc.createElement("class");
							classTag.setAttribute("name", System.getProperty("package")+"."+testPkgList[testPkgList.length-testPkgListSize].getName().replace(".java", ""));
							classes.appendChild(classTag);
							testPkgListSize--;
						}
					} 
					
				}
				else {
					Element packages = doc.createElement("packages");
					testElement.appendChild(packages);
					String testPackageName = System.getProperty("package");
					Element browserOrAppTestPackage = doc.createElement("package");
					browserOrAppTestPackage.setAttribute("name", testPackageName);
					packages.appendChild(browserOrAppTestPackage);
				}
						
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
