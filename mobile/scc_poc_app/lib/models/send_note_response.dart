import 'package:json_annotation/json_annotation.dart';

part 'send_note_response.g.dart';

@JsonSerializable()
class SendNoteResponse {
  final String? id, description, videoUrl, pictureUrl;

  const SendNoteResponse({
    this.id,
    this.description,
    this.videoUrl, 
    this.pictureUrl,
  });

    factory SendNoteResponse.fromJson(Map<String, dynamic> json) => _$SendNoteResponseFromJson(json);

    Map<String, dynamic> toJson() => _$SendNoteResponseToJson(this);

}
