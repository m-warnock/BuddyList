package com.android.michael.buddylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {

    UserData userInfo;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<GroupData> groupDataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Bundle b = this.getIntent().getExtras();
        if (b != null)
            userInfo = b.getParcelable("userData");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        BackgroundTask backgroundTask = new BackgroundTask(GroupActivity.this);
        groupDataArrayList = backgroundTask.getList(userInfo.getId());
        adapter = new RecyclerAdapter(groupDataArrayList);
        recyclerView.setAdapter(adapter);


    }
}
