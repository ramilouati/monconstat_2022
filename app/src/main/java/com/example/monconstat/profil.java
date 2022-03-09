package com.example.monconstat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class profil extends AppCompatActivity {
    ImageView btnback;
    SharedPreferences sp;
    TextView dcin;
    String pas;
    Button btnM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        btnback=findViewById(R.id.btnback);
        btnM=findViewById(R.id.btnmp);
        sp=getSharedPreferences("sp_id",MODE_PRIVATE);
        String id=sp.getString("id",null);
        dcin=findViewById(R.id.cin);
        String url="https://www.monconstat.tech/ConstatMobile/profil.php?id="+id;
        lodingd ld = new lodingd(profil.this);
        ld.startLodingDialog();
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            SharedPreferences.Editor editor = sp.edit();
            @Override
            public void onResponse(String response) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ld.dismissDialog();
                    }
                }, 0);
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj =  juser.getJSONObject(i);
                        String cin = obj.getString("cin");
                        dcin=findViewById(R.id.cin13);
                        dcin.setText(cin);

                        String nom = obj.getString("nom");
                        editor.putString("nom",nom);
                        editor.apply();

                        dcin=findViewById(R.id.cin2);
                        dcin.setText(nom);

                        String prenom = obj.getString("prenom");
                        editor.putString("prenom",prenom);
                        editor.apply();

                        dcin=findViewById(R.id.cin);
                        dcin.setText(prenom);

                        String adresse = obj.getString("adresse");
                        editor.putString("adresse",adresse);
                        editor.apply();

                        dcin=findViewById(R.id.cin4);
                        dcin.setText(adresse);

                        String nature = obj.getString("nature");
                        editor.putString("nature",nature);
                        editor.apply();

                        dcin=findViewById(R.id.cin6);
                        dcin.setText(nature);

                        String email = obj.getString("email");
                        editor.putString("email",email);
                        editor.apply();

                        dcin=findViewById(R.id.cin5);
                        dcin.setText(email);

                        String npermis = obj.getString("npermis");
                        editor.putString("npermis",npermis);
                        editor.apply();

                        dcin=findViewById(R.id.cin7);
                        dcin.setText(npermis);

                       String delivre = obj.getString("delivre");
                        editor.putString("delivre",delivre);
                        editor.apply();

                        dcin=findViewById(R.id.cin8);
                        dcin.setText(delivre);

                        String civilite = obj.getString("civilite");
                        editor.putString("civilite",civilite);
                        editor.apply();

                        dcin=findViewById(R.id.cin9);
                        dcin.setText(civilite);

                        String tel = obj.getString("tel");
                        editor.putString("tel",tel);
                        editor.apply();

                        dcin=findViewById(R.id.cin10);
                        dcin.setText(tel);

                        String assurance = obj.getString("assurance"); //nom assurance
                        editor.putString("assurance",assurance);
                        editor.apply();

                        String id_contrat = obj.getString("id_contrat"); // id_contrat
                        editor.putString("id_contrat",id_contrat);
                        editor.apply();

                        String id_agence = obj.getString("id_agence"); // id_agence
                        editor.putString("id_agence",id_agence);
                        editor.apply();

                        String debut = obj.getString("debut");  // debut contrat
                        editor.putString("debut",debut);
                        editor.apply();

                        String fin = obj.getString("fin");  // fin contrat
                        editor.putString("fin",fin);
                        editor.apply();

                        String marque = obj.getString("marque");  //marque vehicule
                        editor.putString("marque",marque);
                        editor.apply();

                        String type = obj.getString("type"); // type vehicule
                        editor.putString("type",type);
                        editor.apply();

                        String immat = obj.getString("immat");  // immatricule véhicule
                        editor.putString("immat",immat);
                        editor.apply();

                        sp=getSharedPreferences("sp_id",MODE_PRIVATE);

                        Toast.makeText(profil.this, sp.getString("immat",null),Toast.LENGTH_LONG).show();

                        pas = obj.getString("mdp");



                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(profil.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(profil.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(profil.this).add(req);

       btnM.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent2 = new Intent(profil.this, modifeP.class);
               intent2.putExtra("pass",pas);
               startActivity(intent2);
           }
       });
        //<!---------on click---------!>
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profil.this, Accueil.class);
                startActivity(intent);
            }
        });

    }
}