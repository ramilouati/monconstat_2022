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



public class etape2_shared extends AppCompatActivity {
    TextView myLieu,yourLieu,next,back;
    String code,RoE;
    CardView top;
    SharedPreferences rvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape2_shared);
        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        next=findViewById(R.id.next2);
        back=findViewById(R.id.back2);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        top = findViewById(R.id.cardView3);
        top.setMinimumHeight(height/2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape2_shared.this, etape3_shared.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape2_shared.this, etape1_shared.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });


        Intent intentg= getIntent();
        if(intentg.getStringExtra("code") != null)
        {
            code=intentg.getStringExtra("code");
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = rvInfo.edit();
            editor.putString("code",code);
            editor.apply();
        }else code=rvInfo.getString("code",null);
        myLieu=findViewById(R.id.myLieu);
        yourLieu=findViewById(R.id.yourLieu);
        onshow();

    }
    public void onshow(){
        String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&info=i";
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);

                        String getdateE = obj.getString("geoE");
                        //Toast.makeText(etape2_shared.this,getdateE,Toast.LENGTH_SHORT);
                        myLieu.setText(getdateE);

                        String getdateR = obj.getString("geoR");
                        //Toast.makeText(etape2_shared.this,getdateR,Toast.LENGTH_SHORT);
                        yourLieu.setText(getdateR);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(etape2_shared.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(etape2_shared.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(etape2_shared.this).add(req);
    }
}
