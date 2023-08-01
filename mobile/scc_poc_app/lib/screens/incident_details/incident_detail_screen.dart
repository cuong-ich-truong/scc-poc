import 'package:flutter/material.dart';
import 'dart:async';
import 'package:hooks_riverpod/hooks_riverpod.dart';
import 'package:scc_poc_app/screens/send_note_screen.dart';
import 'incident_details_item.dart';
import 'package:scc_poc_app/services/scc_incident_details_provider.dart';
import 'package:flutter_hooks/flutter_hooks.dart';

class IncidentDetailsScreen extends HookConsumerWidget {
  const IncidentDetailsScreen({super.key, required this.incidentId});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String incidentId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final incidentDetails = ref.watch($incidentDetailsProvider).incident;
    final isLoading = ref.watch($incidentDetailsProvider).isLoading;
    final notifier = ref.watch($incidentDetailsProvider.notifier);

    useEffect(() {
      Future(() {
        notifier.getIncidentsDetails(incidentId);
      });
      return null;
    }, []);
    
    return MaterialApp(
      theme:  ThemeData(
         primaryColor: const Color.fromRGBO(24, 24, 24, 1.0),
        canvasColor: const Color.fromRGBO(46, 49, 49, 1.0),
        brightness: Brightness.dark,
      ),
    home:  Scaffold(
      appBar: AppBar(
        title:  const Text('INCIDENT DETAILS'),
      ),
    body: isLoading ? const Center(
              child: CircularProgressIndicator(),)  : Container(alignment: Alignment.topCenter, 
                    child: incidentDetails.id != null ? IncidentDetailsItem(incident: incidentDetails) : const Text("NO INCIDENT INFORMATION"),),
    floatingActionButton: ElevatedButton(child: const Text('Send note'), onPressed: () {
      _goToSendNoteScreen(context, incidentId);
      }),
      )
    );
  }

  void _goToSendNoteScreen(BuildContext context, String incidentId) {
    Navigator.push(
        context, 
        MaterialPageRoute(builder: (context) => SendNoteScreen(incidentId: incidentId))
      );
  }
}