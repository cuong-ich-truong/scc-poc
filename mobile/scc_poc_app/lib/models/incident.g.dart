// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'incident.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Incident _$IncidentFromJson(Map<String, dynamic> json) => Incident(
      id: json['id'] as String?,
      name: json['name'] as String?,
      guardId: json['guardId'] as String?,
      cameraId: json['cameraId'] as String?,
      instruction: json['instruction'] as String?,
      notes: json['notes'] == null
          ? null
          : Notes.fromJson(json['notes'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$IncidentToJson(Incident instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'guardId': instance.guardId,
      'cameraId': instance.cameraId,
      'instruction': instance.instruction,
      'notes': instance.notes,
    };
