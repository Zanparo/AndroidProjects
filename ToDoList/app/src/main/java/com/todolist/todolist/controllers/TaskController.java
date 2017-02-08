package com.todolist.todolist.controllers;

import com.todolist.todolist.models.ITask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TaskController class is used to manage tasks.
 * Created by darrac_s on 09/01/2017.
 */
public class TaskController {

    private eTaskOrder _order = eTaskOrder.NAME;
    private List<ITask> _tasks = new ArrayList();

    /**
     * Enumeration to define the order wanted.
     */
    public enum eTaskOrder {
        NAME,
        DUEDATE,
        CONTENT,
        CREATIONDATE,
        PROGRESS
    }

    /**
     * This method is used to add a task to the controller.
     * @param task Task to add
     */
    public void addTask(ITask task) {
        _tasks.add(task);
    }

    /**
     * Uses to add several tasks to the TaskController
     * @param tasks tasks to add.
     */
    public void addTasks(List<ITask> tasks) { _tasks.addAll(tasks);  }

    /**
     * Used to remove a task from the taskController
     * @param task the task to remove
     */
    public void removeTask(ITask task) {
        if (_tasks.contains(task))
            _tasks.remove(task);
    }

    /**
     * Used to change the sort order in taskController
     * @param order sort order chose.
     * @return The new list correctly sorted
     */
    public List<ITask> orderBy(final eTaskOrder order) {
        switch (order) {
            case NAME:
                Collections.sort(_tasks, ITask.NAME_COMPARATOR);
                break;
            case DUEDATE:
                Collections.sort(_tasks, ITask.DUE_DATE_COMPARATOR);
                break;
            case CONTENT:
                Collections.sort(_tasks, ITask.CONTENT_COMPARATOR);
                break;
            case CREATIONDATE:
                Collections.sort(_tasks, ITask.CREATION_DATE_COMPARATOR);
                break;
            default:
                Collections.sort(_tasks, ITask.NAME_COMPARATOR);
                break;
        }
        _order = order;
        return _tasks;
    }

    /**
     * Used to get tasks from the taskController
     * @return the list of tasks
     */
    public List<ITask> getTasks() {
        return _tasks;
    }

    /**
     * Used to flush the taskController
     */
    public void flush()
    {
        _tasks.clear();
    }
}
