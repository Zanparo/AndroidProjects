package com.example.darrac_s.snoozer;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;

import java.io.File;
import java.io.IOException;

/**
 * Created by darrac_s on 05/10/2016.
 */
public class MusicPlayer extends Activity {

   // private Intent _intent = new Intent(Intent.ACTION_VIEW);
    private File _currentFile = null;
    private MusicController _mc;
    private MediaPlayer _mp = new MediaPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MusicPlayer", "MusicPlayer activity created");
        setContentView(R.layout.music_player_interface);
        _currentFile = (File) getIntent().getSerializableExtra("file");
        Log.i("MusicPlayer", "File get : " + _currentFile);
        Play(_currentFile);
    }

    public boolean Play(File file)
    {
        if (!file.exists() || (!file.getName().contains(".mp3") && !file.getName().contains(".flac")))
            return false;
        Log.i("MusicPlayer", "Playing " + _currentFile);
        _currentFile = file;
       // _intent.setDataAndType(Uri.fromFile(_currentFile), "audio/*");
        MediaController mc = (MediaController) findViewById(R.id.musicController);
        try {
            Log.i("Play" , "Launched");
            _mp.reset();
            _mp.setDataSource(_currentFile.getPath());
            _mp.prepare();
            _mp.start();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Exception :", e.toString());
        }
        // startActivity(_intent);
        return true;
    }



/*    private void setController(){
    //set the controller up
        _mc = new MusicController(this);
        _mc.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrev();
            }
        });
        _mc.setMediaPlayer(this);
        _mc.setAnchorView(findViewById(R.id.song_list));
        _mc.setEnabled(true);
    }*/

    private void playPrev() {

    }

    private void playNext() {
    }
}
