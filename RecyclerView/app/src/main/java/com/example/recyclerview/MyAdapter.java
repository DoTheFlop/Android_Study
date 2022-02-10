package com.example.recyclerview;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<PaintTitle> mDataset;
    public  MyAdapter(ArrayList<PaintTitle> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item1, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return  vh;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataset.get(position % 3).imageId);
        holder.tv1.setText("제품명:  " + mDataset.get(position % 3).title);
        holder.tv2.setText("가격:  " + mDataset.get(position % 3).money);
        final int newpos = position % 3;
        final Context mycontext = holder.itemView.getContext();
        holder.btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mycontext, SecondActivity.class);
                intent.putExtra("pic", mDataset.get(position % 3).imageId);
                intent.putExtra("name", mDataset.get(position % 3).title);
                intent.putExtra("money", mDataset.get(position % 3).money);
                if(mDataset.get(position % 3).title == "갤럭시S21")
                    intent.putExtra("detail", "화면의 크기는 6.2인치 이다.");
                else if(mDataset.get(position % 3).title == "갤럭시S21+")
                    intent.putExtra("detail", "화면의 크기는 6.7인치 이다.");
                else if(mDataset.get(position % 3).title == "갤럭시S21Ultra")
                    intent.putExtra("detail", "화면의 크기는 6.8인치 이다.");
                mycontext.startActivity(intent);
            }
        });
    }
    public  int getItemCount(){
        return 3;
    }
}
