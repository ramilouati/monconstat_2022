package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UploadUser2 extends AppCompatActivity {
    ProgressBar progressBar2;
    String RoE,code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_user2);
      //  progressBar2=findViewById(R.id.progressBar2);
        //////////////////////////////
        Intent intentg= getIntent();
        RoE=intentg.getStringExtra("RoE");
        code=intentg.getStringExtra("code");
        Toast.makeText(this, RoE+" "+code, Toast.LENGTH_SHORT).show();
        ////////////////////////
        onrelode();

    }
    public void onrelode()
    {
        StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/upload.php?Roe="+code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(UploadUser2.this, response, Toast.LENGTH_SHORT).show();
                if (response.contains("1")) {
                    Intent intent2 = new Intent(UploadUser2.this, etape1_shared.class);
                    intent2.putExtra("RoE",RoE);
                    intent2.putExtra("code",code);
                    startActivity(intent2);
                    finish();
                }else
                {
                    onrelode();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UploadUser2.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("RoE",(RoE+"1"));
                return params;
            }
        };
        Volley.newRequestQueue(UploadUser2.this).add(strq);
    }
}