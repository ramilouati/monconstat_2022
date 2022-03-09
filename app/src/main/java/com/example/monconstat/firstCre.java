package com.example.monconstat;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstCre#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firstCre extends Fragment {
TextView getdate,temoins,FistToGeo;
EditText Temoins;
ImageView imgplus;
RadioGroup radioGroup,radioGroup2;
RadioButton radioButton;
SharedPreferences cret;
int CountSelectB,CountSelectB2=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_first_cre, container, false);

       ///////////////////////
        cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();
        editor.putString("Temoins","");
        editor.apply();
        /////////////////////////////


       /* t1.setVisibility(view.GONE);
        t1m.setVisibility(view.GONE);*/
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        String datetime = simpleDateFormat.format(calendar.getTime());
        getdate= view.findViewById(R.id.getdate);
        getdate.setText(datetime);
        FistToGeo=view.findViewById(R.id.FistToGeo);
        //RadioG
       radioGroup=view.findViewById(R.id.radioGroupe);

        FistToGeo.setTextColor(Color.parseColor("#8c8c8c"));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton=view.findViewById(checkedId);
                SharedPreferences.Editor editor = cret.edit();
                editor.putString("groupB1p1",radioButton.getText().toString());
                editor.apply();
                CountSelectB=1;
                if (CountSelectB+CountSelectB2==2)
                FistToGeo.setTextColor(Color.parseColor("#009685"));

            }
        });
        radioGroup=view.findViewById(R.id.radioGroup2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton=view.findViewById(checkedId);
                SharedPreferences.Editor editor = cret.edit();
                editor.putString("groupB2p1",radioButton.getText().toString());
                editor.apply();
                CountSelectB2=1;
                if (CountSelectB+CountSelectB2==2)
                    FistToGeo.setTextColor(Color.parseColor("#009685"));
            }
        });
       //TEMIONS
        temoins=view.findViewById(R.id.temoins);
        Temoins=view.findViewById(R.id.Temoins);
        //imageplus
        imgplus=view.findViewById(R.id.imgplus);

        imgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (temoins.getText().toString().isEmpty())
                {temoins.setText(Temoins.getText().toString());

                }
                else if  (!((Temoins.getText().toString().isEmpty()))){
                    temoins.setText(temoins.getText().toString()+" , "+Temoins.getText().toString());
                } Temoins.setText(null);
                SharedPreferences.Editor editor = cret.edit();
                editor.putString("Temoins",Temoins.getText().toString());
                editor.apply();
            }

        });

            FistToGeo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (CountSelectB+CountSelectB2==2) {
                    Navigation.findNavController(view).navigate(R.id.geolocaliCre);}
                }
            });


        return view;
    }


}