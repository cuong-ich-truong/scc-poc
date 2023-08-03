import 'package:riverpod/riverpod.dart';
import 'package:scc_poc_app/models/incident.dart';
import 'package:scc_poc_app/models/note.dart';
import 'package:scc_poc_app/models/notes.dart';
import 'scc_service.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:scc_poc_app/utils/service_locator.dart';

part 'scc_incident_detail_provider.freezed.dart';

@freezed
class IncidentDetailState with _$IncidentDetailState {
  factory IncidentDetailState({
    @Default(Incident()) Incident incident,
    @Default(true) bool isLoading,
  }) = _IncidentDetailState;
}

// Creating state notifier provider
final $incidentDetailProvider = StateNotifierProvider<IncidentDetailNotifier, IncidentDetailState>((ref) => IncidentDetailNotifier(
  getIt<SCCRepository>(),
));

// Creating Notifier
class IncidentDetailNotifier extends StateNotifier<IncidentDetailState> {

  final SCCRepository _repository;

  // Notifier constructor - call functions on provider initialization
  IncidentDetailNotifier(this._repository) : super(IncidentDetailState());

  getIncidentsDetail(String incidentId) async {
    state = state.copyWith(isLoading: true);
    final incidentDetail = await _repository.fetchIncidentDetail(incidentId);
    state = state.copyWith(isLoading: false);
    if (incidentDetail != null) {
      List<Note> notes = [];
      for (var noteMap in incidentDetail['notes']) {
        Note note = Note(description: noteMap['description']);
        notes.add(note);
      }
      Incident incident = Incident(
        guardId: incidentDetail['guardId'], 
        name: incidentDetail['name'], 
        id: incidentDetail['id'], 
        cameraId: incidentDetail['cameraId'],
        instruction: incidentDetail['instruction'],
        notes: Notes(notes: notes));
      state = state.copyWith(incident: incident);
    }
  }
}