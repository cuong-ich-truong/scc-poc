import 'package:flutter/material.dart';
import 'package:scc_poc_app/models/incident.dart';

class IncidentItem extends StatelessWidget {
    final Incident incident;
  
    const IncidentItem({
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
            )
          ]
          ),
        ),);
    }
  }
  