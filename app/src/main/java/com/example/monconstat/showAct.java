package com.example.monconstat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class showAct extends AppCompatActivity {
    String code,RoE;
    TextView dcin,conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intentg= getIntent();
        RoE=intentg.getStringExtra("RoE");
        code=intentg.getStringExtra("code");
        conf=findViewById(R.id.conf);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirem();
            }
        });

        onshow();
    }



    public void onshow(){
        String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&eor="+RoE+"&info=i";
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        if(RoE.equals("r")) {
                            String id_contrat = obj.getString("BlessesE");
                            dcin = findViewById(R.id.blu);
                            dcin.setText(id_contrat);
                            id_contrat = obj.getString("DegatsE");
                            dcin = findViewById(R.id.dmu);
                            dcin.setText(id_contrat);
                            String debut = obj.getString("temoinsE");
                            dcin = findViewById(R.id.temu);
                            dcin.setText(debut);
                            String geoE = obj.getString("geoE");
                            dcin = findViewById(R.id.local);
                            dcin.setText(geoE);
                            String getdateE = obj.getString("getdateE");
                            dcin = findViewById(R.id.gettime);
                            dcin.setText(getdateE);
                            String observE = obj.getString("observE");
                            dcin = findViewById(R.id.obser);
                            dcin.setText(observE);
                        }
                        else
                        {
                            String id_contrat = obj.getString("BlessesR");
                            dcin = findViewById(R.id.blu);
                            dcin.setText(id_contrat);
                            id_contrat = obj.getString("DegatsR");
                            dcin = findViewById(R.id.dmu);
                            dcin.setText(id_contrat);
                            String debut = obj.getString("temoinsR");
                            dcin = findViewById(R.id.temu);
                            dcin.setText(debut);

                            String geoE = obj.getString("geoR");
                            dcin = findViewById(R.id.local);
                            dcin.setText(geoE);
                            String getdateE = obj.getString("getdateR");
                            dcin = findViewById(R.id.gettime);
                            dcin.setText(getdateE);
                            String observE = obj.getString("observR");
                            dcin = findViewById(R.id.obser);
                            dcin.setText(observE);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(showAct.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(showAct.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(showAct.this).add(req);
    }

   private void confirem(){
           AlertDialog dlg= new AlertDialog.Builder(this)
                   .setTitle("Etes-vous sûr que vos informations sont correctes ")
                   .setMessage("\n\n-Cliquez sur Confirmer pour continuer si vos informations sont correctes \n\n-Cliquez Annuler si informations Incorrect \n\n")
                   .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int i) {
                           dialog.dismiss();
                            accept();

                       }
                   }).setNegativeButton("Annuler",null).create();
           dlg.show();

   }

   private void accept(){
       StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/confr.php?Roe="+code+"&eor="+RoE, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               if(response.contains("1")){
                   Intent intent2 = new Intent(showAct.this, Accueil.class);
                   intent2.putExtra("conf","1");
                   intent2.putExtra("code",code);
                   startActivity(intent2);
                   finish();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(showAct.this, error.toString(), Toast.LENGTH_LONG).show();
           }

       }) ;
       Volley.newRequestQueue(this).add(strq);
   }
   }
