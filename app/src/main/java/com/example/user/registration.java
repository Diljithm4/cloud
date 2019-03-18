package com.example.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {

    EditText e1, e2, e3, e4;
    Button b1;
    JSONParser jsonParser = new JSONParser();
   // SharedPreferences sh;
    public static String ur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        e1 = (EditText) findViewById(R.id.editText3);
        e2 = (EditText) findViewById(R.id.editText13);
        e3 = (EditText) findViewById(R.id.editText21);
        e4 = (EditText) findViewById(R.id.editText22);
        b1 = (Button) findViewById(R.id.button);
        ur="http://192.168.137.111:5000/webservice.py";
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
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "e111" + e1, Toast.LENGTH_LONG).show();
                }
                String s = null;

                try {

                    String ss = jobj.getString("task");

                    if (ss.equals("invalid")) {


                    } else {


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
