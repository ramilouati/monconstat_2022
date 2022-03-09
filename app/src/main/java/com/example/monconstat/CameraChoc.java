package com.example.monconstat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CameraChoc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraChoc extends Fragment {
    TextView suvitAs3,prefirst3;
    ImageView clf,cf,cfr,cl,cu,cr,cbl,cb,cbr;
    int t1,t2,t3,t4,t5,t6,t7,t8,t9,choix;
    SharedPreferences imageSp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_camera_choc, container, false);
        imageSp=getActivity().getSharedPreferences("sp_img",getActivity().MODE_PRIVATE);
        //

        t1=t2=t3=t4=t5=t6=t7=t8=t9=choix=0;
//////// image
        clf=view.findViewById(R.id.clf);
        cf=view.findViewById(R.id.cf);
        cfr=view.findViewById(R.id.cfr);
        cl=view.findViewById(R.id.cl);
        cu=view.findViewById(R.id.cu);
        cr=view.findViewById(R.id.cr);
        cbl=view.findViewById(R.id.cbl);
        cb=view.findViewById(R.id.cb);
        cbr=view.findViewById(R.id.cbr);
////////


////////////controle Camera
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.CAMERA
                    },100);
        }
////////////////


        suvitAs3= view.findViewById(R.id.suvitAs6);
        prefirst3=view.findViewById(R.id.prefirst6);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.observation);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.pointDeChoc);
            }
        });

        ///////////onclick
        if (imageSp.getString("lf",null).equals("checked")){
           clf.setBackgroundResource(R.drawable.borderv);
        clf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.photoVideo);
               /* t1=1;
                choix=1;
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);*/

            }
        });}
        if (imageSp.getString("f",null).equals("checked")){
            cf.setBackgroundResource(R.drawable.borderv);
        cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.photoVideo);
            }
        });}

        if (imageSp.getString("rf",null).equals("checked")){
            cfr.setBackgroundResource(R.drawable.borderv);
        cfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.photoVideo);

            }
        });}
        if (imageSp.getString("l",null).equals("checked")){
            cl.setBackgroundResource(R.drawable.borderv);
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.photoVideo);
            }
        });}
        if (imageSp.getString("u",null).equals("checked")){
            cu.setBackgroundResource(R.drawable.borderv);
        cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.photoVideo);
            }
        });}
        if (imageSp.getString("r",null).equals("checked")){
            cr.setBackgroundResource(R.drawable.borderv);
        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.photoVideo);
            }
        });}

        if (imageSp.getString("lb",null).equals("checked")){
            cbl.setBackgroundResource(R.drawable.borderv);
        cbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.photoVideo);
            }
        });}

        if (imageSp.getString("ba",null).equals("checked")){
            cb.setBackgroundResource(R.drawable.borderv);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.photoVideo);
            }
        });}

        if (imageSp.getString("rb",null).equals("checked")){
            cbr.setBackgroundResource(R.drawable.borderv);
        cbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.photoVideo);
            }
        });}
        ///////////////

        //// BOUTTON

        suvitAs3= view.findViewById(R.id.suvitAs6);
        prefirst3=view.findViewById(R.id.prefirst6);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.observation);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.pointDeChoc);
            }
        });
        ////
        return view;
    }


}