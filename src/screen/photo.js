import React, { useState } from 'react';
import { FlatList, StyleSheet, TouchableOpacity, Text, View, Alert} from 'react-native';
import { RNCamera } from 'react-native-camera';


export default function Photo() {
  const styles = StyleSheet.create({
    container: {
      flex: 1,
      flexDirection: 'column',
      backgroundColor: 'black'
    },
    fab:{
      height: 60,
      width: 60,
      borderRadius: 100,
      position: 'absolute',
      bottom: 40,
      alignSelf: 'center',
      backgroundColor:'#9e9e9e',
      borderColor: '#ffffff',
      borderWidth: 4,
    },
  })

  // takePicture = async () => {
  //   if (this.RNCamera) {
  //     console.log("kepoto")
  //     const options = { quality: 0.5, base64: true };
  //     const data = await this.RNCamera.takePictureAsync(options);
  //     console.log(data.uri);
  //   }
  //   console.log("Gakepoto")
  // };

  takePicture = async () => {
    console.log("kepoto")
    const options = { quality: 0.5, base64: true };
    const data = await this.RNCamera.takePictureAsync(options);
    console.log(data.uri);
  };

  return (
    <View style={styles.container}>
      <RNCamera
        style={{ flex: 1, alignItems: 'center' }}
        type={RNCamera.Constants.Type.back}
        ref={ref => {
          this.camera = ref
        }}
      />
      <TouchableOpacity
        style={styles.fab}
        onPress={this.takePicture}
      />
    </View>
  )
}