
import 'package:riverpod/riverpod.dart';
import 'scc_service.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:scc_poc_app/utils/service_locator.dart';
import 'dart:io';

part 'scc_upload_file_provider.freezed.dart';

@freezed
class UploadFileState with _$UploadFileState {
  factory UploadFileState({
    @Default(true) bool isLoading,
    @Default(true) bool success,
  }) = _UploadFileState;
}

// Creating state notifier provider
final $uploadFileProvider = StateNotifierProvider<UploadFileNotifier, UploadFileState>((ref) => UploadFileNotifier(
  getIt<SCCRepository>(),
));

// Creating Notifier
class UploadFileNotifier extends StateNotifier<UploadFileState> {

  final SCCRepository _repository; 

  // Notifier constructor - call functions on provider initialization
  UploadFileNotifier(this._repository) : super(UploadFileState());

  uploadFile(String url, File file) async {
    state = state.copyWith(isLoading: true);
    bool success = await _repository.uploadFile(url, file);
    state = state.copyWith(isLoading: false, success: success);
  }
}

