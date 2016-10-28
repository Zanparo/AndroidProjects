package com.example.darrac_s.snoozer;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by darrac_s on 24/09/2016.
 */
public class AlarmFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    protected Calendar _calendar = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (_calendar == null)
        {
            _calendar = Calendar.getInstance();
        }
        int hour = _calendar.get(Calendar.HOUR_OF_DAY);
        int minute = _calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        _calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        _calendar.set(Calendar.MINUTE, minute);
        Log.i("onTimeSet", "Calendar set : " + _calendar.get(Calendar.HOUR_OF_DAY) + ":" + _calendar.get(Calendar.MINUTE));
    }

    public Calendar getCalendar() {
        return _calendar;
    }
}
