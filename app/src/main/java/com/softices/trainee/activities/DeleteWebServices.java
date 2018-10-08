package com.softices.trainee.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softices.trainee.database.DatabaseHelper;
import com.softices.traineeapp.R;

public class DeleteWebServices extends AppCompatActivity {
    private String response;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_web_services);
        deletAPImethod();
    }

    private void deletAPImethod() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://reqres.in/api/users/2"; 
        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(DeleteWebServices.this, response, Toast.LENGTH_SHORT).show();
                        }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.

                    }
                }
        );
        queue.add(dr);
    }
}

