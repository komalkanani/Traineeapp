package com.softices.trainee.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softices.trainee.database.DatabaseHelper;
import com.softices.traineeapp.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostWebServices extends AppCompatActivity {
    private String response;
    private DatabaseHelper databaseHelper;
    TextView txtpost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_web_services);
        postAPImethod();
        txtpost.setText("name");
    }

    private void postAPImethod() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://reqres.in/api/users";
        StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String name = jsonObject.getString("name");
                        } catch (Exception e) {
                            Log.e("onResponse", e.toString());
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "komal");
                params.put("job", "leader");

                return params;
            }
        };
        queue.add(postRequest);


    }

}


