package com.example.monconstat;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class lodingd {
   private Activity activity;
   private AlertDialog dialog;
    lodingd(Activity myActivity ){
        activity = myActivity;
    }
    void startLodingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater infalter=activity.getLayoutInflater();
        builder.setView(infalter.inflate(R.layout.custom_d,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();

    }


    void dismissDialog() {
       dialog.dismiss();
    }
}
