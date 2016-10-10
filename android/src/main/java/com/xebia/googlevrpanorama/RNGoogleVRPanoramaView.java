package com.xebia.googlevrpanorama;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.util.Log;
import android.util.Pair;
import android.widget.RelativeLayout;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.google.vr.sdk.widgets.pano.VrPanoramaView.Options;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RNGoogleVRPanoramaView extends RelativeLayout {
    private static final String TAG = RNGoogleVRPanoramaView.class.getSimpleName();

    private android.os.Handler _handler;
    private RNGoogleVRPanoramaViewManager _manager;
    private Activity _activity;

    private VrPanoramaView panoWidgetView;

    private boolean loadImageSuccessful;

    private Map<URL, Bitmap> imageCache = new HashMap<>();
    private URL imageUrl;
    private Options panoOptions = new Options();
    private ImageLoaderTask imageLoaderTask;

    @UiThread
    public RNGoogleVRPanoramaView(Context context, RNGoogleVRPanoramaViewManager manager, Activity activity) {
        super(context);
        _handler = new android.os.Handler();
        _manager = manager;
        _activity = activity;
    }

    public void onAfterUpdateTransaction() {
        panoWidgetView = new VrPanoramaView(_activity);
        panoWidgetView.setEventListener(new ActivityEventListener());
        panoWidgetView.setStereoModeButtonEnabled(false);
        panoWidgetView.setInfoButtonEnabled(false);
        panoWidgetView.setFullscreenButtonEnabled(false);
        this.addView(panoWidgetView);

        if (imageLoaderTask != null) {
            imageLoaderTask.cancel(true);
        }
        imageLoaderTask = new ImageLoaderTask();
        imageLoaderTask.execute(Pair.create(imageUrl, panoOptions));
    }

    public void setImageUrl(String value) {
        if (imageUrl != null && imageUrl.toString().equals(value)) { return; }

        try {
            imageUrl = new URL(value);
        } catch(MalformedURLException e) {}
    }

    public void setInputType(int value) {
        if (panoOptions.inputType == value) { return; }
        panoOptions.inputType = value;
    }

    class ImageLoaderTask extends AsyncTask<Pair<URL, Options>, Void, Boolean> {
        protected Boolean doInBackground(Pair<URL, Options>... fileInformation) {
            final URL imageUrl = fileInformation[0].first;
            Options panoOptions = fileInformation[0].second;

            InputStream istr = null;
            Bitmap image;

            if (!imageCache.containsKey(imageUrl)) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) fileInformation[0].first.openConnection();
                    connection.connect();

                    istr = connection.getInputStream();

                    imageCache.put(imageUrl, BitmapFactory.decodeStream(istr));
                } catch (IOException e) {
                    Log.e(TAG, "Could not load file: " + e);
                    return false;
                } finally {
                    try {
                        istr.close();
                    } catch (IOException e) {
                        Log.e(TAG, "Could not close input stream: " + e);
                    }
                }
            }

            image = imageCache.get(imageUrl);

            panoWidgetView.loadImageFromBitmap(image, panoOptions);

            return true;
        }
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {
            loadImageSuccessful = true;
        }

        @Override
        public void onLoadError(String errorMessage) {
            loadImageSuccessful = false;

            Log.e(TAG, "Error loading pano: " + errorMessage);
        }
    }
}
