package com.example.project.ui.home;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv1, tv2, tv3, tv4, tv5;
    Button btn;
    public  MyViewHolder(View itemView){
        super(itemView);
        tv1 = (TextView) itemView.findViewById(R.id.item1Name);
        tv2 = (TextView) itemView.findViewById(R.id.item1Reservation);
        tv3 = (TextView) itemView.findViewById(R.id.item1Author);
        tv4 = (TextView) itemView.findViewById(R.id.item1Id);
        tv5 = (TextView) itemView.findViewById(R.id.item1Company);
        btn = (Button) itemView.findViewById(R.id.detailBtn);
    }

}
