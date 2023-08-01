import 'package:flutter/material.dart';
import 'package:scc_poc_app/models/incident.dart';

class IncidentDetailsItem extends StatelessWidget {
    final Incident incident;
  
    const IncidentDetailsItem({
      super.key,
      required this.incident
    });
  
    @override
    Widget build(BuildContext context) {
      return  Center(
        child:  Card(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
            ListTile(
              leading: const Icon(
                        Icons.warning,
                        color: Colors.blue,
                        size: 36.0,
                      ),
              title: Text(incident.name ?? ""),
              subtitle: Text('Camera Id: ${incident.cameraId}'),
            )
          ]
          ),
        ),);
    }
  }
  