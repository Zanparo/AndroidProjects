package com.todolist.todolist.activities;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.todolist.todolist.R;
import com.todolist.todolist.adapters.NotifsPublisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 06/02/17.
 */

public class NotifsActivity extends AppCompatActivity {

    private Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notif_layout);
        int delay = getIntent().getIntExtra("delay", 0);
        Log.i("Hello", "Hello");
        scheduleNotification(getNotification("Hello"), delay);
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotifsPublisher.class);
        notificationIntent.putExtra(NotifsPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotifsPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Log.i("Delay", "=" + delay);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.launcher);
        return builder.build();
    }

    private void cancelAll()
    {
// set up alarm
        List<PendingIntent> mPendingIntentList = new ArrayList<PendingIntent>();
        Intent alarmIntent = new Intent(getApplicationContext(),NotifsPublisher.class);
        alarmIntent.setData(Uri.parse("custom://" + NotifsPublisher.NOTIFICATION_ID));
        alarmIntent.setAction(String.valueOf(NotifsPublisher.NOTIFICATION_ID));
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        PendingIntent displayIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

// add pending intent in list
        mPendingIntentList.add(displayIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmDateTime, displayIntent);

        //cancel all alarms
        for(int idx = 0 ; idx < mPendingIntentList.size() ; idx++){
            alarmManager .cancel(mPendingIntentList.get(idx));
        }


    }


}
