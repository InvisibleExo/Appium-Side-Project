package Test_Setup;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.TransformerConfigurationException;

public class DriverTest {
	
	public AppiumDriverSetup createDrivers = new AppiumDriverSetup();
	
	public XMLMaker makeFile = new XMLMaker();
	//Call this file from cmd:
	//mvn exec:java -Dexec.mainClass="BaseTest.DriverTest" -Dexec.args="-Dplat=chrome -Dpackage=Brower_Test -Dgetlayout=yes" -Dpackage="Browser_Test"
	public static void main(String [] args) throws TransformerConfigurationException, IOException, InterruptedException {
		System.out.println("Setting up drivers:");
		
		DriverTest startTest = new DriverTest();
		startTest.driverFileSetup();
		startTest.runSuite(args);
		
		startTest = null;
	}
	
	public void driverFileSetup() throws IOException, TransformerConfigurationException, InterruptedException {
		createDrivers.makeList();
		createDrivers.installApptoDevices();
		makeFile.setupDriverXMLFile(createDrivers.getActiveList());
		makeFile.createDriverFile();
	}
	
	public void runSuite(String args[]) throws IOException, InterruptedException {
		int additionArgCount = 0;
		String additionArgLine = "";
		for (;additionArgCount < args.length; additionArgCount++) {
			additionArgLine += " " + args[additionArgCount];
		}
		Runtime rt = Runtime.getRuntime();
		String cmd = "cmd /c mvn test" + additionArgLine;
		Process p = rt.exec(cmd);
		InputStream input = p.getInputStream();
		testFeed(input, System.out);
		p.waitFor();
		p.getInputStream().close();
		p.getOutputStream().close();
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
