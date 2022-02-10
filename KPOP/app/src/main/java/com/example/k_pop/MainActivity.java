package com.example.k_pop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    CheckBox bts, twice;
    RadioGroup btsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bts = (CheckBox) findViewById(R.id.Bts);
        twice = (CheckBox) findViewById(R.id.Twice);
        btsGroup = (RadioGroup) findViewById(R.id.BtsGroup);
        bts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}