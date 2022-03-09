package com.example.monconstat;

import static com.example.monconstat.R.layout.activity_final;

import static java.lang.System.runFinalizersOnExit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class Final extends AppCompatActivity {
    SharedPreferences cret;
    ImageView im1;
    ImageView im2;
    ImageView im3;
    VideoView vd1;
    VideoView vd2;
    VideoView vd3;
    VideoView vd4;
    TextView next,back;
Button quit,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_final);
        im1=findViewById(R.id.imageView11);
        im2=findViewById(R.id.imageView12);
        im3=findViewById(R.id.imageView13);
        vd1=findViewById(R.id.videoView);
        vd2=findViewById(R.id.videoView2);
        vd3=findViewById(R.id.videoView3);
        vd4=findViewById(R.id.videoView5);

        cret=Final.this.getSharedPreferences("sp_cret",Final.this.MODE_PRIVATE);

        Intent i1= getIntent();
String code= i1.getStringExtra("code");
        String RoE= i1.getStringExtra("RoE");

        next = findViewById(R.id.next500);
        back = findViewById(R.id.back500);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent2 = new Intent(Final.this, etape12_shared.class);
                intent2.putExtra("RoE", RoE);
                intent2.putExtra("code", code);
                startActivity(intent2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Final.this, etape10_shared.class);
                intent2.putExtra("RoE", RoE);
                intent2.putExtra("code", code);
                startActivity(intent2);
            }
        });
        im1.setImageURI(Uri.parse(cret.getString("dataBase[0]",null)));
        im2.setImageURI(Uri.parse(cret.getString("dataBase[1]",null)));
        im3.setImageURI(Uri.parse(cret.getString("dataBase[2]",null)));
        String s1 = cret.getString("dataBase[0]",null);
        String s2 = cret.getString("dataBase[1]",null);
        String s3 = cret.getString("dataBase[2]",null);
        String s4 = cret.getString("pano",null);
        if(s1.contains("jpg")){
            vd1.setVisibility(View.INVISIBLE);
        }
        else{
            vd1.setVideoURI(Uri.parse(cret.getString("dataBase[0]",null)));
            vd1.start();
        }

        if(s2.contains("jpg")){
            vd2.setVisibility(View.INVISIBLE);
        }
        else{
            vd2.setVideoURI(Uri.parse(cret.getString("dataBase[1]",null)));
            vd2.start();
        }

        if(s3.contains("jpg")){
            vd3.setVisibility(View.INVISIBLE);
        }
        else{
            vd3.setVideoURI(Uri.parse(cret.getString("dataBase[2]",null)));
            vd3.start();
        }

        if(s4 == null){
            vd4.setVisibility(View.INVISIBLE);
        }
        else{
            vd4.setVideoURI(Uri.parse(cret.getString("pano",null)));
            vd4.start();

        }

    }


}