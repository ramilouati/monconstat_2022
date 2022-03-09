package viewinfoR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monconstat.Accueil;
import com.example.monconstat.R;

public class RV_VehiculeP extends AppCompatActivity {
    SharedPreferences rvInfo;
    TextView dcin,suvitAs3,prefirst3;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_v__vehicule_p);

        btnback=findViewById(R.id.btnback2);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RV_VehiculeP.this, Accueil.class);
                startActivity(intent);
            }
        });



        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);

        String loginDirect =rvInfo.getString("marque1",null);
        dcin = findViewById(R.id.rvtime1);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("type1",null);
        dcin = findViewById(R.id.myD);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("immat1",null);
        dcin = findViewById(R.id.rvdem1);
        dcin.setText(loginDirect);

        //user2//

        loginDirect =rvInfo.getString("type1",null);
        dcin = findViewById(R.id.yourD);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("type1",null);
        dcin = findViewById(R.id.rvbless2);
        dcin.setText(loginDirect);

        loginDirect =rvInfo.getString("type1",null);
        dcin = findViewById(R.id.rvdem2);
        dcin.setText(loginDirect);




        suvitAs3= findViewById(R.id.suvitAs16);
        prefirst3=findViewById(R.id.prefirst14);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RV_VehiculeP.this, RV_iconsta.class);
                startActivity(intent2);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RV_VehiculeP.this, RV_assP.class);
                startActivity(intent2);
            }
        });



    }
}