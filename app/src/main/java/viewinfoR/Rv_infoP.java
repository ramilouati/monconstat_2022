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
import com.example.monconstat.mes_constat;
import com.example.monconstat.profil;

import org.json.JSONArray;
import org.json.JSONObject;

public class Rv_infoP extends AppCompatActivity {
    String code,RoE;
    TextView dcin,suvitAs3,prefirst3;
    SharedPreferences rvInfo;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_info_p);
        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);


        btnback=findViewById(R.id.btnback5);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rv_infoP.this, Accueil.class);
                startActivity(intent);
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


        suvitAs3= findViewById(R.id.suvitAs13);
        prefirst3=findViewById(R.id.prefirst11);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Rv_infoP.this, RV_assP.class);
                startActivity(intent2);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Rv_infoP.this, mes_constat.class);
                startActivity(intent2);
            }
        });

        onshow();
    }
    public void onshow(){
        String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&eor=r";
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);

                            obj = juser.getJSONObject(i);
                            String id_contrat = obj.getString("assurance");
                            dcin = findViewById(R.id.VAu1);
                            dcin.setText(id_contrat);
                            id_contrat = obj.getString("id_contrat");
                            dcin = findViewById(R.id.PDu1);
                            dcin.setText(id_contrat);
                            //   String nom = obj.getString("immat");
                            String debut = obj.getString("debut");
                            dcin =findViewById(R.id.DUu1);
                            dcin.setText(debut);
                            String fin = obj.getString("fin");
                            dcin = findViewById(R.id.AUu1);
                            dcin.setText(fin);
                            String id_agence = obj.getString("id_agence");
                            dcin = findViewById(R.id.AGu1);
                            dcin.setText(id_agence);


                            /////// vehicule
                        SharedPreferences.Editor editor = rvInfo.edit();


                        String marque = obj.getString("marque");
                        editor.putString("marque1",marque);

                        String type = obj.getString("type");
                        editor.putString("type1",type);

                        String immat = obj.getString("immat");
                        editor.putString("immat1",immat);

                        //////
                        String nom = obj.getString("nom");
                        editor.putString("nom1",nom);

                        String prenom = obj.getString("prenom");
                        editor.putString("prenom1",prenom);

                        String adresse = obj.getString("adresse");
                        editor.putString("adresse1",adresse);

                        String npermis = obj.getString("npermis");
                        editor.putString("npermis1",npermis);

                        String delivre = obj.getString("delivre");
                        editor.putString("delivre1",delivre);
                        editor.apply();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Rv_infoP.this,e.toString(),Toast.LENGTH_LONG).show();
                }
                onshow2();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Rv_infoP.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(Rv_infoP.this).add(req);
    }




    public void onshow2(){
        String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&eor=e";
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);

                        obj = juser.getJSONObject(i);
                        String id_contrat = obj.getString("assurance");
                        dcin = findViewById(R.id.VAu2);
                        dcin.setText(id_contrat);
                        id_contrat = obj.getString("id_contrat");
                        dcin = findViewById(R.id.PDu2);
                        dcin.setText(id_contrat);
                        //   String nom = obj.getString("immat");
                        String debut = obj.getString("debut");
                        dcin =findViewById(R.id.DUu2);
                        dcin.setText(debut);
                        String fin = obj.getString("fin");
                        dcin = findViewById(R.id.AUu2);
                        dcin.setText(fin);
                        String id_agence = obj.getString("id_agence");
                        dcin = findViewById(R.id.AGu2);
                        dcin.setText(id_agence);


                        /////// vehicule
                        SharedPreferences.Editor editor = rvInfo.edit();


                        String marque = obj.getString("marque");
                        editor.putString("marque2",marque);

                        String type = obj.getString("type");
                        editor.putString("type2",type);

                        String immat = obj.getString("immat");
                        editor.putString("immat2",immat);

                        //////
                        String nom = obj.getString("nom");
                        editor.putString("nom2",nom);

                        String prenom = obj.getString("prenom");
                        editor.putString("prenom2",prenom);

                        String adresse = obj.getString("adresse");
                        editor.putString("adresse2",adresse);

                        String npermis = obj.getString("npermis");
                        editor.putString("npermis2",npermis);

                        String delivre = obj.getString("delivre");
                        editor.putString("delivre2",delivre);
                        editor.apply();


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Rv_infoP.this,e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Rv_infoP.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(Rv_infoP.this).add(req);
    }

}