package com.example.monconstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link identiteVehicule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class identiteVehicule extends Fragment {
    SharedPreferences sp;
    TextView dcin,suvitAs3,prefirst3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_identite_vehicule, container, false);
        sp=this.getActivity().getSharedPreferences("sp_id", Context.MODE_PRIVATE);
        String id=sp.getString("id",null);
        String url="https://achref12.000webhostapp.com/constat/login/contra.php?id="+id;
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        String nom = obj.getString("marque");
                        dcin=view.findViewById(R.id.marqueV);
                        dcin.setText(nom);
                        String prenom = obj.getString("type");
                        dcin=view.findViewById(R.id.typeV);
                        dcin.setText(prenom);
                        String adresse = obj.getString("immat");
                        dcin=view.findViewById(R.id.immatV);
                        dcin.setText(adresse);


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

        //// BOUTTON

        suvitAs3= view.findViewById(R.id.suvitAs4);
        prefirst3=view.findViewById(R.id.prefirst4);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.pointDeChoc);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.creConducteur);
            }
        });
        ////

        return view;
    }
}