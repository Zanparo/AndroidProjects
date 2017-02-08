package com.todolist.todolist.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * ATask class implements ITask.
 * This class is used as a model to represent all kinds of task.
 * Created by darrac_s on 09/01/2017.
 */
public class ATask implements ITask {

    private String          _name = "";
    private Calendar        _date;
    private String          _content = "";
    private Calendar        _createdDate = Calendar.getInstance();
    private List<String>    _tags = new ArrayList<>();
    private int             _id;
    private int             _progress = 0;

    /**
     * Constructor of the ATask class
     * @param name name of the task
     * @param date date of the task
     * @param content content of the task
     * @param id id of the task
     */
    public ATask(String name, Calendar date, String content, int id)
    {
        _id = id;
        _name = name;
        _date = date;
        _content = content;
    }

    public ATask(ITask task)
    {
        _name = task.getName();
        _content = task.getContent();
        _progress = task.getProgress();
        _id = task.getId();
        _date = task.getDueDate();
        _createdDate = task.getCreationDate();
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public void setDueDate(Calendar date) {
        _date = date;
    }

    @Override
    public void setContent(String content) {
        _content = content;
    }

    @Override
    public void addContent(String content) {
        _content.concat(content);
    }

    @Override
    public String display() {
        String s = _name + "/" + _date.toString() + "/" + _content;
        return s;
    }

    @Override
    public String getContent() {
        return _content;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public Calendar getCreationDate() {
        return _createdDate;
    }

    @Override
    public Calendar getDueDate() {
        return _date;
    }

    @Override
    public void addTag(String value) {
        if (!_tags.contains(value))
            _tags.add(value);
    }

    @Override
    public boolean isIdentical(ITask task) {
        if (task.getName().equals(_name) && task.getContent().equals(_content) && task.getDueDate().equals(_date))
            return true;
        return false;
    }

    @Override
    public int compareTo(ITask o) {
        return o.getName().compareTo(getName());
    }

    @Override
    public int getId() {
        return _id;
    }

    @Override
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * Check if the task is valid.
     * Mainly by checking the name of the task.
     * @return true if the task is valid.
     */
    @Override
    public boolean isValid()
    {
        if (_name == null || _name.isEmpty() || _name.trim().isEmpty())
            return false;
        return true;
    }

    @Override
    public int getProgress() {
        return _progress;
    }

    @Override
    public void setProgress(int _progress) {
        this._progress = _progress;
    }
}
