package creation;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.example.monconstat.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link identiteVehicule#} factory method to
 * create an instance of this fragment.
 */
public class identiteVehicule extends Fragment {
    SharedPreferences sp;
    TextView dcin,dcin1,dcin2,suvitAs3,prefirst3;
    OnDataPass onDataPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_identite_vehicule, container, false);

        dcin=view.findViewById(R.id.marqueV);

        dcin1=view.findViewById(R.id.typeV);

        dcin2=view.findViewById(R.id.immatV);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    }, 100);
        }

        sp=this.getActivity().getSharedPreferences("sp_id", Context.MODE_PRIVATE);
        String id=sp.getString("id",null);
        String url="https://www.monconstat.tech/ConstatMobile/contra.php?id="+id;
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        String nom = obj.getString("marque");
                        dcin.setText(nom);
                        String prenom = obj.getString("type");
                        dcin.setText(prenom);
                        String adresse = obj.getString("immat");
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

        String adresse1 = sp.getString("type", null);
        dcin1.setText(adresse1);

        String nom1 = sp.getString("marque", null);
        dcin.setText(nom1);

        String tel1 = sp.getString("immat", null);
        dcin2.setText(tel1);

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
        onDataPass.onDataResceived("Étape 9 : \n Identité du Véhicule");
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }
}