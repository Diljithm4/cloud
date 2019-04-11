package com.example.user;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class video_play extends AppCompatActivity {
    ListView L2;
    SharedPreferences sp;
    String url="";
    JSONParser parser=new JSONParser();
    ArrayList<String> vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        L2 = (ListView) findViewById(R.id.list);
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        try {
            url = "http://"+ sp.getString("ip", "") +"/image_view";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id",sp.getString("id","")));;

            JSONArray ar = null;
            try {
                ar = (JSONArray) parser.makeHttpRequest(url, "GET", params);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("11111111", ar + "");
            vid = new ArrayList<String>();
            for (int i = 0; i < ar.length(); i++) {
                JSONObject js = ar.getJSONObject(i);
                vid.add(js.getString("file_name"));
                // L1.setAdapter(new Custom(getApplicationContext(),img));

            }
            L2.setAdapter(new video_custom(getApplicationContext(),vid));


        }
        catch (Exception e)
        {

        }
    }
}
