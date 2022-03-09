package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;

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

public class showAss extends AppCompatActivity {
    String code,RoE;
    TextView dcin,prefirst3,suvitAs3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ass);

        Intent intentg= getIntent();
        RoE=intentg.getStringExtra("RoE");
        code=intentg.getStringExtra("code");

        suvitAs3=findViewById(R.id.suvitAs11);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(showAss.this, ShowVehicule.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });
///

        prefirst3=findViewById(R.id.prefirst10);
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(showAss.this, ShowInfoRE.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });
        onshow();
    }

    public void onshow(){
        String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&eor="+RoE;
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        String nom = obj.getString("nom");
                        dcin=findViewById(R.id.nompu);
                        dcin.setText(nom);
                        String prenom = obj.getString("prenom");
                        dcin=findViewById(R.id.prenompu);
                        dcin.setText(prenom);
                        String adresse = obj.getString("adresse");
                        dcin=findViewById(R.id.adressepu);
                        dcin.setText(adresse);
                        String npermis = obj.getString("npermis");
                        dcin=findViewById(R.id.PCu);
                        dcin.setText(npermis);
                        String delivre = obj.getString("delivre");
                        dcin=findViewById(R.id.delu);
                        dcin.setText(delivre);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(showAss.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(showAss.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(showAss.this).add(req);

    }
}