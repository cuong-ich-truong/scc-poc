import 'package:flutter/material.dart';
import 'dart:io';
import 'dart:async';
import 'package:image_picker/image_picker.dart';
import 'package:scc_poc_app/models/send_note_response.dart';
import 'package:scc_poc_app/services/scc_upload_file_provider.dart';
import 'package:video_player/video_player.dart';
import 'package:flick_video_player/flick_video_player.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';
import 'package:scc_poc_app/services/scc_send_note_provider.dart';

class SendNoteScreen extends ConsumerStatefulWidget {
  const SendNoteScreen({super.key, required this.incidentId});
  final String incidentId;

  @override
  SendNoteScreenState createState() => SendNoteScreenState();

}

class SendNoteScreenState extends ConsumerState<SendNoteScreen> {

  // This is the file that will be used to store the image
  File? _image;
  File? _video;
  late VideoPlayerController _videoPlayerController;
  late Future<void> _initializeVideoPlayerFuture;
  final _textEditController = TextEditingController();
  bool _isNetworkLoading = false;

  // This is the image picker
  final _picker = ImagePicker();
  // Implementing the image picker
  Future<void> _openImagePicker() async {
    final XFile? pickedImage =
        await _picker.pickImage(source: ImageSource.camera);
    if (pickedImage != null) {
      setState(() {
        _image = File(pickedImage.path);
      });
    }
  }

  Future<void> _openVideoPicker() async {
    final XFile? pickedImage =
        await _picker.pickVideo(source: ImageSource.camera);
    if (pickedImage != null) {
      setState(() {
        _video = File(pickedImage.path);
      });
    }
  }

  _sendNote() async{
    final sendNoteNotifier = ref.watch($sendNoteProvider.notifier);
    final uploadFileNotifier = ref.watch($uploadFileProvider.notifier);
    _isNetworkLoading = true;
    
    await sendNoteNotifier.sendNote(widget.incidentId, _textEditController.text);
    final SendNoteResponse sendNoteResponse = ref.watch($sendNoteProvider).sendNoteResponse;
    if (_image != null && sendNoteResponse.pictureUrl != null) {
      await uploadFileNotifier.uploadFile(sendNoteResponse.pictureUrl!, _image!);
    }

    if (_video != null && sendNoteResponse.videoUrl != null) {
      await uploadFileNotifier.uploadFile(sendNoteResponse.videoUrl!, _video!);
    }

    _isNetworkLoading = false;
    if (context.mounted) {
      Navigator.pop(context);
    }
  }

  FutureBuilder _displayVideo(File video) {
    _videoPlayerController = VideoPlayerController.file(video);
    _initializeVideoPlayerFuture = _videoPlayerController.initialize();
    _videoPlayerController.setLooping(true);

    return FutureBuilder(
        future: _initializeVideoPlayerFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            // If the VideoPlayerController has finished initialization, use
            // the data it provides to limit the aspect ratio of the video.
            return
              Stack(children: [
                AspectRatio(aspectRatio: _videoPlayerController.value.aspectRatio,
                      // Use the VideoPlayer widget to display the video.
                          child: FlickVideoPlayer(flickManager: FlickManager(videoPlayerController: VideoPlayerController.file(video)))),
                          ]);
          } else {
            // If the VideoPlayerController is still initializing, show a
            // loading spinner.
            return const Center(
              child: CircularProgressIndicator(),
            );
          }
        },
      );
  }  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Send Note'),
      ),
      body: _isNetworkLoading 
                ? const Center(
                    child: CircularProgressIndicator(),) 
                : SafeArea(
        child: 
          Padding(padding: const EdgeInsets.all(35), 
            child: ListView(padding: const EdgeInsets.all(8),
            children: [
              TextField(
              controller: _textEditController,
              obscureText: false,
              keyboardType: TextInputType.multiline,
              maxLines: 5,
              decoration: const InputDecoration(
                border: OutlineInputBorder(),
                labelText: 'Description',
            )),  
            const SizedBox(height: 25),
            _image != null ? Image.file(_image!, fit: BoxFit.cover) 
                            : Center( child: ElevatedButton(
                                      onPressed: _openImagePicker,
                                      child: const Text("Take an image"),
            ),
            ),
            const SizedBox(height: 25),
            _video != null ? _displayVideo(_video!)
                          : Center( child: ElevatedButton(
                                    onPressed: _openVideoPicker,
                                    child: const Text("Take a video"),

            ),
            ),
            const SizedBox(height: 40),
          ]),
          ),
      ),
      floatingActionButton: Visibility( 
          visible: _textEditController.text.isNotEmpty,
          child: ElevatedButton(
            child: const Text('Send note'), 
              onPressed: () async {
                _sendNote();
            })
            ),
    );
  }

  @override
  void dispose() {
    super.dispose();
    if (_video != null) {
      _videoPlayerController.dispose();
    }
    _textEditController.dispose();
  }
}