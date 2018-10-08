package com.softices.trainee.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.softices.trainee.adapter.ProgramingAdapter;
import com.softices.traineeapp.R;

public class UserActivity extends AppCompatActivity {
    RecyclerView RecyclerView;
    ProgramingAdapter programinglist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RecyclerView programinglist = (RecyclerView) findViewById(R.id.programinglist);
        programinglist.setLayoutManager(new LinearLayoutManager(this));
        String[] languages = {"java","javascript","php","c","c++","python"};
        programinglist.setAdapter(new ProgramingAdapter(languages));


    }
}
