package com.example.darrac_s.catalog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.security.SecureRandom;

/**
 * Created by darrac_s on 14/09/2016.
 */
public class catalogActivity extends Activity {

    private File mLocation;
    private RetainedFile mRetain;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img);
        mLocation = new File(getString(R.string.path));
        if (mLocation.listFiles() == null) {
            Log.i("Pasta Box", "List files null");
            return;
        }
        file = randImg();
        final ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(BitmapFactory.decodeFile(file.getPath().toString()));
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file = randImg();
                img.setImageBitmap(BitmapFactory.decodeFile(file.getPath().toString()));
            }
        });
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(file), "image/jpeg");
                startActivity(i);
                return false;
            }
        });
    }

    protected File randImg()
    {
        SecureRandom sr = new SecureRandom();
        File f = null;
        while (f == null || !f.isFile() || !f.getName().contains("jpg")) {
            int random = sr.nextInt(mLocation.listFiles().length);
            f = mLocation.listFiles()[random];
        }
        return f;
    }
}
