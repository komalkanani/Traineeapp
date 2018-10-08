package com.softices.trainee.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softices.traineeapp.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    //button objects
    private Button buttonStart;
    private Button buttonStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //getting buttons from xml
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        //attaching onclicklistener to buttons
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                startService(new Intent(getApplicationContext(), MyService.class));
                break;
            case R.id.buttonStop:
                stopService(new Intent(getApplicationContext(), MyService.class));
                break;
        }
    }
}