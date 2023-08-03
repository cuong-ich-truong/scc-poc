// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'send_note_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SendNoteResponse _$SendNoteResponseFromJson(Map<String, dynamic> json) =>
    SendNoteResponse(
      id: json['id'] as String?,
      description: json['description'] as String?,
      videoUrl: json['videoUrl'] as String?,
      pictureUrl: json['pictureUrl'] as String?,
    );

Map<String, dynamic> _$SendNoteResponseToJson(SendNoteResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'description': instance.description,
      'videoUrl': instance.videoUrl,
      'pictureUrl': instance.pictureUrl,
    };
