
import 'package:riverpod/riverpod.dart';
import 'package:scc_poc_app/models/incident.dart';
import 'scc_service.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:scc_poc_app/utils/service_locator.dart';

part 'scc_incidents_provider.freezed.dart';

@freezed
class IncidentsState with _$IncidentsState {
  factory IncidentsState({
    @Default([]) List<Incident> incidents,
    @Default(true) bool isLoading,
  }) = _IncidentsState;
}

// Creating state notifier provider
final $incidentsProvider = StateNotifierProvider<IncidentsNotifier, IncidentsState>((ref) => IncidentsNotifier(
  getIt<SCCRepository>(),
));

// Creating Notifier
class IncidentsNotifier extends StateNotifier<IncidentsState> {
  final SCCRepository _repository; 
  // Notifier constructor - call functions on provider initialization
  IncidentsNotifier(this._repository) : super(IncidentsState());

  getIncidents(String guardId) async {
    state = state.copyWith(isLoading: true);
    final incidentsList = await _repository.fetchIncidents(guardId);
    List<Incident> incidents = [];
    for (var item in incidentsList) {
      Incident incident = Incident(guardId: item['guardId'], name: item['name'], id: item['id']);
      incidents.add(incident);
    }
    state = state.copyWith(isLoading: false);
    state = state.copyWith(incidents: incidents);
  }
}

