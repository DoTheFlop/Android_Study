package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    CheckBox bts, twice;
    RadioGroup btsGroup, twiceGroup, fitGroup;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bts = (CheckBox) findViewById(R.id.Bts);
        twice = (CheckBox) findViewById(R.id.Twice);
        btsGroup = (RadioGroup) findViewById(R.id.BtsGroup);
        twiceGroup = (RadioGroup) findViewById(R.id.TwiceGroup);
        fitGroup = (RadioGroup) findViewById(R.id.fit);
        imageView = (ImageView) findViewById(R.id.imageView);

        bts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    btsGroup.setVisibility(View.VISIBLE);
                    twice.setChecked(false);
                }
                else
                    btsGroup.setVisibility(View.GONE);
            }
        });

        twice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    twiceGroup.setVisibility(View.VISIBLE);
                    bts.setChecked(false);
                }
                else
                    twiceGroup.setVisibility(View.GONE);
            }
        });
        twiceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.sa)
                    imageView.setImageResource(R.drawable.sa);
                else if(checkedId == R.id.na)
                    imageView.setImageResource(R.drawable.na);
                else if(checkedId == R.id.che)
                    imageView.setImageResource(R.drawable.che);
            }
        });
        btsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.jung)
                    imageView.setImageResource(R.drawable.jung);
                else if(checkedId == R.id.ji)
                    imageView.setImageResource(R.drawable.ji);
                else if(checkedId == R.id.tae)
                    imageView.setImageResource(R.drawable.tae);
            }
        });
        fitGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.fitCenter)
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                else if(checkedId == R.id.fitXY)
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                else if(checkedId == R.id.center)
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                else if(checkedId == R.id.matrix) {
                    Matrix matrix = imageView.getImageMatrix();
                    float scale = 2f;
                    matrix.setScale(scale, scale);
                    imageView.setImageMatrix(matrix);
                }
            }
        });
    }
}