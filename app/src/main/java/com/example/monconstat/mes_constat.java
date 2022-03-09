package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import recycleviewc.Listitem;
import recycleviewc.myAdapter;
import viewinfoR.RV_iconsta;

public class mes_constat extends AppCompatActivity {
    ImageView btnbackm3;
    SharedPreferences sp;
    ProgressBar progressBar4;
    private RecyclerView RCV;
    private List<Listitem> listitems;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_constat);

        sp=getSharedPreferences("sp_id",MODE_PRIVATE);
        String id =sp.getString("id",null);
        progressBar4=findViewById(R.id.progressBar4);
        btnbackm3=findViewById(R.id.btnbackm3);
        RCV=findViewById(R.id.RCV);
        RCV.setHasFixedSize(true);
        RCV.setLayoutManager(new LinearLayoutManager(this));
        listitems= new ArrayList<>();


        String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?info="+id;
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    progressBar4.setVisibility(progressBar4.INVISIBLE);
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = juser.length()-1; i>0 ; i--) {
                        obj = juser.getJSONObject(i);
                        String cin1,cin2,datt,code,sts;
                        cin1=cin2=datt=code=" ";
                        cin1 = obj.getString("codeE");
                        cin2 = obj.getString("codeR");
                        datt = obj.getString("date_constat");
                        code = obj.getString("id_constat");
                        sts  = obj.getString("statut");
                        Listitem listitem= new Listitem(""+datt,""+cin1,""+cin2,""+code,""+sts);
                        listitems.add(listitem);
                        adapter= new myAdapter(mes_constat.this,listitems);
                        RCV.setAdapter(adapter);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mes_constat.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mes_constat.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(mes_constat.this).add(req);



        btnbackm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mes_constat.this, Accueil.class);
                startActivity(intent);
            }
        });
    }

}