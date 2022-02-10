package com.example.simplediary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int check = 0;
    DatePicker dp;
    EditText edtDiary, password, password2;
    Button btnWrite;
    String fileName;
    View dialogView, dialogView2;

    Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단 일기장");

        dp = (DatePicker)findViewById(R.id.datePicker1);
        edtDiary = (EditText)findViewById(R.id.edtDiary);
        btnWrite = (Button)findViewById(R.id.btnWrite);

        mSwitch = (Switch)findViewById(R.id.switch1);

        SharedPreferences settings = getSharedPreferences("testShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dialogView = (View)View.inflate(MainActivity.this, R.layout.dialogview, null);
        dlg.setView(dialogView);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.putString("name", password.getText().toString());
                editor.commit();
                check = 1;
            }
        });
        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSwitch.setChecked(false);
            }
        });

        AlertDialog.Builder dlg2 = new AlertDialog.Builder(MainActivity.this);
        dialogView2 = (View)View.inflate(MainActivity.this, R.layout.dialogview_2, null);
        dlg2.setView(dialogView2);
        dlg2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                check = 0;
                String str = settings.getString("name", "");
                String pw = password2.getText().toString();
                if (str.equals(pw)){
                    mSwitch.setChecked(false);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Password wrong", Toast.LENGTH_SHORT).show();
                    mSwitch.setChecked(true);
                    editor.clear();
                }
            }
        });
        dlg2.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSwitch.setChecked(true);
            }
        });

        mSwitch.setOnCheckedChangeListener((CompoundButton, v) -> {
            if (dialogView.getParent() != null) {
                ((ViewGroup) dialogView.getParent()).removeView(dialogView);
            }
            if (dialogView2.getParent() != null) {
                ((ViewGroup) dialogView2.getParent()).removeView(dialogView2);
            }

            if(v == true && check == 0) {
                password = (EditText) dialogView.findViewById(R.id.editView);
                dlg.show();
            }
            else if (v == false && check == 1) {
                password2 = (EditText) dialogView2.findViewById(R.id.editView2);
                dlg2.show();
            }
        });
        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) +"_"
                        + Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(fileName);

                edtDiary.setText(str);

                if (mSwitch.isChecked()){
                    btnWrite.setEnabled(false);
                }

                else
                    btnWrite.setEnabled(true);
            }
        });
        btnWrite.setOnClickListener(v -> {
            try{
                if(mSwitch.isChecked()){
                    Toast.makeText(getApplicationContext(),"ReadOnly mode", Toast.LENGTH_SHORT).show();
                    return;
                }
                FileOutputStream outFs = openFileOutput(fileName, MODE_PRIVATE);
                String str = edtDiary.getText().toString();
                outFs.write(str.getBytes());
                outFs.close();
                Toast.makeText(getApplicationContext()," 이 저장됨", Toast.LENGTH_SHORT).show();
            } catch (IOException e){
                Toast.makeText(getApplicationContext(),"error:" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    String readDiary(String fName){
        String diaryStr = null;
        FileInputStream inFs;
        if(mSwitch.isChecked()) {
            edtDiary.setHint("잠김");
        }
        else{
            try {
                inFs = openFileInput(fName);
                byte[] txt = new byte[500];
                inFs.read(txt);
                inFs.close();
                diaryStr = (new String(txt)).trim();
                btnWrite.setText("수정 하기");
            } catch (IOException e) {
                edtDiary.setHint("일기 없음");
                btnWrite.setText("새로 저장");
            }
        }
        return diaryStr;
    }
}