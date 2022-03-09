package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import creation.cre_const;

public class page1createdc extends AppCompatActivity {
    TextView getdate,temoins,FistToGeo;
    SharedPreferences cret;
    CardView cardView9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1createdc);

        cret=getSharedPreferences("sp_cret",MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm");
        String datetime = simpleDateFormat.format(calendar.getTime());
        editor = cret.edit();
        editor.putString("getdate",datetime);
        editor.apply();


         simpleDateFormat = new SimpleDateFormat("HH:mm");
         datetime = simpleDateFormat.format(calendar.getTime());
        getdate= findViewById(R.id.getdate2);
        getdate.setText(datetime);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        datetime = simpleDateFormat.format(calendar.getTime());
        getdate= findViewById(R.id.getdate3);
        getdate.setText(datetime);
        FistToGeo=findViewById(R.id.fistToGeo1);
        cardView9=findViewById(R.id.cardView9);
        FistToGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent2 = new Intent(page1createdc.this, cre_const.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent2);
            }
        });



    }
}