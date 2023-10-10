# Test automation framework

## Framework architecture intro
Framework was designed using Page Object pattern. All data that can be shared with other pages or eve apps saved in BasePage class.
All configuration and steps for cucumber scenarios is in StepDefinitions class. (could be splitted to different parts as well)

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
apk file was build for x86 arch type, Sauce Labs supports only one version with this arch type - 8.1.
rebuild apk file for newer android version to make tests passing on cloud.
Also, I left my credentials HARDCODED, to make tests run on my account. 
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

## P.S.
I use JSon only in one place, to demonstrate I know how to work with it. I prefer to leave all test data in feature files if possible.
Also, app sometimes crashes (my guess is due to old version it was build), I'd recommend to add retry and taking screenshots on failure, but today it would be too much for this task, so I decided to skip it