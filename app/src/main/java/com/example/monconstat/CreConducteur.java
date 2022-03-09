package com.example.monconstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import creation.OnDataPass;


public class CreConducteur extends Fragment {
    SharedPreferences sp;
    TextView dcin,dcin1,dcin2,dcin3,suvitAs3,prefirst3;
    OnDataPass onDataPass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cre_conducteur, container, false);
        dcin=view.findViewById(R.id.nomp);

        dcin1=view.findViewById(R.id.prenomp);

        dcin2=view.findViewById(R.id.adressep);

        dcin3=view.findViewById(R.id.tel);



        sp=this.getActivity().getSharedPreferences("sp_id", Context.MODE_PRIVATE);
        String id=sp.getString("id",null);

        String url="https://www.monconstat.tech/ConstatMobile/profil.php?id="+id;
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        String nom = obj.getString("nom");
                        dcin.setText(nom);
                        String prenom = obj.getString("prenom");
                        dcin1.setText(prenom);
                        String adresse = obj.getString("adresse");
                        dcin2.setText(adresse);
                        String tel = obj.getString("tel");
                        dcin3.setText(tel);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(getActivity()).add(req);

        String prenom1 = sp.getString("prenom", null);
        dcin2.setText(prenom1);

        String adresse1 = sp.getString("adresse", null);
        dcin1.setText(adresse1);

        String nom1 = sp.getString("nom", null);
        dcin.setText(nom1);

        String tel1 = sp.getString("tel", null);
        dcin3.setText(tel1);

        //// BOUTTON

        suvitAs3= view.findViewById(R.id.suvitAs3);
        prefirst3=view.findViewById(R.id.prec);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.identiteVehicule);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.etape8);
            }
        });
        ////
        onDataPass.onDataResceived("Étape 8 : \n Assuré");
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }
}