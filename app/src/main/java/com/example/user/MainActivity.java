package com.example.user;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e5,e6;
    Button b3,b2;

    String ip="";
    SharedPreferences sh;
    JSONParser jsonParser = new JSONParser();
    // SharedPreferences sh;
    public static String ur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b2=(Button)findViewById(R.id.button15);
        b3=(Button)findViewById(R.id.button16);
        e5=(EditText)findViewById(R.id.editText7);
        e6=(EditText)findViewById(R.id.editText8);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ip="192.168.137.111:5000";

        SharedPreferences.Editor ed=sh.edit();
        ed.putString("ip",ip);
        ed.commit();
        ip=sh.getString("ip","");
        ur="http://"+ip+"/registration";
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e5.getText().toString();
                String mail = e6.getText().toString();

    }
}
