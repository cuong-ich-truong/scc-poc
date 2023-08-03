// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'scc_incident_detail_provider.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more information: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
mixin _$IncidentDetailState {
  Incident get incident => throw _privateConstructorUsedError;
  bool get isLoading => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $IncidentDetailStateCopyWith<IncidentDetailState> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $IncidentDetailStateCopyWith<$Res> {
  factory $IncidentDetailStateCopyWith(
          IncidentDetailState value, $Res Function(IncidentDetailState) then) =
      _$IncidentDetailStateCopyWithImpl<$Res, IncidentDetailState>;
  @useResult
  $Res call({Incident incident, bool isLoading});
}

/// @nodoc
class _$IncidentDetailStateCopyWithImpl<$Res, $Val extends IncidentDetailState>
    implements $IncidentDetailStateCopyWith<$Res> {
  _$IncidentDetailStateCopyWithImpl(this._value, this._then);

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
abstract class _$$_IncidentDetailStateCopyWith<$Res>
    implements $IncidentDetailStateCopyWith<$Res> {
  factory _$$_IncidentDetailStateCopyWith(_$_IncidentDetailState value,
          $Res Function(_$_IncidentDetailState) then) =
      __$$_IncidentDetailStateCopyWithImpl<$Res>;
  @override
  @useResult
  $Res call({Incident incident, bool isLoading});
}

/// @nodoc
class __$$_IncidentDetailStateCopyWithImpl<$Res>
    extends _$IncidentDetailStateCopyWithImpl<$Res, _$_IncidentDetailState>
    implements _$$_IncidentDetailStateCopyWith<$Res> {
  __$$_IncidentDetailStateCopyWithImpl(_$_IncidentDetailState _value,
      $Res Function(_$_IncidentDetailState) _then)
      : super(_value, _then);

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? incident = null,
    Object? isLoading = null,
  }) {
    return _then(_$_IncidentDetailState(
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

class _$_IncidentDetailState implements _IncidentDetailState {
  _$_IncidentDetailState(
      {this.incident = const Incident(), this.isLoading = true});

  @override
  @JsonKey()
  final Incident incident;
  @override
  @JsonKey()
  final bool isLoading;

  @override
  String toString() {
    return 'IncidentDetailState(incident: $incident, isLoading: $isLoading)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _$_IncidentDetailState &&
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
  _$$_IncidentDetailStateCopyWith<_$_IncidentDetailState> get copyWith =>
      __$$_IncidentDetailStateCopyWithImpl<_$_IncidentDetailState>(
          this, _$identity);
}

abstract class _IncidentDetailState implements IncidentDetailState {
  factory _IncidentDetailState(
      {final Incident incident, final bool isLoading}) = _$_IncidentDetailState;

  @override
  Incident get incident;
  @override
  bool get isLoading;
  @override
  @JsonKey(ignore: true)
  _$$_IncidentDetailStateCopyWith<_$_IncidentDetailState> get copyWith =>
      throw _privateConstructorUsedError;
}
