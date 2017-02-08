package com.todolist.todolist.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.todolist.todolist.R;
import com.todolist.todolist.Utils;
import com.todolist.todolist.fragments.DeleteDialogFragment;
import com.todolist.todolist.fragments.TimePickerFragment;
import com.todolist.todolist.models.ITask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * TaskExaminationActivity class is used to examinate a task or to create a new one.
 * This activity is associated to the task_examination view
 * Created by darrac_s on 15/01/2017.
 */
public class TaskExaminationActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    private enum eDialogId {
        TIME,
        DELETE,
        CONTENT
    };

    private final Context context = this;
    private eDialogId switcher = eDialogId.TIME;
    private ITask task = null;
    private EditText name = null;
    private TextView content = null;
    private TextView dueTime = null;
    private CalendarView dueDate = null;
    private Calendar calendar;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
    private DeleteDialogFragment ddf = null;
    private SeekBar seekBar = null;
    private TextView progressText = null;

    /**
     * onCreate method is overrided from the AppCompatActivity class
     * This method is called when the activity is created.
     *
     * @param savedInstanceState The Bundle saved instance
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_examination);
        setSupportActionBar(((Toolbar) findViewById(R.id.display_toolbar)));

        //Fields recuperation in the view
        name = (EditText) findViewById(R.id.edit_name);
        content = (TextView) findViewById(R.id.edit_content);
        dueDate = (CalendarView) findViewById(R.id.display_due_date);
        dueTime = (TextView) findViewById(R.id.display_due_time);
        seekBar = (SeekBar) findViewById(R.id.progress);
        progressText = (TextView) findViewById(R.id.progress_text);

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(name.getText());

                // Set up the input
                final EditText input = new EditText(context);
                if (task.getContent() != null && !task.getContent().isEmpty())
                    input.setText(task.getContent());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setMinLines(6);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        task.setContent(input.getText().toString());
                        if (task.getContent() != null && !task.getContent().isEmpty()) {
                            int index = task.getContent().length();
                            if (task.getContent().substring(0, index).contains("\n"))
                                index = task.getContent().indexOf("\n");
                            index = (index > 20) ? 20 : index;
                            content.setText((index == task.getContent().length()) ? task.getContent() : task.getContent().substring(0, index) + " ...");
                        }
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                task.setProgress(progress);
                progressText.setText(String.format(getString(R.string.progress_string), progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Differenciation between task creation and task updating
        if ((task = (ITask) getIntent().getSerializableExtra("task")) != null) {
            calendar = task.getDueDate();
        } else {
            calendar = Calendar.getInstance();
            task = Utils.createTask("", calendar, "");
        }
        name.setText(task.getName());
        refresh();

        //update the calendater to the date selected
        dueDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //dueDate.setDate(calendar.get(Calendar.DATE));
            }
        });
    }

    /**
     * Validate method is called when the validate button is pressed.
     * This MUST end the activity and return the taks updated.
     *
     * @param v View concerned
     */
    public void validate(View v) {
        updateTask();
        if (!task.isValid()) {
            setResult(RESULT_CANCELED);
        } else {
            Intent intent = new Intent();
            intent.putExtra("task", task);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    /**
     * Called when the user pressed the delete button.
     * This MUST opens a dialog to confirm the task delation.
     *
     * @param v View concerned
     */
    public void delete(View v) {
        switcher = eDialogId.DELETE;
        ddf = new DeleteDialogFragment();
        ddf.show(getSupportFragmentManager(), "deleteDialog");
    }

    /**
     * refresh fields with task informations
     */
    public void refresh() {
        if (task.getContent().length() > 20) {
            content.setText(task.getContent().substring(0, 20) + " ...");
        } else {
            content.setText(task.getContent());
        }
        dueTime.setText(sdf.format(calendar.getTimeInMillis()));
        dueDate.setDate(calendar.getTimeInMillis(), true, true);
        seekBar.setProgress(task.getProgress());
        progressText.setText(String.format(getString(R.string.progress_string), task.getProgress()));

        String buffer;
        buffer = sdf.format(calendar.getTime());
        dueTime.setText(buffer);
    }

    /**
     * Set the informations of fields in the task
     */
    public void updateTask() {
        task.setName(name.getText().toString());
        task.setContent(content.getText().toString());
        task.setDueDate(calendar);
    }

    /**
     * Handle the dialog to pick up an hour.
     *
     * @param v Concerned view
     */
    public void showTimeFragment(View v) {
        switcher = eDialogId.TIME;
        TimePickerFragment frag = new TimePickerFragment();
        frag.setCalendar(calendar);
        frag.show(getFragmentManager(), "timePicker");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!task.isValid()) {
            setResult(RESULT_CANCELED);
        } else {
            Intent intent = new Intent();
            intent.putExtra("task", task);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    /**
     * Overrided from AppCompatActivity class
     * Called when a dialog is dismissed.
     * Boolean switcher is used to identify which dialog has been dismisssed.
     *
     * @param dialog DialogInterface dismissed.
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        if (switcher.equals(eDialogId.DELETE)) {
            if (ddf.getDelete()) {
                setResult(RESULT_OK);
                finish();
            }
        } else {
            if (switcher.equals(eDialogId.CONTENT)) {
            }
            refresh();
            updateTask();
        }
    }
}
