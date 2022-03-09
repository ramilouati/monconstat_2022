package com.example.monconstat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;


public class PhotoVideo extends Fragment {
    CardView card;
    Button b1, b2, b3;
    TextView p, v, a;
    ImageView img;
    VideoView vf;
    int choix,s1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_video, container, false);
        b1 = view.findViewById(R.id.pv1);
        b2 = view.findViewById(R.id.pv2);
        b3 = view.findViewById(R.id.pv3);
        card.setVisibility(card.INVISIBLE);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);
        }



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card.setVisibility(card.VISIBLE);

                p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        card.setVisibility(card.INVISIBLE);
                        choix = 1;
                        s1=1;
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 100);
                    }
                });

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        card.setVisibility(card.INVISIBLE);
                        choix = 2;
                        s1=1;
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                        startActivityForResult(intent, 100);
                    }
                });

                a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        card.setVisibility(card.INVISIBLE);
                    }
                });



            }
        });

    if (s1==1) {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card.setVisibility(card.VISIBLE);
                p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        card.setVisibility(card.INVISIBLE);
                        choix = 1;
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 100);
                    }
                });

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        card.setVisibility(card.INVISIBLE);
                        choix = 2;
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                        startActivityForResult(intent, 100);
                    }
                });

                a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        card.setVisibility(card.INVISIBLE);
                    }
                });


            }
        });
    }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (choix == 1) {
            if (requestCode == 100) {
                Bitmap capteurImage = (Bitmap) data.getExtras().get("data");
                    img.setImageBitmap(capteurImage);

            }
        } else if (choix == 2) {
            if (requestCode == 100) {
                vf.setVideoURI(data.getData());
                vf.start();

            }

        }
    }
}