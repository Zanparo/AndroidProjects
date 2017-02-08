package com.todolist.todolist;

import com.todolist.todolist.models.ITask;

import org.junit.Test;

import java.util.Calendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ITaskTest {


    @Test
    public void taskWithoutNameShouldNotBeValid()
    {
        ITask task = Utils.createTask("", Calendar.getInstance(), "");

    }

}
