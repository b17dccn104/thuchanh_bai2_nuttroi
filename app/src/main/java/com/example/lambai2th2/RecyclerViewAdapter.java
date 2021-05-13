package com.example.lambai2th2;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.StudentViewHolder>{
    private List<Order> list;
    private Activity activity;

    public RecyclerViewAdapter(Activity activity) {
        list=new ArrayList<>();
        this.activity = activity;
    }

    public void setStudents(List<Order> list){
        this.list=list;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.cell_order,parent,false);
        return new StudentViewHolder(v);
    }

    public void onBindViewHolder(@NonNull StudentViewHolder holder,int position) {
        Order s = list.get(position);
        holder.tvIdName.setText(s.getName());
        holder.tvDate.setText(" Date:"+s.getDate());
        holder.tvAmount.setText("Amount:"+s.getAmount());
        holder.tvPrice.setText("Price:"+s.getPrice());

        Activity activity = this.activity;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UpdateRemoveActivity.class);
                intent.putExtra("id", s.getId());
                activity.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIdName;
        private TextView tvDate;
        private TextView tvAmount;
        private TextView tvPrice;

        public StudentViewHolder(@NonNull View v) {
            super(v);
            tvIdName=v.findViewById(R.id.idName);
            tvDate = v.findViewById(R.id.txtDate);
            tvAmount=v.findViewById(R.id.txtAmount);
            tvPrice=v.findViewById(R.id.txtPrice);
        }
    }
}
