package creation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.monconstat.R;

public class testi extends AppCompatActivity {
TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    String id;
    String RoE,code;
    SharedPreferences sp,cret,imageSp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi);
        t1=findViewById(R.id.testi1);
        t2=findViewById(R.id.testi2);
        t3=findViewById(R.id.testi3);
        t4=findViewById(R.id.testi4);
        t5=findViewById(R.id.testi5);
        t6=findViewById(R.id.testi6);
        t7=findViewById(R.id.testi7);
        t8=findViewById(R.id.testi8);
        t9=findViewById(R.id.testi9);
        Intent intentg= getIntent();
        cret=getSharedPreferences("sp_cret",MODE_PRIVATE);
        imageSp=getSharedPreferences("sp_img",MODE_PRIVATE);
        sp=getSharedPreferences("sp_id",MODE_PRIVATE);
        id=sp.getString("id",null);
        RoE=intentg.getStringExtra("RoE");
        code=intentg.getStringExtra("code");


        t1.setText(id);
        t2.setText(RoE);
        t3.setText(code);
        t4.setText(cret.getString("groupB1p1",null));
        t5.setText(cret.getString("circonstances",null));
        t6.setText(cret.getString("geo",null));
        t7.setText(cret.getString("observ",null));
        t8.setText(cret.getString("temoins",null));

        String degat="";
        if (imageSp.getString("f",null).equals("checked"))
        {
            degat=degat+"f,";
        }
        if (imageSp.getString("lf",null).equals("checked"))
        {
            degat=degat+"lf,";
        }
        if (imageSp.getString("rf",null).equals("checked"))
        {
            degat=degat+"rf,";
        }
        if (imageSp.getString("l",null).equals("checked"))
        {
            degat=degat+"l,";
        }
        if (imageSp.getString("u",null).equals("checked"))
        {
            degat=degat+"u,";
        }
        if (imageSp.getString("r",null).equals("checked"))
        {
            degat=degat+"r,";
        }
        if (imageSp.getString("lb",null).equals("checked"))
        {
            degat=degat+"lb,";
        }
        if (imageSp.getString("ba",null).equals("checked"))
        {
            degat=degat+"ba,";
        }
        if (imageSp.getString("rb",null).equals("checked"))
        {
            degat=degat+"rb,";
        }
        t9.setText(degat);

    }
}