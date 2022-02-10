package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.project.R;
import com.example.project.ui.bookmark.MyAdapter3;
import com.example.project.ui.bookmark.MyViewHolder3;
import com.example.project.ui.home.MyViewHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class SecondActivity extends Activity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String bookName = intent.getStringExtra("bookName");
        String bookAuthor = intent.getStringExtra("Author");
        String bookCompany = intent.getStringExtra("Company");
        String bookId = intent.getStringExtra("bookId");
        String bookReservation = intent.getStringExtra("Reservation");
        int fromBookMarkCheck = intent.getIntExtra("fromBookMarkCheck", 0);

        TextView Name = (TextView) findViewById(R.id.second_BookName);
        TextView Author = (TextView) findViewById(R.id.second_BookAuthor);
        TextView Company = (TextView) findViewById(R.id.second_BookCompany);
        TextView Id = (TextView) findViewById(R.id.second_BookId);
        TextView Reservation = (TextView) findViewById(R.id.second_BookReservation);
        Button returnBtn = (Button) findViewById(R.id.returnBtn);
        Button bookmarkBtn = (Button) findViewById(R.id.bookmarkBtn);

        Name.setText("책이름: " + bookName);
        Author.setText("지은이: " + bookAuthor);
        Id.setText("식별번호: " + bookId);
        Reservation.setText("대출여부: " + bookReservation);
        Company.setText("출판사: " + bookCompany);

        if(fromBookMarkCheck == 0){
            bookmarkBtn.setVisibility(View.VISIBLE);
        }
        else {
            bookmarkBtn.setVisibility(View.GONE);
        }

        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{Random random = new Random();
                    String BookMarkNum = String.valueOf(random.nextInt(100) + 1);
                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("bookName", bookName);
                    hashMap.put("Author", bookAuthor);
                    hashMap.put("bookId", bookId);
                    hashMap.put("Reservation", bookReservation);
                    hashMap.put("Company", bookCompany);
                    hashMap.put("BookMarkNum", BookMarkNum);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("bookInFo");
                    ref.child(BookMarkNum).setValue(hashMap);
                    Toast.makeText(getApplicationContext(),"즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.w("SecondActivity", "즐겨찾기 추가 실패");
                }
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
