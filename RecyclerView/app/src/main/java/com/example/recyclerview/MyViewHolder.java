package com.example.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv1, tv2;
    ImageView imageView;
    Button btn;
    public  MyViewHolder(View itemView){
        super(itemView);
        tv1 = (TextView) itemView.findViewById(R.id.item1Name);
        tv2 = (TextView) itemView.findViewById(R.id.item1Money);
        imageView = (ImageView) itemView.findViewById(R.id.imageView1);
        btn = (Button) itemView.findViewById(R.id.item1Button);
    }
}
