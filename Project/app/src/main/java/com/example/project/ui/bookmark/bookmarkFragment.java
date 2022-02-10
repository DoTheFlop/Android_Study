package com.example.project.ui.bookmark;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class bookmarkFragment extends Fragment {

    private bookmarkViewModel bookmarkViewModel;
    private RecyclerView mRecyclerView3;
    private RecyclerView.Adapter mAdapter3;
    private RecyclerView.LayoutManager mLayoutManager3;
    ArrayList<bookInFo> myDataset3 = new ArrayList<bookInFo>();
    Button delAll;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookmarkViewModel =
                new ViewModelProvider(this).get(bookmarkViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bookmark, container, false);

        mRecyclerView3 = (RecyclerView) root.findViewById(R.id.bookMarkRecyclerView);
        mLayoutManager3 = new LinearLayoutManager(getActivity());
        mRecyclerView3.setLayoutManager(mLayoutManager3);

        mAdapter3 = new MyAdapter3(myDataset3);
        mRecyclerView3.setAdapter(mAdapter3);

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("bookInFo");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myDataset3.clear();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    //데이터를 담기위한 객체 모델
                    bookInFo bookinfo = ds.getValue(bookInFo.class);
                    //이 객체를 리스트에 넣는다.
                    myDataset3.add(bookinfo);
                    //이 리스트들을 어댑터에 넣어진다.
                }
                mAdapter3.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        delAll = (Button) root.findViewById(R.id.delAll);
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.removeValue();
            }
        });
        return root;
    }
}
