package com.android.michael.buddylist;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListContentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ListContentsRecyclerViewAdapter adapter;
    private List<String> data_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contents);

        recyclerView = (RecyclerView) findViewById(R.id.listdata_recycler_view);
        data_list  = new ArrayList<>();

        load_data_from_server(GroupActivity.userInfo.getCurrentListID());

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new ListContentsRecyclerViewAdapter(this, data_list);
        recyclerView.setAdapter(adapter);

    }
    //parameter id is group id
    private void load_data_from_server(final int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://buddylist.000webhostapp.com/retrieve_list_contents.php?id="+id)
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        data_list.add(object.getString("ListItem"));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        task.execute();
    }
}
