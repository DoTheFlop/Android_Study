package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText edit;
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
    Button add, sub, division, multi, del, equal;
    int where;
    String num1, num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("계산기");
        button0 = (Button) findViewById(R.id.Button0);
        button1 = (Button) findViewById(R.id.Button1);
        button2 = (Button) findViewById(R.id.Button2);
        button3 = (Button) findViewById(R.id.Button3);
        button4 = (Button) findViewById(R.id.Button4);
        button5 = (Button) findViewById(R.id.Button5);
        button6 = (Button) findViewById(R.id.Button6);
        button7 = (Button) findViewById(R.id.Button7);
        button8 = (Button) findViewById(R.id.Button8);
        button9 = (Button) findViewById(R.id.Button9);
        equal = (Button) findViewById(R.id.Equals);

        edit = (EditText) findViewById(R.id.Edit1);

        add = (Button) findViewById(R.id.Add);
        multi = (Button) findViewById(R.id.Multi);
        sub = (Button) findViewById(R.id.Sub);
        division = (Button) findViewById(R.id.Division);
        del = (Button) findViewById(R.id.Del);

        View.OnClickListener number = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(v==button1){
                    edit.setText(edit.getText().toString()+1);
                }
                else if(v==button2){
                    edit.setText(edit.getText().toString()+2);
                }
                else if(v==button3){
                    edit.setText(edit.getText().toString()+3);
                }
                else if(v==button4){
                    edit.setText(edit.getText().toString()+4);
                }
                else if(v==button5){
                    edit.setText(edit.getText().toString()+5);
                }
                else if(v==button6){
                    edit.setText(edit.getText().toString()+6);
                }
                else if(v==button7){
                    edit.setText(edit.getText().toString()+7);
                }
                else if(v==button8){
                    edit.setText(edit.getText().toString()+8);
                }
                else if(v==button9){
                    edit.setText(edit.getText().toString()+9);
                }
                else if(v==button0){
                    edit.setText(edit.getText().toString()+0);
                }

                else if(v==add){
                    result = Double.parseDouble(edit.getText().toString());
                    edit.setText("");
                    where =1;
                }

                else if(v==sub){
                    result = Double.parseDouble(edit.getText().toString());
                    edit.setText("");
                    where =2;
                }

                else if(v==multi){
                    result = Double.parseDouble(edit.getText().toString());
                    edit.setText("");
                    where =3;
                }

                else if(v==division){
                    result = Double.parseDouble(edit.getText().toString());
                    edit.setText("");
                    where =4;
                }

                else if(v==equal){

                    if(where==1){
                        result = result + Double.parseDouble(edit.getText().toString());
                        edit.setText(Double.toString(result));
                    }

                    else if(where==2){
                        result = result - Double.parseDouble(edit.getText().toString());
                        edit.setText(Double.toString(result));
                    }

                    else if(where==3){
                        result = result * Double.parseDouble(edit.getText().toString());
                        edit.setText(Double.toString(result));
                    }

                    else if(where==4){
                        result = result / Double.parseDouble(edit.getText().toString());
                        edit.setText(Double.toString(result));
                    }

                }


                else if(v==del){
                    edit.setText("");
                }
            }
        };
        button1.setOnClickListener(number);
        button2.setOnClickListener(number);
        button3.setOnClickListener(number);
        button4.setOnClickListener(number);
        button5.setOnClickListener(number);
        button6.setOnClickListener(number);
        button7.setOnClickListener(number);
        button8.setOnClickListener(number);
        button9.setOnClickListener(number);
        button0.setOnClickListener(number);
        add.setOnClickListener(number);
        del.setOnClickListener(number);
        multi.setOnClickListener(number);
        sub.setOnClickListener(number);
        equal.setOnClickListener(number);
    }
}