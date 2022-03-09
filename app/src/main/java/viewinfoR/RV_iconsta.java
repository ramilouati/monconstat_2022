package viewinfoR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.monconstat.Accueil;
import com.example.monconstat.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class RV_iconsta extends AppCompatActivity {
    TextView dcin,suvitAs3,prefirst3;
    String ob1,ob2,code;
    SharedPreferences rvInfo;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_v_iconsta);

        suvitAs3= findViewById(R.id.next5);
        prefirst3=findViewById(R.id.back5);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RV_iconsta.this, RV_obs.class);
                startActivity(intent2);
            }
        });


        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RV_iconsta.this, RV_VehiculeP.class);
                startActivity(intent2);

            }
        });

        btnback=findViewById(R.id.btnback6);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RV_iconsta.this, Accueil.class);
                startActivity(intent);
            }
        });

        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        code=rvInfo.getString("code",null);

        onshow();

    }

    public void onshow(){
        String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&info=i";
        Toast.makeText(RV_iconsta.this,code,Toast.LENGTH_LONG).show();
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);

                            String id_contrat = obj.getString("BlessesE");
                            dcin = findViewById(R.id.myD);
                            dcin.setText(id_contrat);
                            id_contrat = obj.getString("DegatsE");
                            dcin = findViewById(R.id.rvdem1);
                            dcin.setText(id_contrat);

                            String debut = obj.getString("temoinsE");
                            dcin = findViewById(R.id.rvob1);
                            dcin.setText(debut);

                            String geoE = obj.getString("geoE");
                            dcin = findViewById(R.id.rvlocal1);
                            dcin.setText(geoE);

                            String getdateE = obj.getString("getdateE");
                            dcin = findViewById(R.id.rvtime1);
                            dcin.setText(getdateE);

                          //////////
                             id_contrat = obj.getString("BlessesR");
                            dcin = findViewById(R.id.rvbless2);
                            dcin.setText(id_contrat);

                            id_contrat = obj.getString("DegatsR");
                            dcin = findViewById(R.id.rvdem2);
                            dcin.setText(id_contrat);

                             debut = obj.getString("temoinsR");
                            dcin = findViewById(R.id.rvob2);
                            dcin.setText(debut);

                             geoE = obj.getString("geoR");
                            dcin = findViewById(R.id.rvlocal2);
                            dcin.setText(geoE);

                             getdateE = obj.getString("getdateR");
                            dcin = findViewById(R.id.yourD);
                            dcin.setText(getdateE);
                        ob1 = obj.getString("observE");
                        ob2 = obj.getString("observR");
                        SharedPreferences.Editor editor = rvInfo.edit();
                        editor.putString("ob1",ob1);
                        editor.putString("ob2",ob2);
                        editor.apply();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RV_iconsta.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RV_iconsta.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(RV_iconsta.this).add(req);
    }



}