
import 'package:riverpod/riverpod.dart';
import 'package:scc_poc_app/models/send_note_response.dart';
import 'scc_service.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:scc_poc_app/utils/service_locator.dart';

part 'scc_send_note_provider.freezed.dart';

@freezed
class SendNoteState with _$SendNoteState {
  factory SendNoteState({
    @Default(SendNoteResponse()) sendNoteResponse,
    @Default(true) bool isLoading,
  }) = _SendNoteState;
}

// Creating state notifier provider
final $sendNoteProvider = StateNotifierProvider<SendNoteNotifier, SendNoteState>((ref) => SendNoteNotifier(
  getIt<SCCRepository>(),
));

// Creating Notifier
class SendNoteNotifier extends StateNotifier<SendNoteState> {

  final SCCRepository _repository; 

  // Notifier constructor - call functions on provider initialization
  SendNoteNotifier(this._repository) : super(SendNoteState());

  sendNote(String incidentId, String description) async {
    state = state.copyWith(isLoading: true);
    final response = await _repository.sendNote(incidentId, description);
    state = state.copyWith(isLoading: false);
    if (response != null) {
      SendNoteResponse sendNoteResponse = SendNoteResponse(
        id: response['id'], 
        description: response['description'], 
        videoUrl: response['videoUrl'], 
        pictureUrl: response['pictureUrl']);
      state = state.copyWith(sendNoteResponse: sendNoteResponse);
    }
  }
}

