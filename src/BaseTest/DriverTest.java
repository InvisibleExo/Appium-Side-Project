package BaseTest;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.TransformerConfigurationException;

import AppiumDriverSetUp_Lib.AppiumDriverSetup;
import AppiumDriverSetUp_Lib.XMLMaker;

public class DriverTest {
	
	public AppiumDriverSetup createDrivers = new AppiumDriverSetup();
	
	public XMLMaker makeFile = new XMLMaker();
	//Call this file from cmd:
	//mvn exec:java -Dexec.mainClass="BaseTest.DriverTest" -Dplat=chrome
	public static void main(String [] args) throws TransformerConfigurationException, IOException, InterruptedException {
		System.out.println("Setting up drivers:");
		
		DriverTest startTest = new DriverTest();
		startTest.driverFileSetup();
		startTest.runSuite();
		
		startTest = null;
	}
	
	public void driverFileSetup() throws IOException, TransformerConfigurationException, InterruptedException {
		createDrivers.makeList();
		makeFile.setupDriverXMLFile(createDrivers.getActiveList());
		makeFile.createDriverFile();
	}
	
	public void runSuite() throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		String cmd = "cmd /c mvn test -Dplat="+System.getProperty("plat");
		Process p = rt.exec(cmd);
		InputStream input = p.getInputStream();
		testFeed(input, System.out);
		p.waitFor();
	}
	
	public void testFeed(InputStream in, OutputStream out) throws IOException {
		while (true) {
			int c = in.read();
			if (c == -1) {
				break;
			}
			out.write((char)c);
		}
	}

}
