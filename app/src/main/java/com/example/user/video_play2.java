package com.example.user;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class video_play2 extends AppCompatActivity {
VideoView v;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play2);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
//		String date=getIntent().getStringExtra("date");
        String filename = getIntent().getStringExtra("filename");
        Toast.makeText(getApplicationContext(), filename, Toast.LENGTH_LONG).show();
//

        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
        } catch (Exception e) {

        }


        v = (VideoView) findViewById(R.id.videoView1);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(v);
        mc.setMediaPlayer(v);
        Toast.makeText(getApplicationContext(), filename, Toast.LENGTH_LONG).show();
        Uri uri = Uri.parse("http://" + ip + ":8080/Pink_Police/video/" + filename);
        v.setMediaController(mc);
        v.setVideoURI(uri);
    }


    }

