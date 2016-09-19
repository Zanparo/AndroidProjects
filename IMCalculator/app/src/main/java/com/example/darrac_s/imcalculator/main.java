package com.example.darrac_s.imcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView edit_height = ((TextView) findViewById(R.id.edit_height));
        final TextView edit_weight = ((TextView) findViewById(R.id.edit_weight));
        final TextView result = ((TextView) findViewById(R.id.result_text));
        final Toast toast = Toast.makeText(this, getResources().getString(R.string.toast), Toast.LENGTH_SHORT);
        Button calcul = (Button) findViewById(R.id.calcul_button);
        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((edit_height.getText().toString()).isEmpty() || (edit_weight.getText().toString()).isEmpty()) {
                    toast.show();
                    return;
                }
                try {
                    double height = Double.valueOf((edit_height.getText().toString()));
                    double weight = Double.valueOf((edit_weight.getText().toString()));
                    if (Double.isInfinite(height) || Double.isNaN(height) || Double.isInfinite(weight) || Double.isNaN(weight)) {
                        toast.show();
                        return;
                    } else {
                        if ((((RadioButton) findViewById(R.id.centimeter_button)).isChecked())) {
                            height /= 100;
                        }
                        double imc = weight / Math.pow(height, 2);
                        ((TextView) findViewById(R.id.result_text)).setText(getResources().getString(R.string.result) + " " + imc);
                    }
                } catch (Exception ex) {
                    toast.show();
                    return;
                }
            }
        });
        Button raz = (Button) findViewById(R.id.raz_button);
        raz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_height.setText("");
                edit_height.setHint(getResources().getString(R.string.height_hint));
                edit_weight.setText("");
                edit_weight.setHint(getResources().getString(R.string.weight_hint));
                ((TextView) findViewById(R.id.result_text)).clearComposingText();
                ((TextView) findViewById(R.id.result_text)).setText(getResources().getString(R.string.result_text));
            }
        });
        TextWatcher list = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                result.setText(getResources().getString(R.string.result_text));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        edit_height.addTextChangedListener(list);
        edit_weight.addTextChangedListener(list);
        handleBigButton();
    }

    public void handleBigButton() {
        final Button big = (Button) findViewById(R.id.big_button);
        big.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                double x = event.getX();
                double y = event.getY();
                big.setTextSize((float) (Math.abs(x - big.getWidth() / 2) + Math.abs(y - big.getHeight() / 2)));
                return true;
            }
        });
    }
}
