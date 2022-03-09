package com.example.monconstat;

import static com.example.monconstat.FilePath.getPath;
import static java.lang.String.valueOf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import creation.testi;

public class Recevoir extends AppCompatActivity {
    TextView codeR;
    Button gencode,pret;
    int enter;
    SharedPreferences cret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recevoir);
        codeR=findViewById(R.id.codeR);
        gencode=findViewById(R.id.GenCode);
pret= findViewById(R.id.button9);
        gencode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeR.setVisibility(View.VISIBLE);

                gcode();

                gencode.setVisibility(View.INVISIBLE);


            }


        });
        pret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent5 = new Intent(Recevoir.this, Accueil.class);



                startActivity(intent5);
            }


        });
    }

    public void find() {
        Toast.makeText(Recevoir.this, codeR.getText().toString(), Toast.LENGTH_LONG).show();

        StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/connect.php?rec="
                +codeR.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if( (response.contains("11"))){
                    enter=2;
                    Intent intent2 = new Intent(Recevoir.this, uploadConsta.class);
                    String e="r";
                    intent2.putExtra("RoE",e);
                    intent2.putExtra("code",codeR.getText().toString());
                    startActivity(intent2);
                   finish();
                }else find();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Recevoir.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mdp",codeR.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(Recevoir.this).add(strq);

    }
    public boolean isInternetAvailable()
    {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public void gcode()
    {
if(!isInternetAvailable())
{
    Random r = new Random();
    int randomInt = r.nextInt(99999999) + 1;
    Toast.makeText(Recevoir.this, "not connected", Toast.LENGTH_LONG).show();
    cret=getSharedPreferences("sp_cret",MODE_PRIVATE);
    SharedPreferences.Editor editor = cret.edit();
    editor.putString("codeoff", valueOf(randomInt));
    editor.apply();
    editor.putString("off", "1");
    editor.apply();

    codeR.setText(randomInt+" ");


    AlertDialog dlg= new AlertDialog.Builder(Recevoir.this)
            .setTitle("vous etes sûr que vos informations sont correctes ")
            .setMessage("\n\n-you are curntlly in offline area  \n\n-Cliquez sur Modifier pour changer les information\n\n")
            .setPositiveButton("Confirmer",null).setNegativeButton("Modifier",null).create();
    dlg.show();



}
        Toast.makeText(Recevoir.this, codeR.getText().toString(), Toast.LENGTH_LONG).show();

        StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/connect.php?gen=1"
                +codeR.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String x=String.copyValueOf(response.toCharArray(), 0, response.length()-4);
                codeR.setText(x);
                find();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Recevoir.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(Recevoir.this).add(strq);
    }
}