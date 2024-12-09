# O-MAT: On-Device Mobile Application Testing: Reproduction Process

This process provides all necessary steps to reproduce the results of the paper On-Device Mobile Application Testing (O-MAT).

## Project Installation

1) Clone the project using the GitHub repository: https://anonymous.4open.science/r/OffDeviceTesting-4606
2) Open the project in your preferred IDE (e.g., Android Studio).
3) The Gradle files will automatically build upon opening the project (if using Android Studio).
4) Once the project has been successfully built, the installation is complete.

## Installing the Evaluated Application

1) Connect a mobile device to your computer. This can be a physical device connected via USB or over the network, or an emulator.
2) Use the command ```adb devices``` in your terminal to verify that the device is connected.
3) Go to the On-Device Mobile Application Testing repository: https://anonymous.4open.science/r/OndeviceMAT-861C.
4) Download the evaluated/applications folder.
6) This folder contains nine applications. Install each application on the mobile device using the command: ```adb install <application_name>.apk```

## Running the Test Cases

1) Manually launch the application you want to test on the mobile device.
2) Once the application is fully launched, navigate in your IDE to the test case intended for the application you launched.
3) Launch the test case by executing the test case class in that file.
