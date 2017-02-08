package com.todolist.todolist;

import android.widget.DatePicker;

import com.todolist.todolist.models.ATask;
import com.todolist.todolist.models.ITask;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by samue on 17/01/2017.
 */

public class Utils {

    public static int currentId = 0;

    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public static ITask createTask(String name, Calendar date, String content){
        return new ATask(name, date, content, currentId++);
    }
}
