
# SCC POC Flutter app

## Prerequisites

* Flutter

## Setup:

## Setup firebase

## Install the CLI if not already done so:

```bash
dart pub global activate flutterfire_cli
 ```

## Run the `configure` command, select a Firebase project and platforms:
```bash
flutterfire configure
 ```

 ## Generate the APK file

Start from root folder, navigate to mobile/scc_poc_app folder then run the flutter build command:
```bash
cd mobile/scc_poc_app
```

 ```bash
flutter build apk
 ```
The release apk file will be generated at mobile/scc_poc_app/build/app/outputs/flutter-apk/app-release.apk
