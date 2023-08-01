import 'package:dio/dio.dart';

class SCCService {

  late final Dio _dio = Dio(
    BaseOptions(
      baseUrl: 'https://dbfwpuvf8b.execute-api.us-east-1.amazonaws.com/dev/',
      connectTimeout: 60000,
      receiveTimeout: 60000,
      responseType: ResponseType.json,
    ),
  );

  Future<dynamic> fetchIncidents(String guardId) async {
    try {
      final icidentsData = await _dio.get('incidents?guardId=$guardId');
      return icidentsData.data;
    } on DioError {
      return [];
    }
  }

  Future<dynamic> fetchIncidentDetails(String incidentId) async {
    try {
      final icidentDetailsData = await _dio.get('incidents/$incidentId');
      return icidentDetailsData.data;
    } on DioError {
      return null;
    }
  }
}
