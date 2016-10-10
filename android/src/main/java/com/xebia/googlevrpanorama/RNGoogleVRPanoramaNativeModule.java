package com.xebia.googlevrpanorama;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

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

    public static final int[] inputTypes = new int[] {
        VrPanoramaView.Options.TYPE_MONO,
        VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER
    };

    @Override
    public @Nullable Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap<>();

        HashMap<String, Object> inputType = new HashMap<>();

        inputType.put("mono", 0);
        inputType.put("stereo", 1);

        constants.put("inputType", inputType);

        return constants;
    }
}
