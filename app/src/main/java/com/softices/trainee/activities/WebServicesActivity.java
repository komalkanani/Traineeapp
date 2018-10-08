package com.softices.trainee.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softices.traineeapp.R;

public class WebServicesActivity extends AppCompatActivity {
    Button btnget,btnpost,btnput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_services);
        btnget=(Button)findViewById(R.id.btn_get);
        btnpost=(Button)findViewById(R.id.btn_post);
        btnput=(Button)findViewById(R.id.btn_put);
        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WebServicesActivity.this,GetWebSerivcesActivity.class);
                startActivity(i);
            }
        });
        btnput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WebServicesActivity.this,PutWebServices.class);
                startActivity(i);
            }
        });
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WebServicesActivity.this,PostWebServices.class);
                startActivity(i);
            }
        });
    }
}
