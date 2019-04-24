package com.example.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class applist extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;

    ArrayList<String> packgeList;
    ArrayList<String> versionName;
    ArrayList<String> installedDate,pname;
    public static ArrayList<PackageInfo> pkgeList;
    public static ArrayList<Drawable> drawables;
    public static  int pos=0;

    public static String apname="";
    public static String packg="",vername="";
    public static int vercode;
    public static Drawable ic;
    SharedPreferences sh;
    //WriteApk wrt;
    public static ArrayList<PackageInfo> packageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installedapps);
        //wrt=new WriteApk();

        lv=(ListView)findViewById(R.id.lv);
        packgeList=new ArrayList<String>();
        versionName=new ArrayList<String>();
        installedDate=new ArrayList<String>();
        pkgeList=new ArrayList<PackageInfo>();
        drawables=new ArrayList<Drawable>();
        pname=new ArrayList<>();
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        new ReadNewApplications().execute();

        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        pos=position;
        String category="",rating="";
        try {
            String url=sh.getString("url","")+"InstalledApps";
            //String url="http://192.168.43.41:8084/FairPlay/InstalledApps";
            List<NameValuePair> params=new ArrayList<>();
            params.add(new BasicNameValuePair("appname",packgeList.get(position)));
            params.add(new BasicNameValuePair("pname",pname.get(position)));
            params.add(new BasicNameValuePair("versionname", versionName.get(position)));
            JSONParser js=new JSONParser();
            JSONObject jb= (JSONObject) js.makeHttpRequest(url,"GET",params);
            category=jb.getString("category");
            rating=jb.getString("rating");
            // wrt.makeFile(pkgeList.get(position),getApplicationContext());


        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent in=new Intent(applist.this,viewdetails.class);
        in.putExtra("appname",packgeList.get(position));
        in.putExtra("pname",pname.get(position));
        in.putExtra("versionname", versionName.get(position));
        in.putExtra("installedDate",installedDate.get(position));

        in.putExtra("pos",position+"");
        in.putExtra("category",category);
        in.putExtra("rating",rating);
        startActivity(in);

    }

    private class ReadNewApplications extends AsyncTask<Void, Void, String> {

        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd=new ProgressDialog(applist.this);
            pd.setTitle("FairPlay");
            pd.setMessage("Searching for applications...");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            int flags = PackageManager.GET_META_DATA |
                    PackageManager.GET_SHARED_LIBRARY_FILES |
                    PackageManager.GET_UNINSTALLED_PACKAGES;
            final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            final List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, flags);

            for (ResolveInfo resolveInfo : pkgAppsList) {

                PackageInfo packageInfo = null;
                try {

                    packageInfo = getPackageManager().getPackageInfo(resolveInfo.activityInfo.packageName, PackageManager.GET_PERMISSIONS);

                } catch (PackageManager.NameNotFoundException e) {}

                if(packageInfo!=null){

                    if (!((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1)) {
                        String a = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                        if (!packgeList.contains(a)) {
                            packgeList.add(a);
                            pkgeList.add(packageInfo);
                            pname.add(packageInfo.packageName);
                            drawables.add(packageInfo.applicationInfo.loadIcon(getPackageManager()));
                            versionName.add(packageInfo.versionName);
                            installedDate.add(setDateFormat(packageInfo.lastUpdateTime));
                        }
                    }
                }
            }
            if(pkgAppsList.size()>0){
                return "ok";
            }
            else{
                return "no";
            }
        }

        private String setDateFormat(long time) {
            Date date = new Date(time);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String strDate = formatter.format(date);
            return strDate;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pd.dismiss();
            if(result.equals("ok")){
                lv.setAdapter(new Custom_apps(getApplicationContext(), versionName, installedDate, packgeList, packgeList, packgeList, drawables));
            }
            else{
                Toast.makeText(applist.this, "No application available", Toast.LENGTH_LONG).show();
            }
        }
    }

}
