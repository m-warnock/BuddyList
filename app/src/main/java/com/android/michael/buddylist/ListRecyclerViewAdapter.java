package com.android.michael.buddylist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<ListData> my_data;

    public ListRecyclerViewAdapter(Context context, List<ListData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(my_data.get(position).getListTitle());
        holder.dateCreated.setText(my_data.get(position).getCreationDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListData list = GroupActivity.userInfo.getListDataList().get(position);
                GroupActivity.userInfo.setCurrentListID(list.getListID());
                Intent intent = new Intent(context, ListContentsActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView title;
        public TextView dateCreated;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.listTitle);
            dateCreated = (TextView) itemView.findViewById(R.id.listCreationDate);
        }
    }
}