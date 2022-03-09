package creation;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.monconstat.Final;
import com.example.monconstat.R;

import static com.example.monconstat.FilePath.getPath;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class PhotoVideo extends Fragment {
    Button b1, b2, b3;
    TextView s, t,BackToL;
    ImageView img1,img2,img3;
    VideoView vd1,vd2,vd3;
    int choix, s1,num;
    public int tailleTab;
    SharedPreferences imageSp;
    SharedPreferences cret;
    Drawable originalDrawable;
    public int [] tb = new int[20];
    public String[] tabdata=new String[30];
    public int indexpv,x=0;
    OnDataPass onDataPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_video, container, false);
        imageSp = getActivity().getSharedPreferences("sp_img", getActivity().MODE_PRIVATE);
        cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
        num = 0;
        b1 = view.findViewById(R.id.pv1);
        b2 = view.findViewById(R.id.pv2);
        b3 = view.findViewById(R.id.pv3);

        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);

        vd1 = view.findViewById(R.id.vd1);
        vd2 = view.findViewById(R.id.vd2);
        vd3 = view.findViewById(R.id.vd3);

        vd1.setVisibility(vd1.GONE);
        vd2.setVisibility(vd2.GONE);
        vd3.setVisibility(vd3.GONE);

        vd1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        vd2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        vd3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });

        SharedPreferences.Editor editor = cret.edit();
          editor.putString("dataBase",null);
        editor.apply();


        indexpv=3;




        s = view.findViewById(R.id.next);
        BackToL = view.findViewById(R.id.back);
        t = view.findViewById(R.id.titchoc);
        choix = 0;
        tailleTab= 0;
        originalDrawable = b2.getBackground();


        cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);

        ///////////onclick
        verif();
        indexpage(0);



        ///////////////
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s1 < 3) {
                    Toast.makeText(getActivity(), "il faut prendre 3 Photos ou videos !", Toast.LENGTH_SHORT).show();
                } else {
                    b1.setBackgroundResource(R.drawable.custom_button);

                    b2.setBackgroundDrawable(originalDrawable);
                    b3.setBackgroundDrawable(originalDrawable);
                    num++;
                    if (num == tailleTab) {
                        SharedPreferences.Editor editor = cret.edit();
                        for (int i = 0; i < tabdata.length; i++) {
                            if (tabdata[i] != null) {
                                Toast.makeText(getContext(), tabdata[i], Toast.LENGTH_SHORT).show();
                                editor.putString("dataBase["+String.valueOf(x)+"]", tabdata[i]);
                                x++;
                                editor.putString("LengthT", String.valueOf(x));
                            }
                        }
                        editor.apply();


                        Navigation.findNavController(view).navigate(R.id.etap12_1);
                    }
                    indexpv += 3;
                    img1.setImageBitmap(null);
                    img2.setImageBitmap(null);
                    img3.setImageBitmap(null);
                    //////////
                    vd1.setVisibility(vd1.GONE);
                    vd2.setVisibility(vd2.GONE);
                    vd3.setVisibility(vd3.GONE);
                    vd1.setVideoURI(null);
                    vd2.setVideoURI(null);
                    vd2.setVideoURI(null);
                    indexpage(num);
                }
            }
        });

        BackToL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //b1.setBackgroundColor(Color.parseColor("#3F51B5"));
                b1.setBackgroundResource(R.drawable.custom_button);
                b2.setBackgroundDrawable(originalDrawable);
                b3.setBackgroundDrawable(originalDrawable);
                num--;
                Toast.makeText(getActivity(), String.valueOf(num), Toast.LENGTH_SHORT).show();
                 if (num>=0) {
                     indexpv-=3;
                     indexpage(num);
                 }else
                     Navigation.findNavController(view).navigate(R.id.cameraChoc);

            }
        });



        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);
        }
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 100);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=1;
                selectionB(s1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=2;
                selectionB(s1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=3;
                selectionB(s1);
            }
        });
        onDataPass.onDataResceived("Étape 11 : \n Dégât apparents ");

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Toast.makeText(getContext(), uri.toString() , Toast.LENGTH_SHORT).show();
        //Toast.makeText(PhotoVideo.this, FilePath.getPath(getContext(), uri) , Toast.LENGTH_SHORT).show();
        if (choix == 1) {
            if (requestCode == 100) {

                Bitmap capteurImage = (Bitmap) data.getExtras().get("data");
                Uri uri = getImageUri(getContext(), capteurImage);
                Toast.makeText(getContext(), uri.toString(), Toast.LENGTH_SHORT).show();

                if (s1==1) {
                 //   tabdata[indexpv-s1]=  getPath(getContext(),uri);
                    b2.setBackgroundResource(R.drawable.custom_button);
                    b1.setBackgroundDrawable(originalDrawable);
                    vd1.setVideoURI(null);
                    vd1.setVisibility(vd1.GONE);
                    img1.setImageBitmap(capteurImage);
                    String urit = capteurImage.toString();


                    //  Toast.makeText(getContext(), s1 , Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), urit, Toast.LENGTH_SHORT).show();
                }else if(s1==2){
                  //  tabdata[indexpv-s1]= uri.toString();
                    img2.setImageBitmap(capteurImage);
                    vd2.setVideoURI(null);
                    b2.setBackgroundDrawable(originalDrawable);
                    b3.setBackgroundResource(R.drawable.custom_button);
                    vd2.setVisibility(vd2.GONE);
                }else
                    {
                        b3.setBackgroundDrawable(originalDrawable);
                        img3.setImageBitmap(capteurImage);
                 //  tabdata[indexpv-s1]=uri.toString();
                    vd3.setVisibility(vd3.GONE);
                    vd3.setVideoURI(null);
                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(280); //You can manage the blinking time with this parameter
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        anim.setRepeatCount(Animation.INFINITE);
                        s.startAnimation(anim);
                }

                tabdata[indexpv-s1]=  getPath(getContext(),uri);
                Toast.makeText(getContext(), tabdata[indexpv-s1] +(indexpv-s1)+" "+s1, Toast.LENGTH_SHORT).show();
            }

        } else if (choix == 2) {
            if (requestCode == 100) {
                Uri uri=data.getData();
                if (s1==1) {
                  //  tabdata[indexpv-s1]= uri.toString();
                    b2.setBackgroundResource(R.drawable.custom_button);
                    b1.setBackgroundDrawable(originalDrawable);
                    img1.setImageBitmap(null);
                    vd1.setVisibility(vd1.VISIBLE);
                    vd1.setVideoURI(data.getData());
                    vd1.start();
                }else if(s1==2){
                 //   tabdata[indexpv-s1]=uri.toString();
                    vd2.setVisibility(vd2.VISIBLE);
                    vd2.setVideoURI(data.getData());
                    vd2.start();
                    img2.setImageBitmap(null);
                    b2.setBackgroundDrawable(originalDrawable);
                    b3.setBackgroundResource(R.drawable.custom_button);
                }else{
                   // tabdata[indexpv-s1]=uri.toString();
                    vd3.setVisibility(vd3.VISIBLE);
                    img3.setImageBitmap(null);
                    vd3.setVideoURI(data.getData());
                    vd3.start();
                }

                tabdata[indexpv-s1]=  getPath(getContext(),uri);
            }

            }
        }


    public void verif() {
        int i=0;
            if (imageSp.getString("lf", null).equals("checked"))  {
                tb[i]=1;
                i++;
            }if (imageSp.getString("f", null).equals("checked"))  {
                tb[i]=2;
                 i++;
            }if (imageSp.getString("rf", null).equals("checked")) {
                tb[i]=3;
                i++;

            }if (imageSp.getString("l", null).equals("checked")){
                tb[i]=4;
                i++;
            } if (imageSp.getString("u", null).equals("checked"))  {
                tb[i]=5;
                i++;
            } if (imageSp.getString("r", null).equals("checked"))  {
                tb[i]=6;
                i++;
            }  if (imageSp.getString("lb", null).equals("checked"))  {
                tb[i]=7;
                i++;
            }  if (imageSp.getString("ba", null).equals("checked"))  {
                tb[i]=8;
                i++;
            }  if (imageSp.getString("rb", null).equals("checked"))  {
                tb[i]=9;
                i++;
            }
        tailleTab=i;

    }



    public void  selectionB(int h){



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Selection Type Video/Photo")
                .setPositiveButton("Photo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        choix = 1;
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 100);
                    }
                })
                .setNegativeButton("Video", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        choix = 2;
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                        startActivityForResult(intent, 100);
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();


}
public void indexpage(int i){
    if (tb[i] == 1) {
        t.setText("left-front");
    } else if (tb[i] == 2) {
        t.setText("Front");
    } else if (tb[i] == 3) {
        t.setText("Right-front");
    } else if (tb[i] == 4) {
        t.setText("Left");
    } else if (tb[i] == 5) {
        t.setText("Rev");
    } else if (tb[i] == 6) {
        t.setText("Right");
    } else if (tb[i] == 7) {
        t.setText("Left-back");
    } else if (tb[i] == 8) {
        t.setText("Back");
    } else if (tb[i] == 9) {
        t.setText("Right-back");
    } else t.setText("non");
}


    public Uri getImageUri(Context inContext, Bitmap inImage) {

        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage,
                "Title", null);



        return Uri.parse(path);
    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }


}