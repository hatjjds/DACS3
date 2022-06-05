package com.example.dacs3;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerActiviti extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_activiti);
        getSupportActionBar().hide();

        videoView=findViewById(R.id.video_view);

        Bundle bundle = getIntent().getExtras();
        String src=bundle.getString("src");

        Uri uri= Uri.parse(src);
        videoView.setVideoURI(uri);

        MediaController controller=new MediaController(this);
        videoView.setMediaController(controller);
        controller.setAnchorView(videoView);
    }
}