package com.android.michael.buddylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button buttonSignIn;
    private Button buttonSignUp;
    private RequestQueue requestQueue;
    //Home Server
    private static final String URL = "http://192.168.1.82:80/PHP/BuddyList/user_login.php";
    //Laptop Server
    //private static final String URL = "http://10.61.0.239:80/BuddyList/user_login.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        requestQueue = Volley.newRequestQueue(this);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){

                                //parse Json associative array into its parts and place them in the user info object
                                String id = jsonObject.getJSONObject("success").getString("id");
                                String email = jsonObject.getJSONObject("success").getString("email");
                                String firstName = jsonObject.getJSONObject("success").getString("fname");
                                String lastName = jsonObject.getJSONObject("success").getString("lname");
                                UserData userInfo = new UserData(id, email, firstName, lastName);

                                //Pass userInfo to HomeActivity so user doesn't have to keep giving account info
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("userData", userInfo);
                                intent.putExtras(bundle);
                                startActivity(intent);

                                Toast.makeText(LoginActivity.this, "Welcome " + userInfo.getFirstName() + " " + userInfo.getLastName(), Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(getApplicationContext(), jsonObject.getString("failure"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                }){
                    /*The server side code requires that the POSTed parameter names be 'Email' and 'Password'
                    The hash map connects the information input by the user in the username and email fields with respective parameter.*/
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("Email", etEmail.getText().toString().toLowerCase());
                        hashMap.put("Password", etPassword.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }




}
