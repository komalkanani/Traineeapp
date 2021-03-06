package com.softices.trainee;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.softices.trainee.database.DatabaseHelper;

public class YouApplication extends Application {

    public static DatabaseHelper databaseHelper;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        databaseHelper = new DatabaseHelper(this);

    }

}