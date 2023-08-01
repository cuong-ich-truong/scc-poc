import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/services.dart';

abstract class NetworkInfo {
  Future<bool> get isConnected;

  Future<bool> checkConnection();
}

class DefaultNetworkInfo implements NetworkInfo {
  final Connectivity connectionChecker;

  DefaultNetworkInfo(this.connectionChecker);

  @override
  Future<bool> get isConnected => checkConnection();

  @override
  Future<bool> checkConnection() async {
    ConnectivityResult connectivityResult;
    try {
      connectivityResult = await (connectionChecker.checkConnectivity());
    } on PlatformException {
      return false;
    }
    if (connectivityResult == ConnectivityResult.none ||
        (connectivityResult != ConnectivityResult.mobile &&
            connectivityResult != ConnectivityResult.wifi)) {
      return false;
    } else {
      return true;
    }
  }
}
