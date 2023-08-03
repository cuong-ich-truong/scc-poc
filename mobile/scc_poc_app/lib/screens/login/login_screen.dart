import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:scc_poc_app/screens/incident_details/incident_detail_screen.dart';
import 'package:scc_poc_app/screens/incidents_list/incidents_list_screen.dart';
import '../../firebase_options.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:scc_poc_app/services/scc_send_token_provider.dart';

class LoginScreen extends ConsumerStatefulWidget {
  const LoginScreen({Key? key}): super(key: key);

  @override
  LoginScreenState createState() => LoginScreenState();
}

class LoginScreenState extends ConsumerState<LoginScreen> {

  String fcmToken = "";
  Future<void> setupInteractedMessage() async {
    // Get any messages which caused the application to open from
    // a terminated state.
    await Firebase.initializeApp(
      options: DefaultFirebaseOptions.currentPlatform,
    );

    FirebaseMessaging messaging = FirebaseMessaging.instance;

    await messaging.requestPermission(
      alert: true,
      announcement: false,
      badge: true,
      carPlay: false,
      criticalAlert: false,
      provisional: false,
      sound: true,
    );

    fcmToken = await FirebaseMessaging.instance.getToken() ?? "";
    await FirebaseMessaging.instance.setAutoInitEnabled(true);

    // Get any messages which caused the application to open from
    // a terminated state.
    RemoteMessage? initialMessage =
        await FirebaseMessaging.instance.getInitialMessage();

    // If the message also contains a data property with a "type" of "chat",
    // navigate to a chat screen
    if (initialMessage != null) {
      _handleMessage(initialMessage);
    }

    // Also handle any interaction when the app is in the background via a
    // Stream listener
    FirebaseMessaging.onMessageOpenedApp.listen(_handleMessage);
    FirebaseMessaging.onMessage.listen((RemoteMessage message) {
      _handleMessage(message);
    });
  }

  void _handleMessage(RemoteMessage message) {
     String? incidentId = message.data['incident_id'];

     if (incidentId != null) {
       _goToDetailScreen(incidentId);
     }
  }

  @override
  void initState() {
    super.initState();
    setupInteractedMessage();
  }

  void _goToIncidentsListScreen(BuildContext context, String guardId) {
    final notifier = ref.watch($sendTokenProvider.notifier);
    notifier.sendToken(guardId, fcmToken);
    Navigator.push(
          context, 
          MaterialPageRoute(builder: (context) => IncidentsListScreen(guardId: guardId))
    );
  }

  void _goToDetailScreen(String incidentId) {
    Navigator.push(
        context, 
        MaterialPageRoute(builder: (context) => IncidentDetailScreen(incidentId: incidentId))
      );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme:  ThemeData(
         primaryColor: const Color.fromRGBO(24, 24, 24, 1.0),
        canvasColor: const Color.fromRGBO(46, 49, 49, 1.0),
        brightness: Brightness.dark,
      ),
    home:  Scaffold(
      appBar: AppBar(
        title:  const Text('Login by choosing a guard'),
        automaticallyImplyLeading: true,
      ),
    body: ListView(children: [
      ElevatedButton(onPressed: () {
        _goToIncidentsListScreen(context, "gua_1");
      }, child: const Text('CHRIS')),
      ElevatedButton(onPressed: () {
        _goToIncidentsListScreen(context, "gua_1");
      }, child: const Text('ALEX')),
      ElevatedButton(onPressed: () {
        _goToIncidentsListScreen(context, "gua_1");
      }, child: const Text('JOHN')),
      ],)
    ));
  }
}
