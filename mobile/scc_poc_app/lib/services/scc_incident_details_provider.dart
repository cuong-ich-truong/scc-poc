

import 'package:riverpod/riverpod.dart';
import 'package:scc_poc_app/models/incident.dart';
import 'scc_service.dart';
import 'package:freezed_annotation/freezed_annotation.dart';

part 'scc_incident_details_provider.freezed.dart';

@freezed
class IncidentDetailsState with _$IncidentDetailsState {
  factory IncidentDetailsState({
    @Default(Incident()) Incident incident,
    @Default(true) bool isLoading,
  }) = _IncidentDetailsState;
}

// Creating state notifier provider
final $incidentDetailsProvider = StateNotifierProvider<IncidentDetailsNotifier, IncidentDetailsState>((ref) => IncidentDetailsNotifier());

// Creating Notifier
class IncidentDetailsNotifier extends StateNotifier<IncidentDetailsState> {
  // Notifier constructor - call functions on provider initialization
  IncidentDetailsNotifier() : super(IncidentDetailsState());

  getIncidentsDetails(String incidentId) async {
    state = state.copyWith(isLoading: true);
    final incidentDetails = await SCCService().fetchIncidentDetails(incidentId);
    state = state.copyWith(isLoading: false);
    if (incidentDetails != null) {
      Incident incident = Incident(guardId: incidentDetails['guardId'], name: incidentDetails['name'], id: incidentDetails['id'], cameraId: incidentDetails['cameraId']);
      state = state.copyWith(incident: incident);
    }
  }
}