package creation;

import static com.example.monconstat.FilePath.getPath;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monconstat.R;
import com.example.monconstat.bluetoothConnect;
import com.example.monconstat.uploadConsta;
import com.kyanogen.signatureview.SignatureView;


public class observation extends Fragment {
    TextView suvitAs3,prefirst3,tv;
    SharedPreferences cret;
    OnDataPass onDataPass;
    Button b1;
    SignatureView sv;
Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_observation, container, false);
        sv =view.findViewById(R.id.signature_view);
        b1= view.findViewById(R.id.button7);
        //// BOUTTON
        tv= view.findViewById(R.id.tv);

        cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();



        suvitAs3= view.findViewById(R.id.suvitAs7);
        prefirst3=view.findViewById(R.id.prefirst7);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !sv.isBitmapEmpty()){
                    AlertDialog dlg= new AlertDialog.Builder(getContext())
                            .setTitle("Etes-vous sûr que vos informations sont correctes ")
                            .setMessage("\n\n-Cliquez sur Confirmer pour continuer si vos informations sont correctes \n\n-Cliquez sur Modifier pour changer les information\n\n")
                            .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                    SharedPreferences.Editor editor = cret.edit();
                                    editor.putString("observ",tv.getText().toString());
                                    editor.apply();
                              bitmap = sv.getSignatureBitmap();
                                    Uri uri = getImageUri(getContext(), bitmap);
                                    String u1=  getPath(getContext(),uri);
                                    editor.putString("signe", u1);
                                    editor.apply();

                                    Intent intent2 = new Intent(getContext(), bluetoothConnect.class);
                                    startActivity(intent2);
                                }
                            }).setNegativeButton("Modifier",null).create();
                    dlg.show();
                }
else {
                    Toast.makeText(getContext(), "signer s'il vous plait", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {

                                  @Override
                                  public void onClick(View v) {
                                      sv.clearCanvas();

                                  }
                              }

        );
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.cameraChoc);
            }
        });
        ////


        onDataPass.onDataResceived("Étape 14:  Observation");
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }

}