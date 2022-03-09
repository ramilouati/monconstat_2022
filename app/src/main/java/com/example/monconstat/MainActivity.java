package com.example.monconstat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText user,pass;
    Button btn;
    TextView contact;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        btn=findViewById(R.id.btn);
        contact=findViewById(R.id.contact);
        sp=getSharedPreferences("sp_id",MODE_PRIVATE);
        String loginDirect =sp.getString("id",null);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 100);
        }
        if(loginDirect != null)
        {
            Intent intent = new Intent(MainActivity.this, Accueil.class);
            startActivity(intent);
        }

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  if (isNetworkAvailable()==true)
                login();
             //   else
                    Toast.makeText(MainActivity.this, "vérifiez votre connexion internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void info(){
        AlertDialog dlg= new AlertDialog.Builder(this)
                .setTitle("Contact")
                .setMessage("+216 55000000")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                }).create();
        dlg.show();
    }


    void login(){
        lodingd ld = new lodingd(MainActivity.this);
        ld.startLodingDialog();
        StringRequest strq = new StringRequest(Request.Method.POST,"https://www.monconstat.tech/ConstatMobile/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ld.dismissDialog();
                    }
                }, 0);
                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();

                if(response.contains("1")||response.contains("11")) {

                 //   Intent intent = new Intent(MainActivity.this,Accueil.class);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("id",user.getText().toString());
                    editor.putString("pass",pass.getText().toString());

                    if(response.contains("11")){
                        editor.putString("premierfoix","0");
                        Intent intent2 = new Intent(MainActivity.this,Accueil.class);
                        intent2.putExtra("code",user.getText().toString());
                        startActivity(intent2);
                        finish();
                    }
                    else{
                        editor.putString("premierfoix","1");
                        Intent intent2 = new Intent(MainActivity.this,MainActivity2.class);
                        intent2.putExtra("code",user.getText().toString());
                        startActivity(intent2);
                        finish();
                        Toast.makeText(MainActivity.this,"jdyyyyddddd",Toast.LENGTH_LONG).show();

                    }

                    editor.apply();
                  //  startActivity(intent);
                }else if(response.contains("0")) Toast.makeText(MainActivity.this,"USER ou PASSWORD incorrect",Toast.LENGTH_SHORT).show();

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("userc",user.getText().toString());
                params.put("mdp",pass.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(MainActivity.this).add(strq);
    }
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}