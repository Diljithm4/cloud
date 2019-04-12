package com.example.user;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class video_play2 extends Activity  {
    VideoView v1;
    SharedPreferences sh;
    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play2);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip", "");

        String filename=getIntent().getStringExtra("filename");

        Toast.makeText(getApplicationContext(), filename, Toast.LENGTH_LONG).show();
//

        try
        {
            if(android.os.Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
        }
        catch(Exception e)
        {

        }


        v1=(VideoView)findViewById(R.id.v1);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(v1);
        mc.setMediaPlayer(v1);
        Toast.makeText(getApplicationContext(), filename, Toast.LENGTH_LONG).show();
        Uri uri = Uri.parse("http://"+ip+"/static/file_upload/"+filename);
        v1.setMediaController(mc);
        v1.setVideoURI(uri);





    }



}
