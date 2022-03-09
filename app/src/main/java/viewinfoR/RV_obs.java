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

public class RV_obs extends AppCompatActivity {
    TextView rvob1,rvob2,suvitAs3,prefirst3;
    SharedPreferences rvInfo;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_v_obs);

        btnback=findViewById(R.id.btnback4);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RV_obs.this, Accueil.class);
                startActivity(intent);
            }
        });



        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        String ob1 =rvInfo.getString("ob1",null);
        String ob2 =rvInfo.getString("ob2",null);
        rvob1=findViewById(R.id.rvob1);
        rvob2=findViewById(R.id.rvob2);
        rvob1.setText(ob1);
        rvob2.setText(ob2);


        suvitAs3= findViewById(R.id.suvitAs17);
        prefirst3=findViewById(R.id.prefirst16);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RV_obs.this, Accueil.class);
                startActivity(intent2);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RV_obs.this, RV_iconsta.class);
                startActivity(intent2);
            }
        });


    }
}