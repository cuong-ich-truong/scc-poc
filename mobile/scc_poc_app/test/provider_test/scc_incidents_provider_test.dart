import 'package:flutter_test/flutter_test.dart';
import 'package:scc_poc_app/services/scc_incidents_provider.dart';
import 'package:mockito/mockito.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';

import '../test_utils/nice_mock.mocks.dart';

void main() {
  final fakeRepository = MockSCCRepository();

  late ProviderContainer container;
  late IncidentsNotifier incidentsNotifier;
  setUp(()  {
    container = ProviderContainer(overrides: [
        $incidentsProvider.overrideWith((ref) => IncidentsNotifier(fakeRepository))
    ]);
    incidentsNotifier = container.read($incidentsProvider.notifier);
  });
  group('test scc_incidents_provider', () {
      test('test scc_incidents_provider', () async {
        
        when(fakeRepository.fetchIncidents('123')).thenAnswer(
            (_) => Future.value(
                  [
                    {'id': "1", 'name': 'Incident 1', 'guardId': "123", 'cameraId': "fdf9dadas", 'instruction': "Please take a look"},
                    {'id': "2", 'name': 'Incident 2', 'guardId': "123", 'cameraId': "fdf9dadas2", 'instruction': "Please take a look carefully"},
                  ]
                )
        );

        await incidentsNotifier.getIncidents('123');
        expect(incidentsNotifier.state.incidents.length, 2);
        },
      );
  });
}
