package com.softices.trainee.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

public class PutWebServices extends AppCompatActivity {
    private String response;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_web_services);
        putAPImethod();

    }

    private void putAPImethod() {
        RequestQueue queue = Volley.newRequestQueue(this);


        String url = "https://reqres.in/api/users/2";
        StringRequest putRequest = new StringRequest(
                Request.Method.PUT,
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


                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        String response = null;
                        Log.d("Error.Response", response);
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return params;
            }
        };
        queue.add(putRequest);
    }
}



