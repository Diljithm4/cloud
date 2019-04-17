package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class applist extends Activity  {

    ArrayList<String> packageList;
    ArrayList<String> versionName;
    ArrayList<String> installedDate;
    ArrayList<PackageInfo> pkgeList;
    EditText search;
    String app;
    SearchView s1;
    public static   ArrayList<Drawable> drawables;
    ArrayList<String> packageList1;
    ArrayList<String> versionName1;
    ArrayList<String> installedDate1;
    ArrayList<PackageInfo> pkgeList1;
    public static   ArrayList<Drawable> drawables1;

    ListView l2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);
        //s1=(SearchView)findViewById(R.id.searchView1);
//        search=(EditText)findViewById(R.id.editText);
//        search.addTextChangedListener(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            packageList = new ArrayList<String>();
            versionName = new ArrayList<String>();
            installedDate = new ArrayList<String>();
            pkgeList = new ArrayList<PackageInfo>();
            drawables = new ArrayList<Drawable>();

            packageList1 = new ArrayList<String>();
            versionName1 = new ArrayList<String>();
            installedDate1 = new ArrayList<String>();
            pkgeList1 = new ArrayList<PackageInfo>();
            drawables1 = new ArrayList<Drawable>();

            l2=(ListView)findViewById(R.id.list2);

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

                } catch (PackageManager.NameNotFoundException e) {

                }

                if (packageInfo != null) {


                    if (!((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1)) {
                        String a = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                        // Toast.makeText(getApplicationContext(),"apssssss---"+ a, Toast.LENGTH_LONG).show();

                        // if(a==app)
                        {
                            packageList.add(a);

                            pkgeList.add(packageInfo);
                            drawables.add(packageInfo.applicationInfo.loadIcon(getPackageManager()));
                            versionName.add(packageInfo.versionName);
                            installedDate.add(packageInfo.lastUpdateTime + "");
                        }

                    }


                }
            }




            l2.setAdapter(new Custom_checking_app(getApplicationContext(),packageList,versionName,installedDate,drawables,pkgeList));

        }



        s1.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String arg0) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                // TODO Auto-generated method stub
                app=arg0;
                //Toast.makeText(getApplicationContext(), arg0, Toast.LENGTH_LONG).show();

                return true;
            }
        });



    }



//    @Override
//    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//        String k=search.getText().toString();
//
//        for(int j=0;j<packageList.size();j++)
//        {   String as=packageList.get(j);
//            Toast.makeText(getApplicationContext(),as,Toast.LENGTH_LONG).show();
//
//            String v=versionName.get(j);
//            String in=installedDate.get(j);
//
//
//
//            if(as.contains(k))
//            {
//                packageList1.add(as);
//                versionName1.add(v);
//                installedDate1.add(in);
//                pkgeList1.add(pkgeList.get(j));
//                drawables1.add(drawables.get(j));
//            }
//        }
//        gv.setAdapter(new Custom_cheking_app(getApplicationContext(),packageList1,versionName1,installedDate1,drawables1,pkgeList1));
//    }
}
