package com.example.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class image_view extends AppCompatActivity {
    ListView L1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        L1=(ListView)findViewById(R.id.list);
    }
}
