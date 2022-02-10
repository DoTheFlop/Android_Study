package com.example.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int pic = intent.getIntExtra("pic",0);
        String name = intent.getStringExtra("name");
        String money = intent.getStringExtra("money");
        String detail = intent.getStringExtra("detail");
        ImageView itemPic = (ImageView) findViewById(R.id.itemPic);
        TextView itemName = (TextView) findViewById(R.id.itemName);
        TextView itemMoney = (TextView) findViewById(R.id.itemMoney);
        TextView itemDetail = (TextView) findViewById(R.id.itemDetail);
        Button returnBtn = (Button) findViewById(R.id.returnBtn);

        itemPic.setImageResource(pic);
        itemName.setText("제품명: " + name);
        itemMoney.setText("가격: " + money);
        itemDetail.setText("세부내용: " + detail);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
