package com.example.monconstat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class etape14_shared extends AppCompatActivity {
    TextView myD,yourD,next,back;
    String code,RoE;
    SharedPreferences rvInfo;
    SharedPreferences cret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape14_shared);
        next=findViewById(R.id.next5);
        back=findViewById(R.id.back5);
        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        Intent intentg= getIntent();
        if(intentg.getStringExtra("code") != null)
        {
            code=intentg.getStringExtra("code");
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = rvInfo.edit();
            editor.putString("code",code);
            editor.apply();
        }else code=rvInfo.getString("code",null);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(280); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        next.startAnimation(anim);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirem();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape14_shared.this, etape12_shared.class);
                intent2.putExtra("RoE",RoE);
                intent2.putExtra("code",code);
                startActivity(intent2);
            }
        });

        myD=findViewById(R.id.O1);
        yourD=findViewById(R.id.O2);
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

                        String T1 = obj.getString("observE");
                        myD.setText(T1);

                        String T2 = obj.getString("observR");
                        yourD.setText(T2);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(etape14_shared.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(etape14_shared.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(etape14_shared.this).add(req);
    }
    private void confirem(){

        cret=etape14_shared.this.getSharedPreferences("sp_cret",etape14_shared.this.MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();
        editor.putString("codeoff","6");
        editor.apply();


        editor.putString("off","6");
        editor.apply();


        AlertDialog dlg= new AlertDialog.Builder(this)
                .setTitle("Votre Constat est confirmer  ")
                .setMessage("\n\n-vous pouvez contacter votre courtier pour finaliser la procédure  \n\n-Cliquez Annuler si informations Incorrect \n\n")
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        accept();
                      Intent intent2 = new Intent(etape14_shared.this, Accueil.class);

                       finish();

                    }
                }).setNegativeButton("Annuler",null).create();
        dlg.show();

    }
    private void accept(){
        StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/confr.php?Roe="+code+"&eor="+RoE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(etape14_shared.this, response.toString(), Toast.LENGTH_LONG).show();

                   Intent intent2 = new Intent(etape14_shared.this, Accueil.class);
                    intent2.putExtra("conf","1");
                    intent2.putExtra("code",code);
                    startActivity(intent2);
                    finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(etape14_shared.this, error.toString(), Toast.LENGTH_LONG).show();
            }

        }) ;
        Volley.newRequestQueue(this).add(strq);
    }
}