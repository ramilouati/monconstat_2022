package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import viewinfoR.RV_assP;
import viewinfoR.RV_iconsta;

public class etape9_shared extends AppCompatActivity {
    SharedPreferences rvInfo;
    TextView dcin,suvitAs3,prefirst3;
    String code,RoE;
    CardView top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape9_shared);

        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        top = findViewById(R.id.cardView3);
        top.setMinimumHeight(height/2);
        String loginDirect =rvInfo.getString("marque1",null);
        dcin = findViewById(R.id.myD);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("type1",null);
        dcin = findViewById(R.id.rvbless6);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("immat1",null);
        dcin = findViewById(R.id.rvbless7);
        dcin.setText(loginDirect);

        //user2//

        loginDirect =rvInfo.getString("marque1",null);
        dcin = findViewById(R.id.rvbless1_2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("type1",null);
        dcin = findViewById(R.id.rvbless6_2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("immat1",null);
        dcin = findViewById(R.id.rvbless7_2);
        dcin.setText(loginDirect);

        suvitAs3= findViewById(R.id.next5);
        prefirst3=findViewById(R.id.back5);


        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        Intent intentg = getIntent();
        if (intentg.getStringExtra("code") != null) {
            code = intentg.getStringExtra("code");
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = rvInfo.edit();
            editor.putString("code", code);
            editor.apply();
        } else code = rvInfo.getString("code", null);


        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape9_shared.this, etape10_shared.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape9_shared.this, etape8_shared.class);
                startActivity(intent2);
            }
        });
    }
}


