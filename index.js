import React, { PropTypes } from 'react'
import {
  View,
  NativeModules,
  requireNativeComponent
} from 'react-native'

const { GoogleVRPanoramaManager } = NativeModules
const { inputType } = GoogleVRPanoramaManager

class PanoramaView extends React.Component {
  constructor(props) {
    super(props)

    this._onImageLoaded = this._onImageLoaded.bind(this)
    this._onImageLoadingFailed = this._onImageLoadingFailed.bind(this)
  }

  render() {
    return (
      <RNGoogleVRPanoramaView
        {...this.props}
        ref={ref => this.ref = ref}
      />
    )
  }

  _onImageLoaded() {
    if (this.props.onImageLoaded) this.props.onImageLoaded()
  }

  _onImageLoadingFailed() {
    if (this.props.onImageLoadingFailed) this.props.onImageLoadingFailed()
  }
}

PanoramaView.propTypes = {
  ...View.propTypes,

  imageUrl: React.PropTypes.string.isRequired,
  dimensions: React.PropTypes.shape({
    width: React.PropTypes.number,
    height: React.PropTypes.number,
  }),
  inputType: React.PropTypes.number,

  onImageLoaded: React.PropTypes.func,
  onImageLoadingFailed: React.PropTypes.func,
}

PanoramaView.defaultProps = {
  inputType: inputType.mono,
}

const RNGoogleVRPanoramaView = requireNativeComponent('RNGoogleVRPanorama', PanoramaView, {
  nativeOnly: {}
})

const GoogleVRPanorama = {
  PanoramaView,
  inputType,
}

module.exports = GoogleVRPanorama
