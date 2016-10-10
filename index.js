import React, { PropTypes } from 'react';
import {
  View,
  NativeModules,
  requireNativeComponent
} from 'react-native';

const { GoogleVRPanoramaManager } = NativeModules;

class PanoramaView extends React.Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <RNGoogleVRPanoramaView
        {...this.props}
        ref={ref => this.ref = ref}
      />
    )
  }
}

PanoramaView.propTypes = {
  ...View.propTypes,
  image: React.PropTypes.string,
}

const RNGoogleVRPanoramaView = requireNativeComponent('RNGoogleVRPanorama', PanoramaView, {
  nativeOnly: {}
});

const GoogleVRPanorama = { PanoramaView };

module.exports = GoogleVRPanorama;
