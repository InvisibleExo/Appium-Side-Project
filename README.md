# Appium-Side-Project
A mobile UI automation framework with a dynamic testng.xml setup


--Requirements:


-Java 8(Java 9 or higher will not work with Android Stuido sdk) 


-Android Studio SDK


-Appium (at least beta version 14) 


-Maven(Dependencies required(By groupID): org.seleniumhq.selenium(artifactIds selenium-remote-driver, selenium-java), org.testng, io.appium, com.googlecode.json-simple) 

The focus of this project is to create a UI Test Mobile Automation Framework for either physical devices or emulator to run from a PC without the hassle to manually enter device udid or specify whether application is browser or mobile app during setup. 

This Base library, (Test_Setup, MobileBaseScreen, and BaseTest), is designed to create Appium Drivers for Android, based on any number of devices connected or active emulators, and simplify the setup. 

An example is also provided to allow this framework to test either app or browser depending on user's preference when executing. This framework can also be specified which Test packages you wish to run. Test packages must extend from BaseTest in BaseTest package. Test Libraries must extend from BaseScreen in MobileBaseScreen package.

(Currently, this draft of code only supports Android for both app and browser(Chrome).)

In order to run tests for your app, you must place your apk file you wish to test in APKFiles directory. (location: ./APKDir/APKFiles) You must also specify your appPackage and appActivity info in json file called APKStartPoint. (location: ./APKDir)

(Due to the difficulties and requirements to run iPhone devices from Windows PC, (such as requirement for a Mac to XCUITestDrivers for the latest iPhones,) it may not be possible to run and automate iPhone from a PC. Will continue to research to attempt finding a solution to automate iPhones from Windows.)

This project now supports simple batch and bash scripts to simplify the input entered and execution. 
Input required: (example: Start_TestSuite<.cmd or .sh> app(or browser name) App_Test(Test script_package) + anymore additional inputs)
Note: If you're executing Start_TestSuite from bash any additional parameters that require double quotes(") need to be entered as (\").

Additional Inputs available:

-Install: You now have the option have all the devices the option to install APK files during setup. 

Argument parameter:-Dinstall="yes"

-Getlayout: For any errors encountered during testing you have the option to retrieve the source layout on the web page/widget from where the error occurred.

 Argument parameter:-Dgetlayout="yes"
 
 -TestClass: To avoid running all the tests classes from your test package, you can now specify which class you want to focus. In order to specify more than one class, you much use semi-colon(;) between test classes.
 
 Argument parameter: -Dtestclass="(Package Name.Class Name);(Package Name.A Different Class Name)"



Let the fun begin. 

--InvisibleExo