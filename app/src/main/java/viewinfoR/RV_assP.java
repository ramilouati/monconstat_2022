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
import com.example.monconstat.showAct;

import org.json.JSONArray;
import org.json.JSONObject;

public class RV_assP extends AppCompatActivity {
    SharedPreferences rvInfo;
    TextView   dcin,suvitAs3,prefirst3;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_v_ass_p);


        btnback=findViewById(R.id.btnback3);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RV_assP.this, Accueil.class);
                startActivity(intent);
            }
        });

        suvitAs3= findViewById(R.id.suvitAs14);
        prefirst3=findViewById(R.id.prefirst13);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RV_assP.this, RV_VehiculeP.class);
                startActivity(intent2);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RV_assP.this, Rv_infoP.class);
                startActivity(intent2);
            }
        });


        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);

        String loginDirect =rvInfo.getString("nom1",null);
        dcin = findViewById(R.id.evnom1);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("prenom1",null);
        dcin = findViewById(R.id.rvprenom1);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("adresse1",null);
        dcin = findViewById(R.id.rvadress1);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("npermis1",null);
        dcin = findViewById(R.id.rvpc1);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("delivre1",null);
        dcin = findViewById(R.id.rvd1);
        dcin.setText(loginDirect);


        //user//
         loginDirect =rvInfo.getString("nom2",null);
        dcin = findViewById(R.id.evnom2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("prenom2",null);
        dcin = findViewById(R.id.rvprenom2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("adresse2",null);
        dcin = findViewById(R.id.rvadress2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("npermis2",null);
        dcin = findViewById(R.id.rvpc2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("delivre2",null);
        dcin = findViewById(R.id.rvd2);
        dcin.setText(loginDirect);




    }




}