package com.todolist.todolist.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.todolist.todolist.R;
import com.todolist.todolist.models.ITask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * TaskAdapter class extending ArrayAdapter
 * This class is used to display tasks in a ListView
 * Created by darrac_s on 10/01/2017.
 */
public class TaskAdapter extends ArrayAdapter<ITask> {

    static class ViewHolder {
        public TextView name;
        public TextView time;
        public ProgressBar progress;
    }

    private final Activity _context;
    private final ArrayList<ITask> _tasks;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm '\n' dd/MM/yyyy");

    /**
     * Constructor of TaskAdapter class
     *
     * @param context Context
     * @param tasks   ArrayList of tasks
     */
    public TaskAdapter(Activity context, ArrayList<ITask> tasks) {
        super(context, 0, tasks);
        _context = context;
        _tasks = tasks;
    }

    /**
     * Used to get the View of an item in the list
     *
     * @param position    of the item
     * @param convertView
     * @param parent      the parent view
     * @return the View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = _context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_grid, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.list_grid_name);
            viewHolder.time = (TextView) rowView.findViewById(R.id.list_grid_time);
            viewHolder.progress = (ProgressBar) rowView
                   .findViewById(R.id.progressBar);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.name.setText(_tasks.get(position).getName());
        holder.time.setText(sdf.format(_tasks.get(position).getDueDate().getTime()));
        holder.progress.setProgress(_tasks.get(position).getProgress());
        return rowView;
    }
    /*@NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item, parent, false);

        TextView taskName = (TextView) convertView.findViewById(R.id.list_task_name);
        TextView taskDueDate = (TextView) convertView.findViewById(R.id.list_task_due_time);

        taskName.setText(_tasks.get(position).getName());
        taskDueDate.setText(_tasks.get(position).getDueDate().toString());
        Log.i("Text set", "Task name =" + _tasks.get(position).getName());
        return convertView;
    }*/

    /**
     * refresh the view
     */
    public void refresh() {
        notifyDataSetChanged();
    }
}
