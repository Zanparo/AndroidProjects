package com.todolist.todolist.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by samue on 09/01/2017.
 */

public interface            ITask extends Comparable<ITask>, Serializable {

    /**
     *  @returns the name of the task
     *  */
    public String           getName();

    /**
     *  @returns the date of the task
     *  */
    public Calendar getDueDate();

    /**
     *  @returns the content of the task
     *  */
    public String           getContent();

    /**
     *  @returns the date of creation of the task
     *  */
    public Calendar         getCreationDate();

    /**
     *  set the name of the task
     *  */
    public void             setName(String name);

    /**
     *  set the date of the task
     *  */
    public void             setDueDate(Calendar date);

    /**
     *  set the content of the task
     *  */
    public void             setContent(String content);

    /**
     *  add some content to _content
     *  */
    public void             addContent(String content);

    /**
     *  display informations about this ITask instance
     *  */
    public String           display();

    /**
     *  add a Tag for this task
     *  */
    public void             addTag(String value);

    /**
     *  static method to compare tasks by name
     *  */
    public static final Comparator<ITask> NAME_COMPARATOR = new Comparator<ITask>() {
        @Override
        public int compare(ITask o1, ITask o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    /**
     *  static method to compare tasks by due date
     *  */
    public static final Comparator<ITask> DUE_DATE_COMPARATOR = new Comparator<ITask>() {
        @Override
        public int compare(ITask o1, ITask o2) {
            return o1.getDueDate().compareTo(o2.getDueDate());
        }
    };

    /**
     *  static method to compare tasks by creation date
     *  */
    public static final Comparator<ITask> CREATION_DATE_COMPARATOR = new Comparator<ITask>() {
        @Override
        public int compare(ITask o1, ITask o2) {
            return o1.getCreationDate().compareTo(o2.getCreationDate());
        }
    };

    /**
     *  static method to compare tasks by content
     *  */
    public static final Comparator<ITask> CONTENT_COMPARATOR = new Comparator<ITask>() {
        @Override
        public int compare(ITask o1, ITask o2) {
            return o1.getContent().compareTo(o2.getContent());
        }
    };

    /**
     *  static method to compare tasks by progress
     *  */
    public static final Comparator<ITask> PROGRESS_COMPARATOR = new Comparator<ITask>() {
        @Override
        public int compare(ITask o1, ITask o2) {
            return Integer.compare(o1.getProgress(), o2.getProgress());
        }
    };

    public boolean isIdentical(ITask task);

    public int compareTo(ITask o);

    public int getId();

    public void setId(int _id);

    public boolean isValid();

    public void setProgress(int _progress);

    public int getProgress();
}
