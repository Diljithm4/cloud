package com.example.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText e5,e6;
    Button b3,b2;

    String ip="";
    SharedPreferences sh;
    JSONParser jsonParser = new JSONParser();
    public static String ur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e5=(EditText)findViewById(R.id.editText22);
        e6=(EditText)findViewById(R.id.editText23);
        b2=(Button)findViewById(R.id.button15);
        b3=(Button)findViewById(R.id.button16);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ip="192.168.43.172:5000";

        SharedPreferences.Editor ed=sh.edit();
        ed.putString("ip",ip);
        ed.commit();
        try {
            if(Build.VERSION.SDK_INT>9)
            {
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
        }
        catch (Exception e)
        {

        }
        ip=sh.getString("ip","");
        ur="http://"+ip+"/login";
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e5.getText().toString();
                String password = e6.getText().toString();

                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("pass", password));

                JSONObject jobj = null;
                try {
                    jobj = (JSONObject) jsonParser.makeHttpRequest(ur, "GET", params);
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "e111" + e1, Toast.LENGTH_LONG).show();
                }
                String s = null;

                try {

                    String ss = jobj.getString("task");

                    if (ss.equals("failed")) {
                        Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_LONG).show();

                    } else {

                        SharedPreferences.Editor ed=sh.edit();
                        ed.putString("id",ss);
                        ed.commit();

                        Intent i = new Intent(getApplicationContext(), cloud_home_new.class);
                        startActivity(i);

                    }


                } catch (Exception e) { // TODO Auto-generated catch block

                    Toast.makeText(getApplicationContext(), "e       " + e, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    }
