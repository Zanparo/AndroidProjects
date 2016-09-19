package com.example.darrac_s.catalog;

import android.content.Intent;
import android.drm.DrmStore;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import java.io.File;

public class main extends AppCompatActivity {

    private File mCurrentFile;
    private boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView t = (TextView) findViewById(R.id.editText);
        t.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == DrmStore.Action.EXECUTE)
                {
                    if (t.getText().toString().equals(""))
                    if (t.getText().toString().equals("2566"))
                    {
                        Log.i("Pasta Box", "Valid");
                        Intent i = new Intent(main.this, catalogActivity.class);
                        startActivity(i);
                    }
                    else if (t.getText().toString().equals("2605"))
                    {
                        Log.i("Pasta box", "Video code detected");
                        Intent i = new Intent(main.this, videoActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Log.i("Pasta box code", t.getText().toString());
                    }
                }
                return false;
            }
        });
        if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.i("Pasta box", "External Storage not accessible");
            return;
        }
    }
}