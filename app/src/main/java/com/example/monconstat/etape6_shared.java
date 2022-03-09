package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
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

import viewinfoR.Rv_infoP;

public class etape6_shared extends AppCompatActivity {
    TextView V1,police1,agence1,deb1,end1,V2,police2,agence2,deb2,end2,next,back;
    String code,RoE;
    SharedPreferences rvInfo;
    CardView top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape6_shared);
        rvInfo = getSharedPreferences("rvInfo", MODE_PRIVATE);
        next = findViewById(R.id.next5);
        back = findViewById(R.id.back5);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        top = findViewById(R.id.cardView3);
        top.setMinimumHeight(height/2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape6_shared.this, etape8_shared.class);
                intent2.putExtra("RoE", RoE);
                intent2.putExtra("code", code);
                startActivity(intent2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape6_shared.this, etape5_shared.class);
                intent2.putExtra("RoE", RoE);
                intent2.putExtra("code", code);
                startActivity(intent2);
            }
        });

        Intent intentg = getIntent();
        if (intentg.getStringExtra("code") != null) {
            code = intentg.getStringExtra("code");
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = rvInfo.edit();
            editor.putString("code", code);
            editor.apply();
        } else code = rvInfo.getString("code", null);
        V1 = findViewById(R.id.v1);
        police1 = findViewById(R.id.police1);
        agence1 = findViewById(R.id.agence1);
        deb1 = findViewById(R.id.deb1);
        end1 = findViewById(R.id.end1);
        V2 = findViewById(R.id.v2);
        police2 = findViewById(R.id.police2);
        agence2 = findViewById(R.id.agence2);
        deb2 = findViewById(R.id.deb2);
        end2 = findViewById(R.id.end2);
        onshow();
    }
        public void onshow(){
            String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&eor=r";
            StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray juser = new JSONArray(response);
                        JSONObject obj;
                        for (int i = 0; i < juser.length(); i++) {
                            obj = juser.getJSONObject(i);

                            obj = juser.getJSONObject(i);
                            String id_contrat = obj.getString("assurance");
                            V1.setText(id_contrat);
                            id_contrat = obj.getString("id_contrat");
                            police1.setText(id_contrat);
                            //   String nom = obj.getString("immat");
                            String debut = obj.getString("debut");
                            deb1.setText(debut);
                            String fin = obj.getString("fin");
                            end1.setText(fin);
                            String id_agence = obj.getString("id_agence");
                            agence1.setText(id_agence);


                            /////// vehicule
                            SharedPreferences.Editor editor = rvInfo.edit();


                            String marque = obj.getString("marque");
                            editor.putString("marque1",marque);

                            String type = obj.getString("type");
                            editor.putString("type1",type);

                            String immat = obj.getString("immat");
                            editor.putString("immat1",immat);

                            //////
                            String nom = obj.getString("nom");
                            editor.putString("nom1",nom);

                            String prenom = obj.getString("prenom");
                            editor.putString("prenom1",prenom);

                            String adresse = obj.getString("adresse");
                            editor.putString("adresse1",adresse);

                            String npermis = obj.getString("npermis");
                            editor.putString("npermis1",npermis);

                            String delivre = obj.getString("delivre");
                            editor.putString("delivre1",delivre);
                            editor.apply();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(etape6_shared.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                    onshow2();
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(etape6_shared.this,error.toString(),Toast.LENGTH_LONG).show();
                }

            });
            Volley.newRequestQueue(etape6_shared.this).add(req);
        }




        public void onshow2(){
            String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&eor=e";
            StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray juser = new JSONArray(response);
                        JSONObject obj;
                        for (int i = 0; i < juser.length(); i++) {
                            obj = juser.getJSONObject(i);

                            obj = juser.getJSONObject(i);
                            String id_contrat = obj.getString("assurance");
                            V2.setText(id_contrat);
                            id_contrat = obj.getString("id_contrat");
                            police2.setText(id_contrat);
                            //   String nom = obj.getString("immat");
                            String debut = obj.getString("debut");
                            deb2.setText(debut);
                            String fin = obj.getString("fin");
                            end2.setText(fin);
                            String id_agence = obj.getString("id_agence");
                            agence2.setText(id_agence);


                            /////// vehicule
                            SharedPreferences.Editor editor = rvInfo.edit();


                            String marque = obj.getString("marque");
                            editor.putString("marque2",marque);

                            String type = obj.getString("type");
                            editor.putString("type2",type);

                            String immat = obj.getString("immat");
                            editor.putString("immat2",immat);

                            //////
                            String nom = obj.getString("nom");
                            editor.putString("nom2",nom);

                            String prenom = obj.getString("prenom");
                            editor.putString("prenom2",prenom);

                            String adresse = obj.getString("adresse");
                            editor.putString("adresse2",adresse);

                            String npermis = obj.getString("npermis");
                            editor.putString("npermis2",npermis);

                            String delivre = obj.getString("delivre");
                            editor.putString("delivre2",delivre);

                            String tel = obj.getString("tel");
                            editor.putString("tel",tel);

                            editor.apply();


                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(etape6_shared.this,e.toString(),Toast.LENGTH_LONG).show();
                    }

                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(etape6_shared.this,error.toString(),Toast.LENGTH_LONG).show();
                }

            });
            Volley.newRequestQueue(etape6_shared.this).add(req);
        }

    }