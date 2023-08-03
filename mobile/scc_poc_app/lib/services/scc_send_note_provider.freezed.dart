// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'scc_send_note_provider.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more information: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
mixin _$SendNoteState {
  dynamic get sendNoteResponse => throw _privateConstructorUsedError;
  bool get isLoading => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $SendNoteStateCopyWith<SendNoteState> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $SendNoteStateCopyWith<$Res> {
  factory $SendNoteStateCopyWith(
          SendNoteState value, $Res Function(SendNoteState) then) =
      _$SendNoteStateCopyWithImpl<$Res, SendNoteState>;
  @useResult
  $Res call({dynamic sendNoteResponse, bool isLoading});
}

/// @nodoc
class _$SendNoteStateCopyWithImpl<$Res, $Val extends SendNoteState>
    implements $SendNoteStateCopyWith<$Res> {
  _$SendNoteStateCopyWithImpl(this._value, this._then);

  // ignore: unused_field
  final $Val _value;
  // ignore: unused_field
  final $Res Function($Val) _then;

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? sendNoteResponse = freezed,
    Object? isLoading = null,
  }) {
    return _then(_value.copyWith(
      sendNoteResponse: freezed == sendNoteResponse
          ? _value.sendNoteResponse
          : sendNoteResponse // ignore: cast_nullable_to_non_nullable
              as dynamic,
      isLoading: null == isLoading
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ) as $Val);
  }
}

/// @nodoc
abstract class _$$_SendNoteStateCopyWith<$Res>
    implements $SendNoteStateCopyWith<$Res> {
  factory _$$_SendNoteStateCopyWith(
          _$_SendNoteState value, $Res Function(_$_SendNoteState) then) =
      __$$_SendNoteStateCopyWithImpl<$Res>;
  @override
  @useResult
  $Res call({dynamic sendNoteResponse, bool isLoading});
}

/// @nodoc
class __$$_SendNoteStateCopyWithImpl<$Res>
    extends _$SendNoteStateCopyWithImpl<$Res, _$_SendNoteState>
    implements _$$_SendNoteStateCopyWith<$Res> {
  __$$_SendNoteStateCopyWithImpl(
      _$_SendNoteState _value, $Res Function(_$_SendNoteState) _then)
      : super(_value, _then);

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? sendNoteResponse = freezed,
    Object? isLoading = null,
  }) {
    return _then(_$_SendNoteState(
      sendNoteResponse: freezed == sendNoteResponse
          ? _value.sendNoteResponse!
          : sendNoteResponse,
      isLoading: null == isLoading
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_SendNoteState implements _SendNoteState {
  _$_SendNoteState(
      {this.sendNoteResponse = const SendNoteResponse(),
      this.isLoading = true});

  @override
  @JsonKey()
  final dynamic sendNoteResponse;
  @override
  @JsonKey()
  final bool isLoading;

  @override
  String toString() {
    return 'SendNoteState(sendNoteResponse: $sendNoteResponse, isLoading: $isLoading)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _$_SendNoteState &&
            const DeepCollectionEquality()
                .equals(other.sendNoteResponse, sendNoteResponse) &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading));
  }

  @override
  int get hashCode => Object.hash(runtimeType,
      const DeepCollectionEquality().hash(sendNoteResponse), isLoading);

  @JsonKey(ignore: true)
  @override
  @pragma('vm:prefer-inline')
  _$$_SendNoteStateCopyWith<_$_SendNoteState> get copyWith =>
      __$$_SendNoteStateCopyWithImpl<_$_SendNoteState>(this, _$identity);
}

abstract class _SendNoteState implements SendNoteState {
  factory _SendNoteState(
      {final dynamic sendNoteResponse,
      final bool isLoading}) = _$_SendNoteState;

  @override
  dynamic get sendNoteResponse;
  @override
  bool get isLoading;
  @override
  @JsonKey(ignore: true)
  _$$_SendNoteStateCopyWith<_$_SendNoteState> get copyWith =>
      throw _privateConstructorUsedError;
}
