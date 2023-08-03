// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'note.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Note _$NoteFromJson(Map<String, dynamic> json) => Note(
      video: json['video'] as String?,
      description: json['description'] as String?,
      picture: json['picture'] as String?,
    );

Map<String, dynamic> _$NoteToJson(Note instance) => <String, dynamic>{
      'video': instance.video,
      'description': instance.description,
      'picture': instance.picture,
    };
