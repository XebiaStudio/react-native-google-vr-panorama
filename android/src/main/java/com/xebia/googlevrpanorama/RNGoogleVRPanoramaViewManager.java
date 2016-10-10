package com.xebia.googlevrpanorama;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNGoogleVRPanoramaViewManager extends SimpleViewManager<RNGoogleVRPanoramaView> {
    private static final String REACT_CLASS = "RNGoogleVRPanorama";

    private ReactApplicationContext _context;

    public RNGoogleVRPanoramaViewManager(ReactApplicationContext context) {
        super();
        _context = context;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public RNGoogleVRPanoramaView createViewInstance (ThemedReactContext context) {
        return new RNGoogleVRPanoramaView(context, this, context.getCurrentActivity());
    }

    @Override
    protected void onAfterUpdateTransaction(RNGoogleVRPanoramaView view) {
        super.onAfterUpdateTransaction(view);
        view.onAfterUpdateTransaction();
    }

    public ReactApplicationContext getContext() {
        return _context;
    }

    @ReactProp(name = "imageUrl")
    public void setImageUrl(RNGoogleVRPanoramaView view, String imageUrl) {
        view.setImageUrl(imageUrl);
    }

    @ReactProp(name = "inputType")
    public void setInputType(RNGoogleVRPanoramaView view, int type) {
        view.setInputType(RNGoogleVRPanoramaNativeModule.inputTypes[type]);
    }
}
