import 'package:flutter_test/flutter_test.dart';
import 'package:scc_poc_app/services/scc_send_token_provider.dart';
import 'package:mockito/mockito.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';

import '../test_utils/nice_mock.mocks.dart';

void main() {
  final fakeRepository = MockSCCRepository();

  late ProviderContainer container;
  late SendTokenNotifier sendTokenNotifier;
  setUp(()  {
    container = ProviderContainer(overrides: [
        $sendTokenProvider.overrideWith((ref) => SendTokenNotifier(fakeRepository))
    ]);
    sendTokenNotifier = container.read($sendTokenProvider.notifier);
  });
  group('test scc_send_token_provider', () {
      test('test scc_send_token_provider', () async {
        
        when(fakeRepository.sendToken('123', 'token')).thenAnswer(
            (_) => Future.value(
                  true
                )
        );

        await sendTokenNotifier.sendToken('123', 'token');
        expect(sendTokenNotifier.state.success, true);
        },
      );
  });
}
