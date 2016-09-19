package com.example.darrac_s.catalog;

import android.app.Fragment;
import android.os.Bundle;

import java.io.File;

/**
 * Created by darrac_s on 15/09/2016.
 */
public class RetainedFile extends Fragment {

    private File data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setData(File f)
    {
        data = f;
    }

    public File getData()
    {
        return data;
    }
}
