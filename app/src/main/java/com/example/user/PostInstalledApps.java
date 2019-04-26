package com.example.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostInstalledApps extends AppCompatActivity implements View.OnClickListener {
    TextView tvname,tvpname,tvvname,tvvcode,tvrate,tvcat;
    ImageView img;
    Button bCheck,btnrvw;
    JSONObject jsonObject;
    String pname="",appname="",vname="",category="";
    String ip;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edt;

    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_installed_apps);
        tvname = (TextView) findViewById(R.id.tvname);
        tvpname = (TextView) findViewById(R.id.tvpname);
        tvvname=(TextView) findViewById(R.id.tvvname);
        //tvvcode= (TextView) findViewById(R.id.tvvcode);
        tvrate = (TextView) findViewById(R.id.tvrate);
        tvcat = (TextView) findViewById(R.id.tvcat);
        bCheck= (Button) findViewById(R.id.button);
        btnrvw=(Button)findViewById(R.id.rvwbtn);
        img=(ImageView)findViewById(R.id.icon);

        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}



        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(PostInstalledApps.this);
        edt=sharedPreferences.edit();
        ip=sharedPreferences.getString("IP","");
        url=sharedPreferences.getString("url","")+"Permission";
        //url=("http://" + ip + ":8084/FairPlay/Permission");
        Intent ing=getIntent();
        appname =ing.getStringExtra("appname");
        pname =ing.getStringExtra("pname");
        edt.putString("pkg",pname);
        vname =ing.getStringExtra("versionname");
        String installedDate =ing.getStringExtra("installedDate");
        category =ing.getStringExtra("category");
        String rating =ing.getStringExtra("rating");
        edt.putString("category",category);
        edt.commit();
        jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        PackageInfo packageInfo=applist.pkgeList.get(applist.pos);
      Drawable icon=applist.drawables.get(applist.pos);
        String[] per=packageInfo.requestedPermissions;

        if(per!=null) {
            for (int i = 0; i < per.length; i++) {
                jsonArray.put(per[i]);
            }
            Log.d("eeeee",per.length+"");
        }
        else{
            Log.d("nnnn","empty");
        }
        try {
            jsonObject.put("per",jsonArray);
            jsonObject.put("pname",pname);

        }catch (Exception e){}


        tvname.setText(appname);
        tvpname.setText("Package: "+pname);
        tvvname.setText("Version: "+vname);
       // tvvcode.setText(versionCode);
        tvrate.setText("Rating: "+rating);
        tvcat.setText("Category: "+category);
        try {
           img.setImageDrawable(icon);

        }catch (Exception e){
            Log.d("errr========",e.toString());
        }
        bCheck.setOnClickListener(this);
        btnrvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent ind=new Intent(getApplicationContext(),Review.class);
               // startActivity(ind);
            }
        });
    }


    @Override
    public void onClick(View v) {

        new CheckService().execute();
    }

    public class CheckService extends AsyncTask<Void,Void,String>{

        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd=new ProgressDialog(PostInstalledApps.this);
            pd.setMessage("Loading");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String res="";
            try {
                List<NameValuePair> pa = new ArrayList<>();
                pa.add(new BasicNameValuePair("obj", jsonObject.toString()));
                JSONParser js = new JSONParser();
                //JSONObject jb = js.makeHttpRequest(url, "GET", pa);
                JSONObject jb= (JSONObject) js.makeHttpRequest(url,"GET",pa);
                res=jb.toString();
            }catch (Exception e){}
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jb=new JSONObject(s);
                String category_result=jb.getString("category_result");
                String rate_result=jb.getString("rate_result");
                String review_result=jb.getString("review_result");
                String flag=jb.getString("flag");
                String cat=jb.getString("category");
                String avgn=jb.getString("avgn");
                String avgp=jb.getString("avgp");
                edt.putString("category_result",category_result);
                edt.putString("review_result",review_result);
                edt.putString("rate_result",rate_result);
                edt.putString("flag",flag);
                edt.putString("avgp",avgp);
                edt.putString("avgn",avgn);
                edt.commit();

                pd.dismiss();
                //Intent in=new Intent(PostInstalledApps.this,MalwareCheck.class);
//                in.putExtra("pname",pname);
//                in.putExtra("appname",appname);
//                in.putExtra("vname",vname);
//                in.putExtra("category",category);
//                in.putExtra("avgp",avgp);
//                in.putExtra("avgn",avgn);


               // startActivity(in);
            }catch (Exception e){}
        }
    }
}
