import 'dart:io' show Platform, Process;

import 'package:flutter/widgets.dart';
import 'package:flutter_driver/flutter_driver.dart';
import 'package:path/path.dart';
import 'package:test/test.dart';

void main() {
  group('Ex_compare app', () {
    // First, define the Finders and use them to locate widgets from the
    // test suite. Note: the Strings provided to the `byValueKey` method must
    // be the same as the Strings we used for the Keys in step 1.
    final counterTextFinder = find.byValueKey('counter');
    final buttonFinder = find.byValueKey('increment');
    final itemFinder = find.byValueKey('name_Andini');

    FlutterDriver driver;

    // Connect to the Flutter driver before running any tests.
    setUpAll(() async {
      final envVars = Platform.environment;
      final adbPath = join(
        envVars['ANDROID_SDK_ROOT'] ?? envVars['ANDROID_HOME'],
        'Platform-tools',
        Platform.isWindows ? 'adb.exe' : 'adb',
      );
      await Process.run(adbPath, [
        'shell',
        'pm',
        'grant',
        'com.example.ex_compare',
        'android.permission.CAMERA'
      ]);

      await Process.run(adbPath, [
        'shell',
        'pm',
        'grant',
        'com.example.ex_compare',
        'android.permission.RECORD_AUDIO'
      ]);

      driver = await FlutterDriver.connect();
    });

    // Close the connection to the driver after the test have been completed
    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });
    
    test('Infinite Scroll', () async {
      final listFinder = find.byValueKey('list_builder');
      final timeline = await driver.traceAction(() async {
        await driver.scroll(listFinder, 0, -10000.0, Duration(seconds: 40));
      });

      // Convert the Timeline into a TimelineSummary that's easier to
      // read and understand.
      final summary = new TimelineSummary.summarize(timeline);

      // Then, save the summary to disk.
      summary.writeSummaryToFile('scrolling_summary', pretty: true);

      // Optionally, write the entire timeline to disk in a json format.
      // This file can be opened in the Chrome browser's tracing tools
      // found by navigating to chrome://tracing.
      summary.writeTimelineToFile('scrolling_timeline', pretty: true);
    },
      timeout: Timeout(
        Duration(minutes: 2),
      ),
    );

    test('Take Photos', () async {
      final buttonFinder = find.byValueKey('camera_button');
      final takePhotoFinder = find.byValueKey('take_photo');
      final imageFinder = find.byValueKey('body_image');

      await Future.delayed(Duration(seconds: 5));

      // Tap the camera button
      await driver.tap(buttonFinder);

      await Future.delayed(Duration(seconds: 10));

      // Tap to take photo
      await driver.tap(takePhotoFinder);

      await Future.delayed(Duration(seconds: 5));

      expect(await driver.waitFor(imageFinder).then((value) => Image), Image);

    });

//    test('Record Videos', () async {
//      final buttonFinder = find.byValueKey('camera_button');
//      final startVideoFinder = find.byValueKey('take_video');
//      final stopVideoFinder = find.byValueKey('stop_video');
//
//      await Future.delayed(Duration(seconds: 5));
//      await driver.tap(buttonFinder);
//      await Future.delayed(Duration(seconds: 5));
//      await driver.tap(startVideoFinder);
//      await Future.delayed(Duration(seconds: 10));
//      await driver.tap(stopVideoFinder);
//      await Future.delayed(Duration(seconds: 5));
//
//    });

  });
}