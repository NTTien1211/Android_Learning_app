package com.hqsoft.esales.doancuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class youtubeApi extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    ImageView img_back;
    TextView tv_description;
    String API_KEY = "AIzaSyBsByOhd-kasDzSRP0hQeeqfYR1Cw5lWBI";
    int REQUEST_VIDEO = 123;
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_api);

        img_back = findViewById(R.id.img_back);
        youTubePlayerView = findViewById(R.id.myYoutube);
        youTubePlayerView.initialize(API_KEY, this);

        String Description = getIntent().getStringExtra("mota");
        tv_description = findViewById(R.id.tv_description);
        tv_description.setText(Description);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        String idVdeo = getIntent().getStringExtra("idVdeo");
        youTubePlayer.cueVideo(idVdeo);

    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(youtubeApi.this, REQUEST_VIDEO);
        }else {
            Toast.makeText(this, "ERR", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_VIDEO){
            youTubePlayerView.initialize(API_KEY, youtubeApi.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}