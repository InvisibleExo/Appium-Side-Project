# Appium-Side-Project
A mobile UI automation test framework with a dynamic testng.xml setup.

---

--Requirements:

-`Java 8 or Higher`

-`Android Studio SDK`

-`Appium` (at least beta version 14) 

-`Maven`(Dependencies required(By groupID): `org.seleniumhq.selenium`(artifactIds: `selenium-remote-driver` and `selenium-java`), `org.testng, io.appium`, and `com.googlecode.json-simple` 

---

The focus of this project is to create a UI Mobile Automation Test Framework for either physical devices or emulator to run from any PC without the hassle of manually enter device udid or specify whether application is browser or mobile app during setup. 

This Base library, (Test_Setup, MobileBaseScreen, and BaseTest), is designed to create Appium Drivers for Android, based on any number of devices connected or active emulators.

An example is also provided in the master branch to show how this framework would test for either app or browser. This framework can run any test package you wish to pass in. Your selected test package must be set as an extention of BaseTest class(BaseTest package). For your test library package, it must be set as an extensions of BaseScreen class(MobileBaseScreen package). 

The MobileBaseScreen package's `BaseScreen` class provides a list of functions which operate basic functions, such as swipes, go back/home or control panel from device, and opening/selecting notifications.

The code currently supports Android devices for both app and Chrome Browser.

In order to run tests with your app, you must place your app's apk file  in `APKFiles` directory. Location: `./APKDir/APKFiles` You must also specify your appPackage and appActivity info in json file called `APKStartPoint`. Location: `./APKDir`

This project now supports simple batch and bash scripts to simplify the input entered and execution. 
For Example: `Start_TestSuite<.cmd or .sh> app(or browser name) App_Test(Test script_package) + anymore additional parameters`

**Note**: For any additional parameters when using batch script use `\"` for the value of system property.

---
Additional Inputs available:

-Install: You now have the option have all the devices the option to install APK files during setup. 

Arg:`-Dinstall=yes`

-Getlayout: For any errors encountered during testing you have the option to retrieve the source layout on the web page/widget from where the error occurred.

 Arg:`-Dgetlayout=yes`
 
-TestClass: To avoid running all the tests classes from your test package, you can now specify which class you want to focus. In order to specify more than one class, you much use semi-colon(;) between test classes.
 
 Arg: `-Dtestclass=(Package Name.Class Name);(Package Name.A Different Class Name)`

---
Let the fun begin. 

--InvisibleExo
