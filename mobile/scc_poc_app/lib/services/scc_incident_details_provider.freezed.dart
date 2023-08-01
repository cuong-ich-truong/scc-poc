// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'scc_incident_details_provider.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more information: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
mixin _$IncidentDetailsState {
  Incident get incident => throw _privateConstructorUsedError;
  bool get isLoading => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $IncidentDetailsStateCopyWith<IncidentDetailsState> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $IncidentDetailsStateCopyWith<$Res> {
  factory $IncidentDetailsStateCopyWith(IncidentDetailsState value,
          $Res Function(IncidentDetailsState) then) =
      _$IncidentDetailsStateCopyWithImpl<$Res, IncidentDetailsState>;
  @useResult
  $Res call({Incident incident, bool isLoading});
}

/// @nodoc
class _$IncidentDetailsStateCopyWithImpl<$Res,
        $Val extends IncidentDetailsState>
    implements $IncidentDetailsStateCopyWith<$Res> {
  _$IncidentDetailsStateCopyWithImpl(this._value, this._then);

  // ignore: unused_field
  final $Val _value;
  // ignore: unused_field
  final $Res Function($Val) _then;

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? incident = null,
    Object? isLoading = null,
  }) {
    return _then(_value.copyWith(
      incident: null == incident
          ? _value.incident
          : incident // ignore: cast_nullable_to_non_nullable
              as Incident,
      isLoading: null == isLoading
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ) as $Val);
  }
}

/// @nodoc
abstract class _$$_IncidentDetailsStateCopyWith<$Res>
    implements $IncidentDetailsStateCopyWith<$Res> {
  factory _$$_IncidentDetailsStateCopyWith(_$_IncidentDetailsState value,
          $Res Function(_$_IncidentDetailsState) then) =
      __$$_IncidentDetailsStateCopyWithImpl<$Res>;
  @override
  @useResult
  $Res call({Incident incident, bool isLoading});
}

/// @nodoc
class __$$_IncidentDetailsStateCopyWithImpl<$Res>
    extends _$IncidentDetailsStateCopyWithImpl<$Res, _$_IncidentDetailsState>
    implements _$$_IncidentDetailsStateCopyWith<$Res> {
  __$$_IncidentDetailsStateCopyWithImpl(_$_IncidentDetailsState _value,
      $Res Function(_$_IncidentDetailsState) _then)
      : super(_value, _then);

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? incident = null,
    Object? isLoading = null,
  }) {
    return _then(_$_IncidentDetailsState(
      incident: null == incident
          ? _value.incident
          : incident // ignore: cast_nullable_to_non_nullable
              as Incident,
      isLoading: null == isLoading
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_IncidentDetailsState implements _IncidentDetailsState {
  _$_IncidentDetailsState(
      {this.incident = const Incident(), this.isLoading = true});

  @override
  @JsonKey()
  final Incident incident;
  @override
  @JsonKey()
  final bool isLoading;

  @override
  String toString() {
    return 'IncidentDetailsState(incident: $incident, isLoading: $isLoading)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _$_IncidentDetailsState &&
            (identical(other.incident, incident) ||
                other.incident == incident) &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading));
  }

  @override
  int get hashCode => Object.hash(runtimeType, incident, isLoading);

  @JsonKey(ignore: true)
  @override
  @pragma('vm:prefer-inline')
  _$$_IncidentDetailsStateCopyWith<_$_IncidentDetailsState> get copyWith =>
      __$$_IncidentDetailsStateCopyWithImpl<_$_IncidentDetailsState>(
          this, _$identity);
}

abstract class _IncidentDetailsState implements IncidentDetailsState {
  factory _IncidentDetailsState(
      {final Incident incident,
      final bool isLoading}) = _$_IncidentDetailsState;

  @override
  Incident get incident;
  @override
  bool get isLoading;
  @override
  @JsonKey(ignore: true)
  _$$_IncidentDetailsStateCopyWith<_$_IncidentDetailsState> get copyWith =>
      throw _privateConstructorUsedError;
}
