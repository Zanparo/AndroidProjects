package com.todolist.todolist;

import java.util.List;

/**
 * Created by samue on 09/01/2017.
 */

public class                    TagHandler {

    public List<String>            _tags;

    public void                 addTag(String value)
    {
        if (!_tags.contains(value))
        {
            _tags.add(value);
        }
    }

    public boolean          isTag(String value)
    {
        if (_tags.contains(value))
            return true;
        return false;
    }
}
