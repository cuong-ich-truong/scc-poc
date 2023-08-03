import 'package:flutter/material.dart';
import 'package:scc_poc_app/models/incident.dart';

class IncidentDetailItem extends StatelessWidget {
    final Incident incident;
  
    const IncidentDetailItem({
      super.key,
      required this.incident
    });
  
    @override
    Widget build(BuildContext context) {
      return  Center(
        child: Column(
              children: [
              ListTile(
                leading: const Icon(
                          Icons.warning,
                          color: Colors.blue,
                          size: 36.0,
                        ),
                title: Text(incident.name ?? ""),
                subtitle: Text('Instruction: ${incident.instruction}'),
              ),
              const Text("NOTES:", 
              textAlign: TextAlign.left,
              style: TextStyle(fontWeight: FontWeight.bold)),

              incident.notes != null ? 
              Expanded(
                child: ListView.builder(
                padding: const EdgeInsets.all(20.0),
                itemBuilder: (context, index) {
                    final noteItem = incident.notes!.notes[index];
                    return Text('${noteItem.description}');
                  },
                  itemCount: incident.notes!.notes.length

                )) : Container(height: 20,)
            ]
            ),
          );
    }
  }
  