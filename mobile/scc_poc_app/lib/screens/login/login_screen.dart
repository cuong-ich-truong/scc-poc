import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:scc_poc_app/screens/incident_details/incident_detail_screen.dart';
import 'package:scc_poc_app/screens/incidents_list/incidents_list_screen.dart';
import '../../firebase_options.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_hooks/flutter_hooks.dart';

class LoginScreen extends ConsumerStatefulWidget {
  const LoginScreen({Key? key}): super(key: key);

  @override
  LoginScreenState createState() => LoginScreenState();
}

class LoginScreenState extends ConsumerState<LoginScreen> {

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

    final fcmToken = await FirebaseMessaging.instance.getToken();
    await FirebaseMessaging.instance.setAutoInitEnabled(true);
    print("FCMToken $fcmToken");

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
     // TODO: Implement logic of getting incident id from push notification message later
    // _goToDetailScreen(context, message.data['incident_id']);
    _goToDetailScreen(context, 'fb267025-1c26-4a0b-89e5-2a885a1cead2');
  }

  @override
  void initState() {
    super.initState();
    setupInteractedMessage();
    // "ref" can be used in all life-cycles of a StatefulWidget.
  }

  void _goToIncidentsListScreen(BuildContext context, String guardId) {
  Navigator.push(
        context, 
        MaterialPageRoute(builder: (context) => IncidentsListScreen(guardId: guardId))
      );
  }

  void _goToDetailScreen(BuildContext context, String incidentId) {
    Navigator.push(
        context, 
        MaterialPageRoute(builder: (context) => IncidentDetailsScreen(incidentId: incidentId))
      );
  }

  @override
  Widget build(BuildContext context) {
    // final incidents = ref.watch($incidentsProvider).incidents;
    // final isLoading = ref.watch($incidentsProvider).isLoading;
    // final notifier = ref.watch($incidentsProvider.notifier);

    // useEffect(() {
    //   Future(() {
    //     notifier.getIncidents('123');
    //   });
    //   return null;
    // }, []);
    return MaterialApp(
      theme:  ThemeData(
         primaryColor: const Color.fromRGBO(24, 24, 24, 1.0),
        canvasColor: const Color.fromRGBO(46, 49, 49, 1.0),
        brightness: Brightness.dark,
      ),
    home:  Scaffold(
      appBar: AppBar(
        title:  const Text('Login by choosing a guard'),
      ),
    body: ListView(children: [
      ElevatedButton(onPressed: () {
        _goToIncidentsListScreen(context, "123");
      }, child: const Text('CHRIS')),
      ElevatedButton(onPressed: () {
        _goToIncidentsListScreen(context, "123");
      }, child: const Text('ALEX')),
      ElevatedButton(onPressed: () {
        _goToIncidentsListScreen(context, "123");
      }, child: const Text('JOHN')),
      ],)
    ));
  }
}
