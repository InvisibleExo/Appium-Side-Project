package Test_Setup;

import java.io.IOException;

import javax.xml.transform.TransformerConfigurationException;

public class DriverTest {
	
	protected AppiumDriverSetup createDrivers = new AppiumDriverSetup();
	
	protected XMLMaker makeFile = new XMLMaker();

	public static void main(String [] args) throws TransformerConfigurationException, IOException, InterruptedException {
		System.out.println("Setting up drivers:");
		new DriverTest().driverFileSetup();
	}
	
	protected void driverFileSetup() throws IOException, TransformerConfigurationException, InterruptedException {
		createDrivers.makeList();
		createDrivers.installApptoDevices();
		makeFile.setupDriverXMLFile(createDrivers.getActiveList());
		makeFile.createDriverFile();
	}
	
}
