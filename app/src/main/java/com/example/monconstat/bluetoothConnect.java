package com.example.monconstat;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class bluetoothConnect extends AppCompatActivity {
    Button recb,envoyerb;
    EditText rechP;
    ProgressBar LoadConnectBar;
    SharedPreferences cret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connect);
        recb=findViewById(R.id.recevoirB);
        envoyerb=findViewById(R.id.envoyerB);
        rechP=findViewById(R.id.rechP);
        //LoadConnectBar=findViewById(R.id.LoadConnectBar);
        //LoadConnectBar.setVisibility(ProgressBar.INVISIBLE);
        recb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(bluetoothConnect.this, Recevoir.class);
                startActivity(intent2);
            }
        });



        envoyerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LoadConnectBar.setVisibility(ProgressBar.VISIBLE);

                if(!isInternetAvailable())
                {

                    Toast.makeText(bluetoothConnect.this, "not connected", Toast.LENGTH_LONG).show();
                    cret=getSharedPreferences("sp_cret",MODE_PRIVATE);
                    SharedPreferences.Editor editor = cret.edit();
                    editor.putString("codeoff", rechP.getText().toString());
                    editor.apply();
                    editor.putString("off", "1");
                    editor.apply();
                    Intent intent5 = new Intent(bluetoothConnect.this, Accueil.class);

                    startActivity(intent5);

                }


                onrelode();

            }
        });
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
    public void onrelode()
    {
        Toast.makeText(bluetoothConnect.this, rechP.getText().toString(), Toast.LENGTH_SHORT).show();

        StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/connect.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             //   if (response.contains("1")) {

                    Intent intent2 = new Intent(bluetoothConnect.this, uploadConsta.class);
                   String e="e";
                   intent2.putExtra("RoE",e);
                   intent2.putExtra("code",rechP.getText().toString());
                   startActivity(intent2);
          //     }else
               Toast.makeText(bluetoothConnect.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(bluetoothConnect.this, error.toString(), Toast.LENGTH_LONG).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mdp",rechP.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(bluetoothConnect.this).add(strq);
    }
}