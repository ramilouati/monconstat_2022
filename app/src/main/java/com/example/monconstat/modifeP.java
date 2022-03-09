package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class modifeP extends AppCompatActivity {
    EditText p1,p2,p3;
    Button btn;
    ImageView btnback;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modife_p);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);
        p3=findViewById(R.id.p3);
        btn=findViewById(R.id.btnconM);
        btnback=findViewById(R.id.btnbackm);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(modifeP.this, profil.class);
                startActivity(intent);
            }
        });
        sp = getSharedPreferences("sp_id", MODE_PRIVATE);
        String id = sp.getString("id", null);
       Intent intentg= getIntent();
       String key=intentg.getStringExtra("pass");

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                if (p1.getText().toString().isEmpty()){Toast.makeText(modifeP.this, "Entree votre mot de passe", Toast.LENGTH_SHORT).show();}
                else if (p2.getText().toString().isEmpty()){Toast.makeText(modifeP.this, "Entree votre nouvelle mot de passe", Toast.LENGTH_SHORT).show();}
                else if (p3.getText().toString().isEmpty()){Toast.makeText(modifeP.this, "Confirme votre mot de passe", Toast.LENGTH_SHORT).show();}
                else if (!(key.equals(p1.getText().toString())))
                {Toast.makeText(modifeP.this, "mot de passe incorrect", Toast.LENGTH_SHORT).show();}
                else if (!(p2.getText().toString().equals(p3.getText().toString())))
                {Toast.makeText(modifeP.this, "Confirmation incorrect", Toast.LENGTH_SHORT).show();}
                   else  {
                    StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/modifep.php?id=" + id, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.contains("1")) {
                                Toast.makeText(modifeP.this, "MODIFE", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(modifeP.this, profil.class);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("key",key);
                                editor.apply();
                                startActivity(intent);
                            } else if (response.contains("0"))
                                Toast.makeText(modifeP.this, "PASSWORD incorrect", Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(modifeP.this, error.toString(), Toast.LENGTH_LONG).show();
                        }

                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("mdp", p2.getText().toString());
                            return params;
                        }
                    };
                    Volley.newRequestQueue(modifeP.this).add(strq);
                }}

        });}


    }
