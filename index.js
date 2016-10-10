import React, { PropTypes } from 'react';
import {
  View,
  NativeModules,
  requireNativeComponent
} from 'react-native';

const { GoogleVRPanoramaManager } = NativeModules;
const { inputType } = GoogleVRPanoramaManager;

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

  imageUrl: React.PropTypes.string.isRequired,
  inputType: React.PropTypes.number,
}

PanoramaView.defaultProps = {
  inputType: inputType.mono,
}

const RNGoogleVRPanoramaView = requireNativeComponent('RNGoogleVRPanorama', PanoramaView, {
  nativeOnly: {}
});

const GoogleVRPanorama = {
  PanoramaView,
  inputType,
};

module.exports = GoogleVRPanorama;
