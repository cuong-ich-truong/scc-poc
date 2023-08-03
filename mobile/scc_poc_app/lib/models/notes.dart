import 'package:json_annotation/json_annotation.dart';
import 'package:scc_poc_app/models/note.dart';

part 'notes.g.dart';

@JsonSerializable()
class Notes {
  final List<Note> notes;

  Notes({
    required this.notes, 
  });

    factory Notes.fromJson(Map<String, dynamic> json) => _$NotesFromJson(json);

    Map<String, dynamic> toJson() => _$NotesToJson(this);

}