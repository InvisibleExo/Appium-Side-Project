# Appium-Side-Project

The purpose of this project to create an easy Test Automation Framework for Mobile devices on either physical or emulator. This Base library is designed to create Appium Drivers for both Android and iPhone, based on any number of devices connected or active emulators, and simplify the setup proper setup to have phones running based on json or any other data format files containing info for the mobile devices required for running mobile automation. 

An example is also provided to allow this framework to test either app or browser depending on user's preference when executing the command. 

(Currently, this rough draft of code only supports Android at this time. Still working on how to support iPhone devices. The project now supports dynamic configuration based on any of the devices connected to PC.)

To run this project, run: mvn exec:java -Dexec.mainClass="BaseTest.DriverTest" -Dplat=<enter browser type or type "App" to run on App Side>
(Still working on how to support App-based automation setup)

Let the fun begin. 

--Tony L.