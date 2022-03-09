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

public class ShowInfoRE extends AppCompatActivity {
    String code,RoE;
    TextView dcin,suvitAs3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info_r_e);
        Intent intentg= getIntent();
        RoE=intentg.getStringExtra("RoE");
        code=intentg.getStringExtra("code");
        onshow();
        suvitAs3= findViewById(R.id.suvitAs9);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ShowInfoRE.this, showAss.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });
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
                        String id_contrat = obj.getString("assurance");
                        dcin = findViewById(R.id.VAu);
                        dcin.setText(id_contrat);
                        id_contrat = obj.getString("id_contrat");
                        dcin = findViewById(R.id.PDu);
                        dcin.setText(id_contrat);
                     //   String nom = obj.getString("immat");
                        String debut = obj.getString("debut");
                        dcin =findViewById(R.id.DUu);
                        dcin.setText(debut);
                        String fin = obj.getString("fin");
                        dcin = findViewById(R.id.AUu);
                        dcin.setText(fin);
                        String npermis = obj.getString("id_agence");
                        dcin = findViewById(R.id.AGu);
                        dcin.setText(npermis);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ShowInfoRE.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowInfoRE.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(ShowInfoRE.this).add(req);

    }
}
