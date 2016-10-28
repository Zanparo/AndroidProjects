package com.example.darrac_s.snoozer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class main extends Activity {

    public final AlarmFragment _a = new AlarmFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MusicSearcher ms = new MusicSearcher(getString(R.string.music_directory), (MediaController) findViewById(R.id.musicController));
        if (ms.getMusic() != null){
            Intent i = new Intent(this, MusicPlayer.class);
            i.putExtra("file", ms.getMusic());
            Log.i("Main", "Checkpoint");
            startActivity(i);
        }
    }

    public void showTimePickerDialog(View v) {
        _a.show(getFragmentManager(), "timePicker");
    }

    public void displayAlarm(View v) {
        TextView tv = (TextView) findViewById(R.id.timeDisplay);
        Calendar cal = _a.getCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
//        tc.setText(cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));
        tv.setText(cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));
    }

}
