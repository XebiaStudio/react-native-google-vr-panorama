import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  View
} from 'react-native';
import GoogleVRPanorama, { PanoramaView } from 'react-native-google-vr-panorama'

export default class Example extends Component {
  onImageLoaded() {
    alert('success!')
  }

  onImageLoadingFailed() {
    alert('oh noes!')
  }

  render() {
    const imageUrl = 'http://blog.dsky.co/wp-content/uploads/2015/09/06-VikingVillage_stereo_thumb.jpg'
    const inputType = GoogleVRPanorama.inputType.stereo
    const dimensions = {
      width: 702,
      height: 702,
    }

    return (
      <View style={styles.container}>
        <PanoramaView
          style={styles.panorama}
          inputType={inputType}
          imageUrl={imageUrl}
          dimensions={dimensions}
          onImageLoaded={this.onImageLoaded}
          onImageLoadingFailed={this.onImageLoadingFailed}
        />
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  panorama: {
    flex: 1,
  },
});

AppRegistry.registerComponent('Example', () => Example);
