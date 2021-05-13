# Food_Delivery_ug
if incase google service changes in the current time:
Sign in to Firebase, then create your project
Add a name for your project and select the appropriate settings, then click on Create project.
Click Settings icon, then select Project settings
In the Your apps card, select the platform for the app you want created.
Follow the required steps to add Firebase to your Android app. Here are the steps:
Step 1 - Register app
Package name : com.example.fooddelivery
Create local keystore file from command line : keytool -genkey -v -keystore ~/.android/debug.keystore -alias androiddebugkey -keyalg RSA -keysize 2048 -validity 10000
Get the local debug certificate fingerprint : keytool -list -v -alias androiddebugkey -keystore ~/.android/debug.keystore
Step 2 - Download config file google-services.json
Step 3 - Move google-services.json to {app root folder}/fooddelivery/
Step 4 - Edit {app root folder}/build.gradle, change classpath to com.google.gms:google-services:version (or whatever google tells you)

This Source consists two model:
1. Customer app
2. Restaurater app
Build Both module appropriately.


