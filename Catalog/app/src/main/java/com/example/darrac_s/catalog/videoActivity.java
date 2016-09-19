package com.example.darrac_s.catalog;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by darrac_s on 15/09/2016.
 */
public class videoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_view);
        File first = null, second = null;
        Log.i("Pasta Box", "VideoActivity launched");

        for (File f : (new File(getString(R.string.path))).listFiles()) {
            if (f.getName().contains(".MOV")) {
                Log.i("Pasta Box", "First found ! ! ");
                first = f;
            }
            if (f.getName().contains(".mp4")) {
                Log.i("Pasta Box", "Second found ! ");
                second = f;
            }
        }
        if (first != null && second != null) {
            Log.i("Pasta Box", "Check ! ");
            VideoView vv = (VideoView) findViewById(R.id.videoView);
            MediaController mc = new MediaController(this);
            vv.setMediaController(mc);
            vv.setVideoPath(first.getPath());
            vv.start();
        }
    }
}
