import 'dart:io';

import 'package:flutter_test/flutter_test.dart';
import 'package:scc_poc_app/services/scc_upload_file_provider.dart';
import 'package:mockito/mockito.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';

import '../test_utils/nice_mock.mocks.dart';

void main() {
  final fakeRepository = MockSCCRepository();

  late ProviderContainer container;
  late UploadFileNotifier uploadFileNotifier;
  setUp(()  {
    container = ProviderContainer(overrides: [
        $uploadFileProvider.overrideWith((ref) => UploadFileNotifier(fakeRepository))
    ]);
    uploadFileNotifier = container.read($uploadFileProvider.notifier);
  });
  group('test scc_upload_file_provider', () {
    File file = File("abc");
      test('test scc_upload_file_provider', () async {
        
        when(fakeRepository.uploadFile('123', file)).thenAnswer(
            (_) => Future.value(
                  true
                )
        );

        await uploadFileNotifier.uploadFile('123', file);
        expect(uploadFileNotifier.state.success, true);
        },
      );
  });
}
