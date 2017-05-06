package com.android.michael.buddylist;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 5/5/2017.
 */

public class BackgroundTask {
    Context context;
    ArrayList<GroupData> arrayList = new ArrayList<>();
    String URL = "http://buddylist.000webhostapp.com/retrieve_group_data.php";


    public BackgroundTask(Context context){
        this.context = context;
    }

    public ArrayList<GroupData> getList(final String id){
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try{
                                JSONObject jsonObject = response.getJSONObject(i);
                                GroupData groupData = new GroupData(
                                        jsonObject.getString("GroupID"),
                                        jsonObject.getString("GroupName"),
                                        jsonObject.getString("GroupDescription"),
                                        jsonObject.getString("GroupLeader"),
                                        jsonObject.getString("CreationDate"));
                                arrayList.add(groupData);

                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("Id", id);

                return hashMap;
            }
        };

        MySingleton.getsInstance(context).addToRequestQueue(jsonArrayRequest);
        return arrayList;
    }
}
