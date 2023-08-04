import 'package:flutter_test/flutter_test.dart';
import 'package:scc_poc_app/services/scc_incident_detail_provider.dart';
import 'package:mockito/mockito.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';

import '../test_utils/nice_mock.mocks.dart';

void main() {
  final fakeRepository = MockSCCRepository();

  late ProviderContainer container;
  late IncidentDetailNotifier incidentDetailProvier;
  setUp(()  {
    container = ProviderContainer(overrides: [
        $incidentDetailProvider.overrideWith((ref) => IncidentDetailNotifier(fakeRepository))
    ]);
    incidentDetailProvier = container.read($incidentDetailProvider.notifier);
  });
  group('test scc_incident_detail_provider', () {
      test('test scc_incident_detail_provider', () async {
        
        when(fakeRepository.fetchIncidentDetail('1')).thenAnswer(
            (_) => Future.value(
                    {'id': "1", 'name': 'Incident 1', 'guardId': "123", 'cameraId': "fdf9dadas", 'instruction': "Please take a look", 'notes': []},
                  )
        );

        await incidentDetailProvier.getIncidentsDetail('1');
        expect(incidentDetailProvier.state.incident.name, 'Incident 1');
        },
      );
  });
}
