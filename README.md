# Appium-Side-Project

The focus of this project to create an easy Test Automation Framework for Mobile devices on either physical or emulator to run from a PC. This Base library, (Test_Setup, MobileBaseScreen, and BaseTest), is designed to create Appium Drivers for both Android and iPhone, based on any number of devices connected or active emulators, and simplify the proper setup to have phones running based on json or any other data format files containing info for the mobile devices to run automation. 

An example is also provided to allow this framework to test either app or browser depending on user's preference when executing the command. This framework can also be specified which Test packages you wish to run. 

(Currently, this draft of code only supports Android for both app and browser at this time.The project now supports dynamic configuration based on any of the devices connected to PC for both app and browser.)

In order to run mobile app test scripts, you must place your apk file you wish to test in APKFiles. (location: /APKDir/APKFiles) You must also specify your appPackage and appActivity info in json file called APKStartPoint. (location: /APKDir)

(Due to the difficulties and requirements to run iPhone devices from Windows PC, (such as requirement for a Mac to XCUITestDrivers for the latest iPhones,) it may not be possible to run and automate iPhone from a PC. Will continue to research to attempt finding a solution to automate iPhones from Windows.)

This project now supports simple batch and bash scripts to simply the input entered and execution. 
Input required: (example: Start_TestSuite<.cmd or .sh> app App_Test + anymore additional inputs)
Note: If you're executing Start_TestSuite from bash any additional parameters that require double quotes(") need to be entered as (\").

Additional Inputs available:
-Install: You now have the option have all the devices the option to install APK files during setup. 
Argument parameter:-Dinstall="yes"
-Getlayout: For any errors encountered during testing you have the option to retrieve the source layout on the web page/widget from where the error occured.
 Argument parameter:-Dgetlayout="yes"


Let the fun begin. 

--InvisibleExo