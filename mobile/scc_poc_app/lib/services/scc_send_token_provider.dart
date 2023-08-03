
import 'package:riverpod/riverpod.dart';
import 'scc_service.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:scc_poc_app/utils/service_locator.dart';

part 'scc_send_token_provider.freezed.dart';

@freezed
class SendTokenState with _$SendTokenState {
  factory SendTokenState({
    @Default(true) bool isLoading,
    @Default(false) bool success,
  }) = _SendTokenState;
}

// Creating state notifier provider
final $sendTokenProvider = StateNotifierProvider<SendTokenNotifier, SendTokenState>((ref) => SendTokenNotifier(
  getIt<SCCRepository>(),
));

// Creating Notifier
class SendTokenNotifier extends StateNotifier<SendTokenState> {

  final SCCRepository _repository; 

  // Notifier constructor - call functions on provider initialization
  SendTokenNotifier(this._repository) : super(SendTokenState());

  sendToken(String guardId, String token) async {
    state = state.copyWith(isLoading: true);
    bool success = await _repository.sendToken(guardId, token);
    state = state.copyWith(isLoading: false, success: success);
  }
}

