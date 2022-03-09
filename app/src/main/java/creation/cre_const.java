package creation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monconstat.Accueil;
import com.example.monconstat.R;
import com.example.monconstat.etap12_1;


public class cre_const extends AppCompatActivity implements OnDataPass{
    TextView affichpage;
    ImageView btnbackm2;
    SharedPreferences sp;
    String nompage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cre_const);
        /*getSupportFragmentManager().
                beginTransaction().
                add(R.id.fragment,new etap12_1()).commit();*/




        btnbackm2=findViewById(R.id.btnbackm2);
        affichpage=findViewById(R.id.affichpage);

        sp=getSharedPreferences("sp_id",MODE_PRIVATE);
        String id=sp.getString("id",null);
        btnbackm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(cre_const.this, Accueil.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onDataResceived(String data) {
        nompage=data;
        affichpage.setText(nompage);
    }
}