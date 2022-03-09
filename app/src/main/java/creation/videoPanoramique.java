package creation;

import static com.example.monconstat.FilePath.getPath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.monconstat.R;

public class videoPanoramique extends Fragment {
VideoView videoPano;
ImageView recordPano;
    SharedPreferences cret;

TextView suvitAs3,prefirst3;
    OnDataPass onDataPass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_video_panoramique, container, false);
        cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);


        videoPano=view.findViewById(R.id.videoPano);
        recordPano=view.findViewById(R.id.recordPano);
        recordPano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 6);
                startActivityForResult(intent, 100);



         //    vi   =  getPath(getContext(),uri);

                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(380); //You can manage the blinking time with this parameter
                anim.setStartOffset(20);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);
                suvitAs3.startAnimation(anim);
            }



        });
        videoPano.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });

        suvitAs3= view.findViewById(R.id.suvitAs10);
        prefirst3=view.findViewById(R.id.prefirst8);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.observation);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.etap12_3);
            }
        });

        onDataPass.onDataResceived("Étape 13: photo\n" +
                "panoramique de l'accident");
    return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if (requestCode == 100) {
                videoPano.setVideoURI(data.getData());
                videoPano.start();
                Uri uri=data.getData();
                String g1=  getPath(getContext(),uri);

                SharedPreferences.Editor editor = cret.edit();
                editor.putString("pano", null);

                editor.putString("pano", g1);
                editor.apply();
                Toast.makeText(getContext(), String.valueOf(data.getData()), Toast.LENGTH_SHORT).show();

            }
        }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage,
                "Title", null);
        return Uri.parse(path);
    }
    }
