import 'package:flutter/material.dart';
import 'dart:async';
import 'package:scc_poc_app/screens/send_note/send_note_screen.dart';
import 'incident_detail_item.dart';
import 'package:scc_poc_app/services/scc_incident_detail_provider.dart';
import 'package:flutter_hooks/flutter_hooks.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';

class IncidentDetailScreen extends HookConsumerWidget {
  const IncidentDetailScreen({super.key, required this.incidentId});

  final String incidentId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final incidentDetail = ref.watch($incidentDetailProvider).incident;
    final isLoading = ref.watch($incidentDetailProvider).isLoading;
    final notifier = ref.watch($incidentDetailProvider.notifier);

    useEffect(() {
      Future(() {
        notifier.getIncidentsDetail(incidentId);
      });
      return null;
    }, []);
    
    return Scaffold(
      appBar: AppBar(
        title:  const Text('Incident Details'),
        automaticallyImplyLeading: true,
      ),
    body: isLoading ? const Center(
              child: CircularProgressIndicator(),)  : Container(alignment: Alignment.topCenter, 
                    child: incidentDetail.id != null ? IncidentDetailItem(incident: incidentDetail) : const Text("NO INCIDENT INFORMATION"),),
    floatingActionButton: Visibility( 
      visible: !isLoading,
      child: ElevatedButton(
        child: const Text('Send note'), 
          onPressed: () {
            _goToSendNoteScreen(context, incidentId);
        })
        ),
      );
    
  }

  void _goToSendNoteScreen(BuildContext context, String incidentId) {
    Navigator.push(
        context, 
        MaterialPageRoute(builder: (context) => SendNoteScreen(incidentId: incidentId),)
    );
  }
}