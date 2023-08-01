import 'package:flutter/material.dart';
import 'package:scc_poc_app/models/incident.dart';
import 'package:scc_poc_app/screens/incident_details/incident_detail_screen.dart';
import 'package:scc_poc_app/services/scc_incidents_provider.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';
import 'package:flutter_hooks/flutter_hooks.dart';
import 'incident_item.dart';

class IncidentsListScreen extends HookConsumerWidget {
  const IncidentsListScreen({super.key, required this.guardId});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".
  final String guardId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final incidents = ref.watch($incidentsProvider).incidents;
    final isLoading = ref.watch($incidentsProvider).isLoading;
    final notifier = ref.watch($incidentsProvider.notifier);

    useEffect(() {
      Future(() {
        notifier.getIncidents(guardId);
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
        title:  const Text('Incidents List'),
      ),
    body: isLoading ? const Center(
              child: CircularProgressIndicator(),) 
                    : (incidents.isNotEmpty ? _displayIncidentsList(context, incidents) : const Text('NO INCIDENT')
      )),
    );
  }

  void _goToDetailScreen(BuildContext context, String incidentName, String incidentId) {
    Navigator.push(
        context, 
        MaterialPageRoute(builder: (context) => IncidentDetailsScreen(incidentId: incidentId))
      );
  }

  Widget _displayIncidentsList(BuildContext context, List<Incident> incidents) {
  return ListView.builder(
      padding: const EdgeInsets.all(24.0),
      itemBuilder: (context, index) {
      final incident = incidents[index];
      return GestureDetector(
        onTap: () {_goToDetailScreen(context, incident.name ?? "", incident.id ?? "");},
        child: IncidentItem(incident: incident));
    },
    itemCount: incidents.length);
  }
}
