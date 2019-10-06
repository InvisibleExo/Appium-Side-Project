package Test_Setup;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.StringUtils;

public class AppPackageInventory {
	
	//Make a separate arrays for APK files and IPA files
	
	private File[] apkFileList;
	
	private HashMap<String, File> apkInstallOptions = new HashMap<String, File>();
	
	
	//IPA global variables
	
	AppPackageInventory(){
		setUpAPKKeys();
	}
	
	//APK functions
	public void createAPKList() throws ZipException, IOException {
		apkFileList =  new File("./APKDir/APKFiles").listFiles();
		apkInstallOptions = storeAndSortFiles(apkFileList, apkInstallOptions);
		apkInstallOptions = alternativeAPKSetup(apkInstallOptions);
	}
	
	public void setUpAPKKeys() {
		apkInstallOptions.put("arm64-v8a", null);
		apkInstallOptions.put("armeabi", null);
		apkInstallOptions.put("armeabi-v7a", null);
		apkInstallOptions.put("x86", null);
		apkInstallOptions.put("x86_64", null);
	}
	
	public File[] getAPKList() {
		return apkFileList;
	}
	
	public HashMap<String, File> getAPKOptions(){
		return apkInstallOptions;
	}
 	
	private HashMap<String, File> storeAndSortFiles(File[] fileList, HashMap<String, File> storageList) throws ZipException, IOException{
		ZipFile apkDetails;
		for(File apk : fileList) {
			apkDetails = new ZipFile(apk);
			for (String key : storageList.keySet()) {
				boolean hasMatchingAPKEntries = apkDetails.stream().map(ZipEntry::getName).anyMatch(name -> name.startsWith("lib/"+key+"/") 
						&& name.endsWith(".so"));
				System.out.println(key + ":" + hasMatchingAPKEntries);
				if (hasMatchingAPKEntries == true && storageList.get(key) == null) {
					storageList.put(key, apk);
				} else if (hasMatchingAPKEntries == true 
						&& (storageList.get(key) != null && storageList.get(key).length() > apk.length())) {
					storageList.replace(key, apk);
				}
			}
		}
		return storageList;
	}
	
	private HashMap<String, File> alternativeAPKSetup (HashMap<String, File> storageList){
		
		for(String key : storageList.keySet()) {
			if (storageList.get(key) == null) {
				switch(key) {
					case "arm64-v8a":
						if(storageList.get("armeabi-v7a") != null) {
							storageList.put(key, storageList.get("armeabi-v7a"));
						} 
						else if (storageList.get("armeabi") != null) {
							storageList.put(key, storageList.get("armeabi"));
						}
						else {
							System.out.println("APKs stored in APKDir don't support 32 or 64 bit ARM processor. \n"
									+ "Please provide an apk that at least supports 32 bit ARM processor.");
						}
						break;
					case "armeabi-v7a":
						if(storageList.get("armeabi") != null) {
							storageList.put(key, storageList.get("armeabi"));
						}
						else {
							System.out.println("APKs stored in APKDir don't support 32 or 64 bit ARM processor. \n"
									+ "Please provide an apk that at least supports 32 bit ARM processor.");
						}
						break;
					case "x86_64":
						if(storageList.get("x86") != null) {
							storageList.put(key, storageList.get("armeabi-v7a"));
						}
						else {
							System.out.println("APK stored in APKDir don't support 32 or 64 bit AMD processor. \n"
									+ "Please an apk that at least supports 32 bit AMD processor.");
						}
						break;
					default:
						break;
				}
			}
		}
		
		return storageList;
	}
	
	//IPA methods
	
	
	
	//Install Apps to Devices
	public void installAppToDevices(List<DesiredCapabilities> deviceList, HashMap<String, File> storageList) {
		deviceList.parallelStream().forEach((cap) -> {
			if(((String)cap.getCapability("platformName")).equals("Android")) {
				String deviceABI = "";
				
				Runtime rt = Runtime.getRuntime();
				try {
					Process p = rt.exec("adb -s " + ((String)cap.getCapability("udid")).trim() + " shell getprop ro.product.cpu.abi");
					BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
					
					String outputLine = "";
					
					while((outputLine = output.readLine()) != null) {
						if (StringUtils.isEmpty(outputLine)) {
							continue;
						}
						deviceABI = outputLine.trim();
						System.out.println(deviceABI);
					}
					
					System.out.println("Now installing: "+ storageList.get(deviceABI) + " to " + cap.getCapability("deviceName"));
					p = rt.exec("adb -s " + ((String)cap.getCapability("udid")).trim() + " install "+ storageList.get(deviceABI));
					p.waitFor();
						
				} catch (IOException e) {
					System.out.println("IOException detected");
					e.printStackTrace();
				} catch (InterruptedException e) {
					System.out.println("Unexpected interruption detected");
					e.printStackTrace();
				}
				
			}
		});
	}
	
	
	
}
