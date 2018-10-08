package com.softices.trainee.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.softices.traineeapp.R;


import org.json.JSONArray;
import org.json.JSONObject;

public class GetWebSerivcesActivity extends AppCompatActivity {

    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_serivces);

        getAPImethod();
    }

    private void getAPImethod() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://reqres.in/api/users?page=2";

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.e("Response", response.toString());

                        parseJSONdata(response);

                        Toast.makeText(GetWebSerivcesActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }
        );
        queue.add(getRequest);
    }

    private void parseJSONdata(JSONObject response) {
        try {
            int page = response.getInt("page");
            String totlpage = response.getString("per_page");
            String toatl = response.getString("total");
            String toatlPages = response.getString("total_pages");

            JSONArray jsonArrayData = response.getJSONArray("data");
            for (int i = 0; i <= jsonArrayData.length(); i++) {
                JSONObject jsonObject=jsonArrayData.getJSONObject(i);
                String  id =jsonObject.getString("id");
            }
        } catch (Exception e) {
            Log.e("parseJSONdata", e.toString());
        }
    }



}
