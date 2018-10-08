package com.softices.trainee.methods;

import android.app.ProgressDialog;
import android.content.Context;

import com.softices.trainee.activities.SplashActivity;

public class L {
    public static ProgressDialog progress;
    
    public static void showprogressDialog(Context context){
        if (progress == null){
            progress= new ProgressDialog (context);
            progress.setMessage("Loading..");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
            
        }
    }
    public static void hideProgressDialog(){
        if (progress.isShowing() && progress != null){
            progress.dismiss();
            progress = null;
        }
    }
}