package com.example.picvote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView text[] = new TextView[3];
    Integer textId[] = {R.id.text1, R.id.text2, R.id.text3};
    final int voteCount[] = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("선호도 투표");

        for(int i = 0; i < 3; i++){
            voteCount[i] = 0;
        }

        ImageView image[] = new ImageView[3];
        Integer imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3};

        final String imgName[] = {"강아지1", "강아지2", "강아지3"};

        for(int i = 0; i < imageId.length; i++){
            final int index;
            index = i;
            image[index] = (ImageView)findViewById(imageId[index]);
            text[index] = (TextView)findViewById(textId[index]);
            image[index].setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    voteCount[index]++;
                    text[index].setText("투표수: " + voteCount[index]);
                }
            });
        }

        Button btnFinish = (Button) findViewById(R.id.btnResult);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int[] point = data.getIntArrayExtra("Point");
            for(int i = 0; i < textId.length; i++){
                final int index;
                index = i;
                voteCount[index] = point[index];
                text[index] = (TextView)findViewById(textId[index]);
                text[index].setText("투표수: " + point[index]);
            }
        }
    }
}