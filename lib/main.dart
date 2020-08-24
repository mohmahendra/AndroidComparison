import 'dart:io';

import 'package:ex_compare/post-container.dart';
import 'package:ex_compare/post.dart';
import 'package:flutter/material.dart';
import 'package:camera/camera.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final cameras = await availableCameras();
  final firstCamera = cameras.first;

  runApp(
    MaterialApp(
      title: 'Camera Test',
      theme: ThemeData.dark(),
      home: HomeScreen(camera: firstCamera),
//      home: TakePictureScreen(camera: firstCamera),
    )
  );
}

class HomeScreen extends StatefulWidget {
  final CameraDescription camera;
  List<Post> get posts => dummyPost.toList();

  const HomeScreen({Key key, this.camera}) : super(key: key);

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Timeline')),
      body: RefreshIndicator(
        onRefresh: _refresh,
        child: ListView.builder(
          key: Key('list_builder'),
          itemBuilder: (BuildContext context, int index) {
            if (index >= 45) {
              return ListTile(
                title: PostContainer(post: widget.posts[0]),
                contentPadding: EdgeInsets.all(0.0),
              );
            } else {
              return ListTile(
                title: PostContainer(post: widget.posts[index]),
                contentPadding: EdgeInsets.all(0.0),
              );
            }
          },
        ),
      ),
//      bottomNavigationBar: BottomAppBar(
//        child: Container(
//          height: 50,
//        ),
//        color: Colors.blueAccent,
//        shape: CircularNotchedRectangle(),
//      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
        Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => TakePictureScreen(camera: widget.camera)
            ));
        },
        tooltip: 'Increment',
        child: Icon(Icons.camera_alt),
        backgroundColor: Colors.greenAccent,
        key: Key('camera_button'),
      ),
    );
  }

  Future<Null> _refresh() async {
    await Future.delayed(Duration(seconds: 2));
    return null;
  }
}

class TakePictureScreen extends StatefulWidget {
  final CameraDescription camera;

  const TakePictureScreen({Key key, @required this.camera}) : super(key : key);

  @override
  _TakePictureScreenState createState() => _TakePictureScreenState();
}

class _TakePictureScreenState extends State<TakePictureScreen> {
  CameraController _controller;
  Future<void> _initializeControllerFuture;

  @override
  void initState() {
    super.initState();
    _controller = CameraController(
      widget.camera,
      ResolutionPreset.veryHigh
    );

    _initializeControllerFuture = _controller.initialize();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Take a picture')),
      body: FutureBuilder<void>(
        future: _initializeControllerFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            return CameraPreview(_controller);
          } else {
            return Center(child: CircularProgressIndicator());
          }
        }
      ),
      floatingActionButton: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: <Widget>[
          FloatingActionButton(
//              child: Icon(Icons.camera_alt),
              child: Ink(
                decoration: BoxDecoration(
                  border: Border.all(color: Colors.white, width: 4.0),
                  color: Colors.grey,
                  shape: BoxShape.circle,
                ),
                child: InkWell(
                  borderRadius: BorderRadius.circular(1000.0), //Something large to ensure a circle
                ),
              ),
              key: Key('take_photo'),
              heroTag: null,
              onPressed: () async {
                try {
                  await _initializeControllerFuture;

                  final path = join(
                      (await getTemporaryDirectory()).path,
                      '${DateTime.now()}.jpg'
                  );

                  await _controller.takePicture(path);

                  Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => DisplayPictureScreen(imagePath: path)
                )
            );
                } catch (e) {
                  print(e);
                }
              }
          ),
//          FloatingActionButton(
//            child: Icon(Icons.videocam),
//            key: Key('take_video'),
//            heroTag: null,
//            onPressed: () async {
//              try {
//                await _initializeControllerFuture;
//
//                final Directory extDir = await getExternalStorageDirectory();
//                final String dirPath = '${extDir.path}/Movies/flutter_test';
//                await Directory(dirPath).create(recursive: true);
//                final String path = '$dirPath/${DateTime.now()}.mp4';
//
//                await _controller.startVideoRecording(path);
//
//              } catch (e) {
//                print(e);
//              }
//            }
//          ),
//          FloatingActionButton(
//            child: Icon(Icons.stop),
//            key: Key('stop_video'),
//            heroTag: null,
//            onPressed: () async{
//              try {
//                await _controller.stopVideoRecording();
//              } catch (e) {
//                print(e);
//              }
//            },
//          )
        ],
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );
  }
}

class DisplayPictureScreen extends StatelessWidget {
  final String imagePath;

  const DisplayPictureScreen({Key key, this.imagePath}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Display a Picture')),
      body: Image.file(File(imagePath)), key: Key('body_image'),
    );
  }
}