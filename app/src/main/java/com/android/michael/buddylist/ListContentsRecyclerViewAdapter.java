package com.android.michael.buddylist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ListContentsRecyclerViewAdapter extends RecyclerView.Adapter<ListContentsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<String> my_data;

    public ListContentsRecyclerViewAdapter(Context context, List<String> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_datapoint,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.item.setText(my_data.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView item;

        public ViewHolder(View itemView) {
            super(itemView);

            item = (TextView) itemView.findViewById(R.id.list_datapoint);
        }
    }
}