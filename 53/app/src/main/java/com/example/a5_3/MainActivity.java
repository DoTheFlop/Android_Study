package com.example.a5_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(baseLayout,params);

        editText=new EditText(this);
        baseLayout.addView(editText);
        btn=new Button(this);
        btn.setText("버튼입니다.");
        btn.setBackgroundColor(Color.rgb(0,255,0));
        baseLayout.addView(btn);
        textView=new TextView(this);
        textView.setTextSize(50);
        textView.setTextColor(Color.BLACK);
        baseLayout.addView(textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Text;
                Text=editText.getText().toString();
                textView.setText(Text);
            }
        });
    }
}