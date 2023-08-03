// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'scc_upload_file_provider.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more information: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
mixin _$UploadFileState {
  bool get isLoading => throw _privateConstructorUsedError;
  bool get success => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $UploadFileStateCopyWith<UploadFileState> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UploadFileStateCopyWith<$Res> {
  factory $UploadFileStateCopyWith(
          UploadFileState value, $Res Function(UploadFileState) then) =
      _$UploadFileStateCopyWithImpl<$Res, UploadFileState>;
  @useResult
  $Res call({bool isLoading, bool success});
}

/// @nodoc
class _$UploadFileStateCopyWithImpl<$Res, $Val extends UploadFileState>
    implements $UploadFileStateCopyWith<$Res> {
  _$UploadFileStateCopyWithImpl(this._value, this._then);

  // ignore: unused_field
  final $Val _value;
  // ignore: unused_field
  final $Res Function($Val) _then;

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? isLoading = null,
    Object? success = null,
  }) {
    return _then(_value.copyWith(
      isLoading: null == isLoading
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      success: null == success
          ? _value.success
          : success // ignore: cast_nullable_to_non_nullable
              as bool,
    ) as $Val);
  }
}

/// @nodoc
abstract class _$$_UploadFileStateCopyWith<$Res>
    implements $UploadFileStateCopyWith<$Res> {
  factory _$$_UploadFileStateCopyWith(
          _$_UploadFileState value, $Res Function(_$_UploadFileState) then) =
      __$$_UploadFileStateCopyWithImpl<$Res>;
  @override
  @useResult
  $Res call({bool isLoading, bool success});
}

/// @nodoc
class __$$_UploadFileStateCopyWithImpl<$Res>
    extends _$UploadFileStateCopyWithImpl<$Res, _$_UploadFileState>
    implements _$$_UploadFileStateCopyWith<$Res> {
  __$$_UploadFileStateCopyWithImpl(
      _$_UploadFileState _value, $Res Function(_$_UploadFileState) _then)
      : super(_value, _then);

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? isLoading = null,
    Object? success = null,
  }) {
    return _then(_$_UploadFileState(
      isLoading: null == isLoading
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      success: null == success
          ? _value.success
          : success // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_UploadFileState implements _UploadFileState {
  _$_UploadFileState({this.isLoading = true, this.success = true});

  @override
  @JsonKey()
  final bool isLoading;
  @override
  @JsonKey()
  final bool success;

  @override
  String toString() {
    return 'UploadFileState(isLoading: $isLoading, success: $success)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _$_UploadFileState &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading) &&
            (identical(other.success, success) || other.success == success));
  }

  @override
  int get hashCode => Object.hash(runtimeType, isLoading, success);

  @JsonKey(ignore: true)
  @override
  @pragma('vm:prefer-inline')
  _$$_UploadFileStateCopyWith<_$_UploadFileState> get copyWith =>
      __$$_UploadFileStateCopyWithImpl<_$_UploadFileState>(this, _$identity);
}

abstract class _UploadFileState implements UploadFileState {
  factory _UploadFileState({final bool isLoading, final bool success}) =
      _$_UploadFileState;

  @override
  bool get isLoading;
  @override
  bool get success;
  @override
  @JsonKey(ignore: true)
  _$$_UploadFileStateCopyWith<_$_UploadFileState> get copyWith =>
      throw _privateConstructorUsedError;
}
