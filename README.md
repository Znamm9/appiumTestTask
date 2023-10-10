# Test automation framework

## To spin the tests locally do next

Start appium server with endpoint 127.0.0.1/wd/hub.

### Create virtual device (I used Android 11)
Create system variable for your device name like below (use your id instead of emulator-5554)
```bash
export deviceid=emulator-5554
```
hint to get UDID use command
```bash
adb devices
```

Run next command in your terminal:

```bash
mvn test
```

## To spin the tests in cloud (Sauce Labs) do next:
_____________________
### !!!!important!!!!
apk file was build for old android version, 7.1.1 android version is not supported with Sauce Labs anymore.
rebuild apk file for newer android version to maek tests passing on cloud. 
____________________
add your Sauce Labs username to system variables (name is fake, so paste your name instead)
```bash
export SAUCENAME=oauth-v.rudenko106-12345
```
change "oauth-v.rudenko106-12345" for your sauce labs username

add your Sauce Labs key to system variables (key is face so paste your key instead)
```bash
export SAUCEKEY=7ccdc9d5-8cda-4a68-9959-890592612345
```

```bash
mvn -Dremote=true test
```