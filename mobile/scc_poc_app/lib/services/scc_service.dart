import 'package:dio/dio.dart';
import 'dart:io';

abstract class SCCRepository {
  Future<dynamic> fetchIncidents(String guardId);
  Future<dynamic> fetchIncidentDetail(String incidentId);
  Future<dynamic> sendToken(String guardId, String token);
  Future<dynamic> sendNote(String incidentId, String description);
  Future<dynamic> uploadFile(String url, File file);
}

class SCCServiceRepository extends SCCRepository {

  late final Dio _dio = Dio(
    BaseOptions(
      baseUrl: 'https://lf1ag8mnsi.execute-api.us-east-1.amazonaws.com/demo/',
      connectTimeout: 60000,
      receiveTimeout: 60000,
      responseType: ResponseType.json,
    ),
  );

  @override
  Future<dynamic> fetchIncidents(String guardId) async {
    try {
      final icidentsData = await _dio.get('incidents?guardId=$guardId');
      return icidentsData.data;
    } on DioError {
      return [];
    }
  }

  @override
  Future<dynamic> fetchIncidentDetail(String incidentId) async {
    try {
      final icidentDetailsData = await _dio.get('incidents/$incidentId');
      return icidentDetailsData.data;
    } on DioError {
      return null;
    }
  }

  @override
  Future<dynamic> sendToken(String guardId, String token) async {
    try {
      final response = await _dio.put('guards/$guardId/$token');
      return response.statusCode == 200;
    } on DioError {
      return false;
    }
  }

  @override
  Future<dynamic> sendNote(String incidentId, String description) async {
    try {
      final sendNoteResponse = await _dio.post('incidents/$incidentId/notes', data: {'description': description});
      return sendNoteResponse.data;
    } on DioError {
      return null;
    }
  }

  @override
  Future<dynamic> uploadFile(String url, File file) async {
    try {
      var len = await file.length();
      await _dio.put(url,
          data: file.openRead(),
          options: Options(headers: {
            Headers.contentLengthHeader: len,
          } // set content-length
              ));
      return true;
    } on DioError {
      return false;
    }
  }
}
