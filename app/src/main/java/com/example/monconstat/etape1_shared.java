package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

public class etape1_shared extends AppCompatActivity {
    TextView mydate,yourdate,next;
    String code,RoE;
    SharedPreferences rvInfo;
    ConstraintLayout top ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape1_shared);
        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        top = findViewById(R.id.constraintLayout2);
        top.setMaxHeight(height/2);
        next=findViewById(R.id.next5);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape1_shared.this, etape2_shared.class);
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

        mydate=findViewById(R.id.myD);
        yourdate=findViewById(R.id.yourD);

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



                        String getdateE = obj.getString("getdateE");
                        Toast.makeText(etape1_shared.this,getdateE,Toast.LENGTH_SHORT);
                        mydate.setText(getdateE);

                        String getdateR = obj.getString("getdateR");
                        Toast.makeText(etape1_shared.this,getdateR,Toast.LENGTH_SHORT);
                        yourdate.setText(getdateR);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(etape1_shared.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(etape1_shared.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(etape1_shared.this).add(req);
    }
}
