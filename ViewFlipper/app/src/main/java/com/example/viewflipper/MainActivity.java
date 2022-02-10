package com.example.viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    RatingBar ratingBar;
    int imageNum=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.honey);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.eclair);
        viewFlipper.addView(imageView1);
        viewFlipper.addView(imageView2);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(imageNum==1)
                    Toast.makeText(getApplicationContext(),"도넛 별점: "+ rating,Toast.LENGTH_SHORT).show();
                else if(imageNum==2)
                    Toast.makeText(getApplicationContext(),"컵케이크 별점: "+ rating,Toast.LENGTH_SHORT).show();
                else if(imageNum==3)
                    Toast.makeText(getApplicationContext(),"허니 별점: "+ rating,Toast.LENGTH_SHORT).show();
                else  if(imageNum==4)
                    Toast.makeText(getApplicationContext(),"에클레어 별점: "+ rating,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void nextBtnClick(View v){
        viewFlipper.showNext();
        imageNum = imageNum + 1;
        if(imageNum==5)
            imageNum=1;
    }
    public void previousBtnClick(View v){
        viewFlipper.showPrevious();
        imageNum = imageNum - 1;
        if(imageNum==0)
            imageNum=4;
    }
}