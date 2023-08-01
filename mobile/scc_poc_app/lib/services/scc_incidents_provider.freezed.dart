// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'scc_incidents_provider.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more information: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
mixin _$IncidentsState {
  List<Incident> get incidents => throw _privateConstructorUsedError;
  bool get isLoading => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $IncidentsStateCopyWith<IncidentsState> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $IncidentsStateCopyWith<$Res> {
  factory $IncidentsStateCopyWith(
          IncidentsState value, $Res Function(IncidentsState) then) =
      _$IncidentsStateCopyWithImpl<$Res, IncidentsState>;
  @useResult
  $Res call({List<Incident> incidents, bool isLoading});
}

/// @nodoc
class _$IncidentsStateCopyWithImpl<$Res, $Val extends IncidentsState>
    implements $IncidentsStateCopyWith<$Res> {
  _$IncidentsStateCopyWithImpl(this._value, this._then);

  // ignore: unused_field
  final $Val _value;
  // ignore: unused_field
  final $Res Function($Val) _then;

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? incidents = null,
    Object? isLoading = null,
  }) {
    return _then(_value.copyWith(
      incidents: null == incidents
          ? _value.incidents
          : incidents // ignore: cast_nullable_to_non_nullable
              as List<Incident>,
      isLoading: null == isLoading
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ) as $Val);
  }
}

/// @nodoc
abstract class _$$_IncidentsStateCopyWith<$Res>
    implements $IncidentsStateCopyWith<$Res> {
  factory _$$_IncidentsStateCopyWith(
          _$_IncidentsState value, $Res Function(_$_IncidentsState) then) =
      __$$_IncidentsStateCopyWithImpl<$Res>;
  @override
  @useResult
  $Res call({List<Incident> incidents, bool isLoading});
}

/// @nodoc
class __$$_IncidentsStateCopyWithImpl<$Res>
    extends _$IncidentsStateCopyWithImpl<$Res, _$_IncidentsState>
    implements _$$_IncidentsStateCopyWith<$Res> {
  __$$_IncidentsStateCopyWithImpl(
      _$_IncidentsState _value, $Res Function(_$_IncidentsState) _then)
      : super(_value, _then);

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? incidents = null,
    Object? isLoading = null,
  }) {
    return _then(_$_IncidentsState(
      incidents: null == incidents
          ? _value._incidents
          : incidents // ignore: cast_nullable_to_non_nullable
              as List<Incident>,
      isLoading: null == isLoading
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_IncidentsState implements _IncidentsState {
  _$_IncidentsState(
      {final List<Incident> incidents = const [], this.isLoading = true})
      : _incidents = incidents;

  final List<Incident> _incidents;
  @override
  @JsonKey()
  List<Incident> get incidents {
    if (_incidents is EqualUnmodifiableListView) return _incidents;
    // ignore: implicit_dynamic_type
    return EqualUnmodifiableListView(_incidents);
  }

  @override
  @JsonKey()
  final bool isLoading;

  @override
  String toString() {
    return 'IncidentsState(incidents: $incidents, isLoading: $isLoading)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _$_IncidentsState &&
            const DeepCollectionEquality()
                .equals(other._incidents, _incidents) &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, const DeepCollectionEquality().hash(_incidents), isLoading);

  @JsonKey(ignore: true)
  @override
  @pragma('vm:prefer-inline')
  _$$_IncidentsStateCopyWith<_$_IncidentsState> get copyWith =>
      __$$_IncidentsStateCopyWithImpl<_$_IncidentsState>(this, _$identity);
}

abstract class _IncidentsState implements IncidentsState {
  factory _IncidentsState(
      {final List<Incident> incidents,
      final bool isLoading}) = _$_IncidentsState;

  @override
  List<Incident> get incidents;
  @override
  bool get isLoading;
  @override
  @JsonKey(ignore: true)
  _$$_IncidentsStateCopyWith<_$_IncidentsState> get copyWith =>
      throw _privateConstructorUsedError;
}
