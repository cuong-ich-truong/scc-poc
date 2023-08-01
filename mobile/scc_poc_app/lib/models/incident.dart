import 'package:json_annotation/json_annotation.dart';

part 'incident.g.dart';

@JsonSerializable()
class Incident {
  final String? id, name, guardId, cameraId;

  const Incident({
    this.id, 
    this.name, 
    this.guardId,
    this.cameraId
    });

    factory Incident.fromJson(Map<String, dynamic> json) => _$IncidentFromJson(json);

    Map<String, dynamic> toJson() => _$IncidentToJson(this);

}
