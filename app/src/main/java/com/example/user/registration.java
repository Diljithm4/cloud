package com.example.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class registration extends AppCompatActivity {

    EditText e1, e2, e3, e4;
    Button b1;
    String ip="";
    SharedPreferences sh;
    JSONParser jsonParser = new JSONParser();
   // SharedPreferences sh;
    public static String ur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        e1 = (EditText) findViewById(R.id.editText3);
        e2 = (EditText) findViewById(R.id.editText13);
        e3 = (EditText) findViewById(R.id.editText);
        e4 = (EditText) findViewById(R.id.editText22);
        b1 = (Button) findViewById(R.id.button);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ip=sh.getString("ip","");
        ur="http://"+ip+"/registration";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e1.getText().toString();
                String mail = e2.getText().toString();
                String password = e3.getText().toString();
                String phone = e4.getText().toString();

                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("email", mail));
                params.add(new BasicNameValuePair("pass", password));
                params.add(new BasicNameValuePair("phone", phone));


                JSONObject jobj = null;
                try {
                    jobj = (JSONObject) jsonParser.makeHttpRequest(ur, "GET", params);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "e111" + e, Toast.LENGTH_LONG).show();
                }

                try {

                    String ss = jobj.getString("task");

                    Toast.makeText(getApplicationContext(), ""+ss , Toast.LENGTH_LONG).show();


                    if (ss.equals("failed")) {


                    } else {

                        Toast.makeText(getApplicationContext(),"Successfully Registerd ",Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);

                    }


                } catch (Exception e) { // TODO Auto-generated catch block

                    Toast.makeText(getApplicationContext(), "e       " + e1, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
