import 'package:json_annotation/json_annotation.dart';

part 'note.g.dart';

@JsonSerializable()
class Note {
  final String? video, description, picture;

  const Note({
    this.video, 
    this.description, 
    this.picture,
  });

    factory Note.fromJson(Map<String, dynamic> json) => _$NoteFromJson(json);

    Map<String, dynamic> toJson() => _$NoteToJson(this);

}
