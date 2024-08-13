package org.q_style.ui.slideshow;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.q_style.R;
import org.q_style.ui.home.HomeViewModel;

import java.util.concurrent.TimeUnit;

public class SlideshowPlayerFragment extends Fragment {
    private HomeViewModel homeViewModel;
        private ImageButton forwardbtn, backwardbtn, pausebtn, playbtn;
        private MediaPlayer mPlayer;
        private TextView songName, startTime, songTime;
        private SeekBar songPrgs;
        private static int oTime =0, sTime =0, eTime =0, fTime = 5000, bTime = 5000;
        private Handler hdlr = new Handler();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sladishow_player, container, false);

            backwardbtn = (ImageButton)root.findViewById(R.id.btnBackward);
            forwardbtn = (ImageButton)root.findViewById(R.id.btnForward);
            playbtn = (ImageButton)root.findViewById(R.id.btnPlay);
            pausebtn = (ImageButton)root.findViewById(R.id.btnPause);
            songName = (TextView)root.findViewById(R.id.txtSname);
            startTime = (TextView)root.findViewById(R.id.txtStartTime);
            songTime = (TextView)root.findViewById(R.id.txtSongTime);
            songName.setText("Mindfields");
            mPlayer = MediaPlayer.create(getActivity(), R.raw.mindfields);
            songPrgs = (SeekBar)root.findViewById(R.id.sBar);
            songPrgs.setClickable(false);
            pausebtn.setEnabled(false);

            playbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Playing Audio", Toast.LENGTH_SHORT).show();
                    mPlayer.start();
                    eTime = mPlayer.getDuration();
                    sTime = mPlayer.getCurrentPosition();
                    if(oTime == 0){
                        songPrgs.setMax(eTime);
                        oTime =1;
                    }
                    songTime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(eTime),
                            TimeUnit.MILLISECONDS.toSeconds(eTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS. toMinutes(eTime))) );
                    startTime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(sTime),
                            TimeUnit.MILLISECONDS.toSeconds(sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS. toMinutes(sTime))) );
                    songPrgs.setProgress(sTime);
                    hdlr.postDelayed(UpdateSongTime, 100);
                    pausebtn.setEnabled(true);
                    playbtn.setEnabled(false);
                }
            });
            pausebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlayer.pause();
                    pausebtn.setEnabled(false);
                    playbtn.setEnabled(true);
                    Toast.makeText(getActivity(),"Pausing Audio", Toast.LENGTH_SHORT).show();
                }
            });
            forwardbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((sTime + fTime) <= eTime)
                    {
                        sTime = sTime + fTime;
                        mPlayer.seekTo(sTime);
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                    }
                    if(!playbtn.isEnabled()){
                        playbtn.setEnabled(true);
                    }
                }
            });
            backwardbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((sTime - bTime) > 0)
                    {
                        sTime = sTime - bTime;
                        mPlayer.seekTo(sTime);
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                    }
                    if(!playbtn.isEnabled()){
                        playbtn.setEnabled(true);
                    }
                }
            });

        return root;
        }

        private Runnable UpdateSongTime = new Runnable() {
            @Override
            public void run() {
                sTime = mPlayer.getCurrentPosition();
                startTime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))) );
                songPrgs.setProgress(sTime);
                hdlr.postDelayed(this, 100);
            }
        };
}
