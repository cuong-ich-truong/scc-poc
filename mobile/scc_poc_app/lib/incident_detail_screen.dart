import 'package:flutter/material.dart';
import 'dart:io';
import 'dart:async';
import 'package:image_picker/image_picker.dart';
import 'package:video_player/video_player.dart';
import 'package:flick_video_player/flick_video_player.dart';

class IncidentDetailScreen extends StatefulWidget {
  const IncidentDetailScreen({super.key});

  @override
  State<IncidentDetailScreen> createState() => _IncidentDetailScreenState();
}

class _IncidentDetailScreenState extends State<IncidentDetailScreen> {

  // This is the file that will be used to store the image
  File? _image;
  File? _video;
  late VideoPlayerController _controller;
  late Future<void> _initializeVideoPlayerFuture;

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

  FutureBuilder _displayVideo(File video) {
    _controller = VideoPlayerController.file(video);
    _initializeVideoPlayerFuture = _controller.initialize();
    _controller.setLooping(true);

    return FutureBuilder(
        future: _initializeVideoPlayerFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            // If the VideoPlayerController has finished initialization, use
            // the data it provides to limit the aspect ratio of the video.
            return
              Stack(children: [
                AspectRatio(aspectRatio: _controller.value.aspectRatio,
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
        title: const Text('Incident Detail'),
      ),
      body: SafeArea(
        child: 
        Padding(padding: const EdgeInsets.all(35), 
          child: ListView(padding: const EdgeInsets.all(8),
          children: [
           _image != null ? Image.file(_image!, fit: BoxFit.cover) 
                          : Center( child: ElevatedButton(
                                    onPressed: _openImagePicker,
                                    child: const Text("Take an image"),
          ),
          ),
          const SizedBox(height: 45),
          _video != null ? _displayVideo(_video!)
                         : Center( child: ElevatedButton(
                                   onPressed: _openVideoPicker,
                                   child: const Text("Take a video"),
          ),
          ),
        ]),
        ),
      ),
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }
}
