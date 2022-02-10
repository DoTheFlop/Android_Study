package com.example.project.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.SecondActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<com.example.project.ui.home.MyViewHolder> {

    private ArrayList<com.example.project.ui.home.PaintTitle> mDataset;
    public  MyAdapter(ArrayList<PaintTitle> myDataset) {
        mDataset = myDataset;
    }
        @Override
    public com.example.project.ui.home.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item1, parent, false);
        com.example.project.ui.home.MyViewHolder vh = new com.example.project.ui.home.MyViewHolder(v);
        return  vh;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv1.setText(mDataset.get(position).title);
        holder.tv2.setText(mDataset.get(position).reservation);
        holder.tv3.setText(mDataset.get(position).author);
        holder.tv4.setText(mDataset.get(position).id);
        holder.tv5.setText(mDataset.get(position).company);
        final Context mycontext = holder.itemView.getContext();

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mycontext, SecondActivity.class);
                int fromBookMarkCheck = 0;
                intent.putExtra("bookName", mDataset.get(position).title);
                intent.putExtra("Author", mDataset.get(position).author);
                intent.putExtra("bookId", mDataset.get(position).id);
                intent.putExtra("Reservation", mDataset.get(position).reservation);
                intent.putExtra("Company", mDataset.get(position).company);
                intent.putExtra("fromBookMarkCheck", fromBookMarkCheck);
                mycontext.startActivity(intent);
            }
        });
    }
    public  int getItemCount(){
        return mDataset.size();
    }
}
