package com.example.darrac_s.snoozer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;

import java.io.File;
import java.security.SecureRandom;

/**
 * Created by darrac_s on 04/10/2016.
 */
public class MusicSearcher {
    private File _currentDirectory;
    private File _music = null;

    public MusicSearcher(String path, MediaController mc) {
        if (Access(path)) {
            _music = Navigate(_currentDirectory);
            if (_music == null) {
                Log.i("MusicSearcher", "GetRandom returns null");
            } else {
                Log.i("MusicSearcher", "Target=" + _music.getName());
            }
        }
    }

    public File Navigate(File directory) {
        if (!directory.exists() || directory.listFiles() == null || directory.listFiles().length == 0)
            return null;
        SecureRandom sr = new SecureRandom();
        int random = sr.nextInt(directory.listFiles().length);
        int i = 0;
        for (File tmp : directory.listFiles()) {
            if (i == random) {
                if (tmp.isFile() && (tmp.getName().contains(".mp3") || tmp.getName().contains(".flac")))
                    return tmp;
                else if (tmp.isDirectory()) {
                    directory = tmp;
                }
                return Navigate(directory);
            }
            i++;
        }
        return null;
    }

    public boolean Access(String path) {
        if (isExternalStorageReadable()) {
            _currentDirectory = new File(path);
            if (_currentDirectory.exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getMusic()
    {
        return _music;
    }

}
