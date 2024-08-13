package org.q_style.ui.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

import org.q_style.R;

import java.util.Objects;


public class VideoFragment extends AppCompatActivity implements ExoPlayer.EventListener{

    private BandwidthMeter bandwidthMeter;
    private TrackSelector trackSelector;
    private TrackSelection.Factory trackSelectionFactory;
    private SimpleExoPlayer player;
    private PlayerView playerView;
    private DataSource.Factory dataSourceFactory;
    private ExtractorsFactory extractorsFactory;
    private DefaultBandwidthMeter defaultBandwidthMeter;
    private MediaSource mediaSource;
    TextView title;
    TextView discrip;

    private String[] songUrl = {"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"};
    // массивы данных
    String[] texts1 = {"Big Buck Bunny", "Elephant Dream", "For Bigger Blazes",
            "For Bigger Escape", "For Bigger Fun'", "For Bigger Joyrides", "For Bigger Meltdowns", "Sintel"};
    String[] texts2 = {"Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... ",
            "The first Blender Open Movie from 2006",
            "HBO GO now works with Chromecast -- the easiest way to enjoy online video on your TV.",
            "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for when Batman's escapes aren't quite big enough.",
            "Introducing Chromecast. The easiest way to enjoy online video and music on your TV. For $35. Find out more at google.com/chromecast.",
            "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for the times that call for bigger joyrides.",
            "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for when you want to make Buster's big meltdowns even bigger.",
            "Sintel is an independently produced short film, initiated by the Blender Foundation as a means to further improve and validate the free/open source 3D creation suite Blender."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
        Bundle extras = getIntent().getExtras();
        int i = extras.getInt("id");
        title = findViewById(R.id.videoTitle);
        discrip = findViewById(R.id.videoDisc);
        title.setText(texts1[i]);
        discrip.setText(texts2[i]);
        playerView = findViewById(R.id.ep_video_view);
        bandwidthMeter = new DefaultBandwidthMeter();
        extractorsFactory = new DefaultExtractorsFactory();

        trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

        trackSelector = new DefaultTrackSelector(trackSelectionFactory);

        defaultBandwidthMeter = new DefaultBandwidthMeter();

        dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "mediaPlayerSample"),defaultBandwidthMeter);

        mediaSource = new ExtractorMediaSource(Uri.parse(songUrl[i]),
                dataSourceFactory,
                extractorsFactory,
                null,
                null);

        player = ExoPlayerFactory.newSimpleInstance(this,trackSelector);

        player.addListener(this);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
        playerView.setPlayer(player);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.setPlayWhenReady(false);
    }


    @Override
    public void onLoadingChanged(boolean isLoading) {
        Log.i("TEST", "onLoadingChanged: " + isLoading + "");
        Log.i("TEST", "Buffered Position: " + player.getBufferedPosition() + "");
        Log.i("TEST", "Buffered Percentage: " + player.getBufferedPercentage() + "");
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        if(playbackState == ExoPlayer.STATE_READY){
            Log.i("TEST", "ExoPlayer State is: READY");
        } else if (playbackState == ExoPlayer.STATE_BUFFERING){
            Log.i("TEST", "ExoPlayer State is: BUFFERING");
        } else if (playbackState == ExoPlayer.STATE_ENDED){
            Log.i("TEST", "ExoPlayer State is: ENDED");
        } else if (playbackState == ExoPlayer.STATE_IDLE){
            Log.i("TEST", "ExoPlayer State is: IDLE");
        }


    }


    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }



    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

}
