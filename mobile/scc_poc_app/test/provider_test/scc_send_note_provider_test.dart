import 'package:flutter_test/flutter_test.dart';
import 'package:scc_poc_app/services/scc_send_note_provider.dart';
import 'package:mockito/mockito.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';

import '../test_utils/nice_mock.mocks.dart';

void main() {
  final fakeRepository = MockSCCRepository();

  late ProviderContainer container;
  late SendNoteNotifier sendNoteNotifier;
  setUp(()  {
    container = ProviderContainer(overrides: [
        $sendNoteProvider.overrideWith((ref) => SendNoteNotifier(fakeRepository))
    ]);
    sendNoteNotifier = container.read($sendNoteProvider.notifier);
  });
  group('test scc_send_note_provider', () {
      test('test scc_send_note_provider', () async {
        
        when(fakeRepository.sendNote("incident_1", "description")).thenAnswer(
            (_) => Future.value(
                  {'id': 'note_1', 'description': 'description ABC', 'pictureUrl': 'https://resigned_url1.com', 'videoUrl': 'https://resigned_url2.com'}
                )
        );

        await sendNoteNotifier.sendNote("incident_1", "description");
        expect(sendNoteNotifier.state.sendNoteResponse.description, 'description ABC');
        expect(sendNoteNotifier.state.sendNoteResponse.pictureUrl, 'https://resigned_url1.com');
        expect(sendNoteNotifier.state.sendNoteResponse.videoUrl, 'https://resigned_url2.com');
        },
      );
  });
}
