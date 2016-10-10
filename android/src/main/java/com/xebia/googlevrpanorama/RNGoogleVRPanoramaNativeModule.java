package com.xebia.googlevrpanorama;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class RNGoogleVRPanoramaNativeModule extends ReactContextBaseJavaModule {
    private static final String REACT_CLASS = "GoogleVRPanoramaManager";

    private ReactApplicationContext	mReactContext;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    public RNGoogleVRPanoramaNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }
}
