package com.android.michael.buddylist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class GroupRecyclerViewAdapter extends RecyclerView.Adapter<GroupRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<GroupData> my_data;

    public GroupRecyclerViewAdapter(Context context, List<GroupData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_row_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(my_data.get(position).getGroupName());
        holder.description.setText(my_data.get(position).getGroupDescription());
        holder.leader.setText(my_data.get(position).getGroupLeader());
        holder.dateCreated.setText(my_data.get(position).getCreationDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupData group = GroupActivity.userInfo.getGroupDataList().get(position);
                GroupActivity.userInfo.setCurrentGroupID(group.getGroupID());
                Intent intent = new Intent(context, ListActivity.class);
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
        public TextView description;
        public TextView leader;
        public TextView dateCreated;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.groupTitle);
            description = (TextView) itemView.findViewById(R.id.groupDescription);
            leader = (TextView) itemView.findViewById(R.id.groupLeader);
            dateCreated = (TextView) itemView.findViewById(R.id.creationDate);
        }
    }
}