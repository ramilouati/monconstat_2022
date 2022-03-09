package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class cre_const extends AppCompatActivity {
    TextView getdate,dcin,temoins;
    ImageView btnbackm2,imgplus;
    EditText Temoins;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cre_const);
        btnbackm2=findViewById(R.id.btnbackm2);
        sp=getSharedPreferences("sp_id",MODE_PRIVATE);
        String id=sp.getString("id",null);
        btnbackm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(cre_const.this,Accueil.class);
                startActivity(intent);
            }
        });




        //----------
      /*  String url="https://achref12.000webhostapp.com/constat/login/contra.php?id="+id;

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        String id_contrat = obj.getString("id_contrat");
                        dcin = findViewById(R.id.PA);
                        dcin.setText(id_contrat);

                        id_contrat = obj.getString("assurance");
                        dcin = findViewById(R.id.VA);
                        dcin.setText(id_contrat);

                        String nom = obj.getString("immat");

                        String debut = obj.getString("debut");
                        dcin = findViewById(R.id.dated);
                        dcin.setText(debut);
                        String fin = obj.getString("fin");
                        dcin = findViewById(R.id.datef);
                        dcin.setText(fin);

                        String npermis = obj.getString("id_agence");



                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(cre_const.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(cre_const.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(cre_const.this).add(req);

        //----------
         url="https://achref12.000webhostapp.com/constat/login/profil.php?id="+id;

         req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        String nom = obj.getString("nom");
                        dcin = findViewById(R.id.nomp);
                        dcin.setText(nom);
                        String prenom = obj.getString("prenom");
                        dcin = findViewById(R.id.prenomp);
                        dcin.setText(prenom);
                        String adresse = obj.getString("adresse");
                        dcin = findViewById(R.id.adresse);
                        dcin.setText(adresse);
                        String npermis = obj.getString("npermis");
                        dcin = findViewById(R.id.Npermi);
                        dcin.setText(npermis);
                        String delivre = obj.getString("delivre");
                        dcin = findViewById(R.id.delivre);
                        dcin.setText(delivre);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(cre_const.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(cre_const.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(cre_const.this).add(req);
/// contra -----------------*/

    }
}