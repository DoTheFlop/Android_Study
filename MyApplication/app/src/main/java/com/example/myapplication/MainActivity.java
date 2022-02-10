package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    CheckBox check1, check2;
    RadioGroup rGroup1;
    ImageView imageView1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check1 = (CheckBox) findViewById(R.id.checkBox1);
        check2 = (CheckBox) findViewById(R.id.checkBox2);

        check1.setOnCheckedChangeListener(mCheckListener);
        check2.setOnCheckedChangeListener(mCheckListener);

        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView1.setImageResource(R.drawable.q10);

        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);

        rGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                StringBuilder mStr = new StringBuilder();
                if (i == R.id.man){
                    mStr.append("MAN selected");
                    imageView1.setImageResource(R.drawable.q10);
                }
                else{
                    mStr.append("WOMAN selected");
                    imageView1.setImageResource(R.drawable.q10);
                }
                Toast.makeText(getApplicationContext(), mStr, Toast.LENGTH_SHORT).show();
            }
        });
    }

    CompoundButton.OnCheckedChangeListener mCheckListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            StringBuilder mStr = new StringBuilder();
            if(compoundButton.getId() == R.id.checkBox1)
                mStr.append(check1.getText().toString());
            else
                mStr.append(check2.getText().toString());

            if(b)
                mStr.append(" "+"checked");
            else
                mStr.append(" "+"Unchecked");

            Toast.makeText(getApplicationContext(), mStr, Toast.LENGTH_SHORT).show();
        }
    };
}