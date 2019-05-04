package com.example.user;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

import static com.example.user.MainActivity.hasPermissions;

public class forgot_password extends AppCompatActivity {
    EditText e5,e6,e10;
    Button b4;



    String ip="";
    SharedPreferences sh;
    JSONParser jsonParser = new JSONParser();
    public static String ur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        b4=(Button)findViewById(R.id.button6);
        e5=(EditText)findViewById(R.id.editText5);
        e6=(EditText)findViewById(R.id.editText6);
        e10=(EditText)findViewById(R.id.editText10);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


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
        ur="http://"+ip+"/forgot_password";
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e5.getText().toString();
                String e_mail = e6.getText().toString();
                String phone = e10.getText().toString();

                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("email", e_mail));
                params.add(new BasicNameValuePair("phone", phone));

                JSONObject jobj = null;
                try {
                    jobj = (JSONObject) jsonParser.makeHttpRequest(ur, "GET", params);
                    //Toast.makeText(getApplicationContext(),"rere"+ur,Toast.LENGTH_LONG).show();
                } catch (JSONException e1) {
                     //TODO Auto-generated catch block
                    // Toast.makeText(getApplicationContext(), "e1" + e1, Toast.LENGTH_LONG).show();
                }
                String s = null;

                try {

                    String ss = jobj.getString("task");

                    if (ss.equals("failed")) {
                        Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_LONG).show();

                    } else {


                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);

                    }


                } catch (Exception e) { // TODO Auto-generated catch block
                    Log.d("err",e+"");
                    //Toast.makeText(getApplicationContext(), "e" + e, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}

