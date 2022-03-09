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

public class ShowVehicule extends AppCompatActivity {
    String code,RoE;
    TextView dcin,prefirst3,suvitAs3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_vehicule);
        Intent intentg= getIntent();
        RoE=intentg.getStringExtra("RoE");
        code=intentg.getStringExtra("code");

        suvitAs3= findViewById(R.id.suvitAs12);
        prefirst3=findViewById(R.id.prefirst12);


        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ShowVehicule.this, showAct.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });
        ///
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ShowVehicule.this, showAss.class);
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
                        String nom = obj.getString("marque");
                        dcin=findViewById(R.id.marqueV2);
                        dcin.setText(nom);
                        String prenom = obj.getString("type");
                        dcin=findViewById(R.id.typeV2);
                        dcin.setText(prenom);
                        String adresse = obj.getString("immat");
                        dcin=findViewById(R.id.immatV2);
                        dcin.setText(adresse);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ShowVehicule.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowVehicule.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(ShowVehicule.this).add(req);

    }


}