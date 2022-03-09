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

import viewinfoR.RV_VehiculeP;
import viewinfoR.Rv_infoP;

public class etape8_shared extends AppCompatActivity {
    SharedPreferences rvInfo;
    TextView dcin,suvitAs3,btnback;
    String code,RoE;
    CardView top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape8_shared);
        btnback=findViewById(R.id.back5);
        suvitAs3= findViewById(R.id.next5);


        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        top = findViewById(R.id.cardView3);
        top.setMinimumHeight(height/2);

        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape8_shared.this, etape9_shared.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(etape8_shared.this, etape6_shared.class);
                startActivity(intent);
            }
        });

        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        Intent intentg = getIntent();
        if (intentg.getStringExtra("code") != null) {
            code = intentg.getStringExtra("code");
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = rvInfo.edit();
            editor.putString("code", code);
            editor.apply();
        } else code = rvInfo.getString("code", null);



        String loginDirect =rvInfo.getString("nom1",null);
        dcin = findViewById(R.id.myD);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("prenom1",null);
        dcin = findViewById(R.id.rvbless6);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("adresse1",null);
        dcin = findViewById(R.id.rvbless7);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("tel",null);
        dcin = findViewById(R.id.rvbless9);
        dcin.setText(loginDirect);




        //user//
        loginDirect =rvInfo.getString("nom2",null);
        dcin = findViewById(R.id.rvbless1_2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("prenom2",null);
        dcin = findViewById(R.id.rvbless6_2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("adresse2",null);
        dcin = findViewById(R.id.rvbless7_2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("tel",null);
        dcin = findViewById(R.id.rvbless9_2);
        dcin.setText(loginDirect);


    }
}



