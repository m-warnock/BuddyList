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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etConfirmPassword, etFirstName, etLastName;
    private Button buttonSubmit;
    private RequestQueue requestQueue;
    //Laptop WAMP
    //private static final String URL = "http://10.205.0.219:80/BuddyList/register.php";
    //Desktop WAMP
    private static final String URL = "http://192.168.1.82:80/PHP/BuddyList/user_registration.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etNewEmail);
        etPassword = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etFirstName = (EditText) findViewById(R.id.etFName);
        etLastName = (EditText) findViewById(R.id.etLName);

        requestQueue = Volley.newRequestQueue(this);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean validEmail = false;
                boolean validPassword = false;
                boolean validFName = false;
                boolean validLName = false;
                //String email = etEmail.getText().toString().toLowerCase();
                //check to see if input is a valid email address
                Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

                Matcher emailMatcher = pattern.matcher(etEmail.getText().toString().toLowerCase());
                if (emailMatcher.matches()) {
                    validEmail = true;

                    //check if passwords match and length requirement and 1 digit requirement
                    if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                        if (etPassword.getText().length() > 7 && etPassword.getText().length() < 33) {
                            if (etPassword.getText().toString().matches(".*\\d+.*")) {
                                if(etPassword.getText().toString().matches(".*[A-Z].*")){
                                    validPassword = true;
                                } else {
                                    Toast.makeText(getApplicationContext(), "Must contain 1 uppercase character", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Password must have at least 1 digit", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must be at least 8-32 characters long", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                }

                if(validEmail && validPassword) {
                    if (etFirstName.getText().length() > 0) {
                        if (etLastName.getText().length() > 0) {
                            validFName = true;
                            validLName = true;
                        } else {
                            Toast.makeText(getApplicationContext(), "You must enter a Last Name", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "You must enter a First Name", Toast.LENGTH_SHORT).show();
                    }
                }

                if(validEmail && validPassword && validFName && validLName){
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                        @Override
                        public void  onResponse(String response){
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.names().get(0).equals("success")){
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("failure"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error){

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError{
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("NewEmail", etEmail.getText().toString().toLowerCase());
                            hashMap.put("NewPassword", etPassword.getText().toString());
                            hashMap.put("FName", etFirstName.getText().toString());
                            hashMap.put("LName", etLastName.getText().toString());

                            return hashMap;
                        }
                    };

                    requestQueue.add(request);
                }

            }
        });
    }
}
