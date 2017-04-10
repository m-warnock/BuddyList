package com.android.michael.buddylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.michael.buddylist.R;

import java.util.ArrayList;


public class Tab1Groups extends Fragment {
    FloatingActionButton createGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab1_home_frag, container, false);
        createGroup = (FloatingActionButton) rootView.findViewById(R.id.fabNewGroup);

        ArrayList<String> groups = new ArrayList<String>();
        groups.add("Alpha");
        groups.add("Bravo");
        ListView listView = (ListView) rootView.findViewById(R.id.LVgroupList);
        listView.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, groups));

        //listener for list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(),
                        "Clicked List Item Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

        //listener for fab
        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), CreateGroupActivity.class));
            }
        });
        return rootView;
    }

}
