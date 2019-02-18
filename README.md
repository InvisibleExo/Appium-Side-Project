# Appium-Side-Project

The focus of this project to create an easy Test Automation Framework for Mobile devices on either physical or emulator to run from a PC. This Base library, (AppiumDriversetUp_Lib,) is designed to create Appium Drivers for both Android and iPhone, based on any number of devices connected or active emulators, and simplify the proper setup to have phones running based on json or any other data format files containing info for the mobile devices to run automation. 

An example is also provided to allow this framework to test either app or browser depending on user's preference when executing the command. 

(Currently, this rough draft of code only supports Android at this time.The project now supports dynamic configuration based on any of the devices connected to PC.)

(Due to the difficulties and requirements to run iPhone devices from Windows PC, (such as requirement for a Mac to XCUITestDrivers for the latest iPhones,) it may not be possible to run and automate iPhone from a PC. Will continue to research to attempt finding a solution to automate iPhones from Windows.)

In order to run this framework, run: mvn exec:java -Dexec.mainClass="BaseTest.DriverTest" -Dplat=<enter browser type or type "App" to run on App Side>
(Still working on how to support App-based automation setup.)

Let the fun begin. 

--InvisibleExo