package com.todolist.todolist.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.todolist.todolist.R;
import com.todolist.todolist.adapters.TaskAdapter;
import com.todolist.todolist.controllers.TaskController;
import com.todolist.todolist.models.ITask;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samue on 11/01/2017.
 * Class DisplayTaskActivity is used as the activity which displays all user tasks
 * using a ListView.
 */
public class DisplayTasksActivity extends AppCompatActivity {

    private final Context context = this;
    private ITask copyTask = null;
    private TaskAdapter taskAdapter;
    private TaskController taskController;
    private ListView lv;
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private FileInputStream fis;

    /**
     * onCreate methods is a override of AppCompatActivity class.
     * This method is called when the Activity is created.
     *
     * @param savedInstanceState The Bundle saved instance.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_task);

        //taskController creation and load of saved tasks
        taskController = new TaskController();

        if (getIntent().hasExtra("load")) {
            boolean bool = getIntent().getBooleanExtra("load", false);
            loadTasks(bool);
        }

        //set up action bar and listView display
        taskAdapter = new TaskAdapter(this, (ArrayList<ITask>) taskController.getTasks());
        setSupportActionBar(((Toolbar) findViewById(R.id.display_toolbar)));
        lv = (ListView) findViewById(R.id.list_task_view);
        lv.setAdapter(taskAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                copyTask = (ITask) lv.getItemAtPosition(position);
                Intent intent = new Intent(context, TaskExaminationActivity.class);
                intent.putExtra("task", copyTask);
                createNotification(copyTask);
                taskController.removeTask((ITask) lv.getItemAtPosition(position));
                startActivityForResult(intent, getResources().getInteger(R.integer.TASK_EXAM));
            }
        });
    }

    /**
     * onCreateOptionsMenu is overrided from AppCompatActivity
     *
     * @param menu The menu concerned
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final ArrayList<String> array = new ArrayList<>();
        Spinner spinner;

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        spinner = (Spinner) MenuItemCompat.getActionView(menu.findItem(R.id.order_spinner));

        // Set up the array adaptater used by the spinner to chose list order
        for (TaskController.eTaskOrder ordere : TaskController.eTaskOrder.values()) {
            array.add(ordere.toString().toLowerCase());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // set the adapter to provide layout of rows and content
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = array.get(position);
                for (TaskController.eTaskOrder ord : TaskController.eTaskOrder.values()) {
                    if (select.equals(ord.toString().toLowerCase())) {
                        taskController.orderBy(ord);
                    }
                }
                taskAdapter.refresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return true;
    }

    /**
     * onOptionsItemSelected is overrided from AppCompatActivity class.
     *
     * @param item The item of the menu selected.
     * @return true if all went fine.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                break;
            }
            case R.id.create_task: {
                startActivityForResult(new Intent(context, TaskExaminationActivity.class), getResources().getInteger(R.integer.TASK_CREATE));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * onActivityResult method is overrided from AppCompatActivity class.
     *
     * @param requestCode The request code of the activity ended.
     * @param resultCode  The result code of the activity ended.
     * @param data        The intent returned by the activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && data.hasExtra("task")) {
            ITask task = (ITask) data.getSerializableExtra("task");
            taskController.addTask(task);
        } else if (resultCode == RESULT_CANCELED) {
            Toast t = Toast.makeText(getApplicationContext(), R.string.no_update, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.BOTTOM, 0, 0);
            t.show();
            if (copyTask != null) {
                taskController.addTask(copyTask);
                copyTask = null;
            }
        }
        taskAdapter.refresh();
    }

    /**
     * onPause method is overrided from AppCompatActivity class.
     * This it called when the activity is paused and tasks need to be save.
     */
    @Override
    protected void onPause() {
        saveTasks(null);
        super.onPause();
    }

    /**
     * saveTasks method is used to save task in a local file.
     *
     * @param v View concerned.
     * @return true if all went fine.
     */
    public boolean saveTasks(View v) {
        final List<ITask> tasks = taskController.getTasks();
        try {
            deleteFile(getString(R.string.pathname));
            fos = openFileOutput(getString(R.string.pathname), Context.MODE_APPEND);
            oos = new ObjectOutputStream(fos);
            oos.flush();
            oos.writeObject(tasks);
            oos.close();
            fos.close();
            Log.i("Save:", tasks.size() + " tasks");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * loadTasks method is used to load tasks from an internal file.
     *
     * @return tasks loaded.
     */
    public List<ITask> loadTasks(boolean bool) {
        if (!bool)
            return null;
        List<ITask> tasks = new ArrayList<>();
        try {
            fis = openFileInput(getString(R.string.pathname));
            ois = new ObjectInputStream(fis);
            tasks = (List<ITask>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            Toast t = Toast.makeText(getApplicationContext(), R.string.no_task, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.BOTTOM, 0, 0);
            t.show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast t = Toast.makeText(getApplicationContext(), R.string.no_task, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.BOTTOM, 0, 0);
            t.show();
            e.printStackTrace();
        }
        if (!tasks.isEmpty())
            taskController.addTasks(tasks);
        Log.i("Load:", tasks.size() + " tasks");
        return tasks;
    }

    public void refresh(View v) {
        taskAdapter.refresh();
        Log.i("Refresh", "OK");
    }

    public void createNotification(ITask task) {
        Intent resultIntent = new Intent(this, DisplayTasksActivity.class);
        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(getString(R.string.app_name)).setContentText("End of the task \"" + task.getName() + "\" is now !").setContentIntent(resultPendingIntent);
        int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, builder.build());
    }

    public void launchNotif(View v)
    {
        Intent i = new Intent(context, NotifsActivity.class);
        i.putExtra("delay", 20000);
        Log.i("Hey", "Heyyyyy");
        startActivity(i);
    }
}
