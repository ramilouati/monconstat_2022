package com.example.monconstat;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.Settings;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import creation.cre_const;
import viewinfoR.RV_assP;
import viewinfoR.Rv_infoP;

import static android.content.ContentValues.TAG;


public class Accueil extends AppCompatActivity {
    Button btnC,btnM,btnP;
    ImageView logout;
    TextView test;
    SharedPreferences sp;
     String deb ;
     String fin;
     Boolean findate=false;
    SharedPreferences cret;
    public String e = "e";//nassim

    public boolean isInternetAvailable()
    {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        sp=getSharedPreferences("sp_id",MODE_PRIVATE);
        cret=Accueil.this.getSharedPreferences("sp_cret",Accueil.this.MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();

        String  bt=cret.getString("codeoff","6");
        String  bt1=cret.getString("off","6");
        test=findViewById(R.id.textView141);

         test.setText("code: "+bt+" "+"stat: "+bt1);



if (bt1.equals("1")){


        String url="https://monconstat.tech/ConstatMobile/r_ou_e.php?mdp="+bt;
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {

                    if( (response.contains("1"))) {
                        e = "r";
                        Toast.makeText(Accueil.this,e,Toast.LENGTH_LONG).show();

                   }
                  else{
                    e = "e";

                    Toast.makeText(Accueil.this,e,Toast.LENGTH_LONG).show();
                   }

                    } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Accueil.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Accueil.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(Accueil.this).add(req);


}



        if(!isInternetAvailable() && bt1.equals("1")){
    StringRequest strq = new StringRequest(Request.Method.GET, "https://www.monconstat.tech/ConstatMobile/upload100.php?rec="+bt, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
                Intent intent2 = new Intent(Accueil.this, uploadConsta.class);
                intent2.putExtra("RoE", e);
                intent2.putExtra("code", bt);


                startActivity(intent2);

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(Accueil.this, error.toString(), Toast.LENGTH_LONG).show();
        }

    });
    Volley.newRequestQueue(Accueil.this).add(strq);
    //  requestQueue.add(strq);




}


       // Toast.makeText(Accueil.this,bt, Toast.LENGTH_LONG).show();


        // new File("/SDCARD/Pictures1000").mkdirs();
       File mydir = new File(Environment.getExternalStorageDirectory() + "SDCARD/Picture/");
        if(!mydir.exists())
            mydir.mkdirs();
        else
            Log.d("error", "dir. already exists");

        //Files.createDirectories(Paths.get("/sdcard/Pictures1000"));

        String premierfoix=(String)sp.getString("premierfoix",null);
         datelimite();
        Intent intentg= getIntent();
         if(intentg.getStringExtra("conf") != null){
         String code=intentg.getStringExtra("code");
         String conf=intentg.getStringExtra("conf");
             Toast.makeText(this, conf, Toast.LENGTH_SHORT).show();


         }


      /*if(premierfoix == "1")
        {
            Intent intent =new Intent(Accueil.this, MainActivity2.class);
            startActivity(intent);
        }*/
       datelimite();


        btnC=findViewById(R.id.btnc);
        btnP=findViewById(R.id.btnp);
        btnM=findViewById(R.id.btnm);
        logout=findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                finish();
                Intent intent2 = new Intent(Accueil.this, MainActivity.class);
                startActivity(intent2);

            }
        });
       datelimite();

            btnC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Intent intent =new Intent(Accueil.this,etape1_shared.class);

                   Intent intent = new Intent(Accueil.this, page1createdc.class);
                    startActivity(intent);
                }
            });

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Accueil.this,profil.class);

                startActivity(intent);
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Accueil.this,mes_constat.class);

                startActivity(intent);
            }
        });
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            Log.i(TAG, "sdk < 28 Q");
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] strings =
                        {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                ActivityCompat.requestPermissions(this, strings, 1);
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    "android.permission.ACCESS_BACKGROUND_LOCATION") != PackageManager.PERMISSION_GRANTED) {
                String[] strings = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        "android.permission.ACCESS_BACKGROUND_LOCATION"};
                ActivityCompat.requestPermissions(this, strings, 2);
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        String provider = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){ //if gps is disabled
            Intent inte =new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(inte);
        }


    }

    private void datelimite() {
        String id = sp.getString("id", null);
        String url = "https://www.monconstat.tech/ConstatMobile/Accueil.php?id="+id;
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                float res;
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                         fin = obj.getString("fin");
                    }
                       // Toast.makeText(Accueil.this,fin.toString(), Toast.LENGTH_LONG).show();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
                    String s=((fin.substring(5,7))+"/"+fin.substring(8,10))+"/"+(fin.substring(0,4));
                    //Toast.makeText(Accueil.this,s, Toast.LENGTH_LONG).show();
                        Date dateAvant = sdf.parse(s);
                        Date dateApres = sdf.parse(sdf.format(new Date()));
                        long diff = dateApres.getTime() - dateAvant.getTime();
                         res = (diff / (1000*60*60*24));
                         if(res<0)
                         {
                          Toast.makeText(Accueil.this, String.valueOf(Math.round(res)), Toast.LENGTH_SHORT).show();
                          findate=false;

                             btnC.setBackgroundColor((Color.parseColor("#C2261F")));

                         }
                         else if ((res<31)&&(res>0)){
                             Toast.makeText(Accueil.this, "il est en "+String.valueOf(Math.round(res))+" jour", Toast.LENGTH_SHORT).show();
                             findate=true;
                         }else
                             findate=true;

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Accueil.this, e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Accueil.this, error.toString(), Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(Accueil.this).add(req);
    }

    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
}