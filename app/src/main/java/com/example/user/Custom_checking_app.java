package com.example.user;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;



import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by HP on 23-06-2018.
 */

public class Custom_checking_app extends BaseAdapter implements OnClickListener {

    private Context Context;
    ArrayList<String> c;
    ArrayList<String> d;
    ArrayList<String> e;
    ArrayList<Drawable> f;
    ArrayList<PackageInfo> g;
    String a="";



    public Custom_checking_app(Context applicationContext, ArrayList<String> name, ArrayList<String> v_name, ArrayList<String> date, ArrayList<Drawable> img, ArrayList<PackageInfo> info) {

        this.Context=applicationContext;
        this.c=name;
        this.d=v_name;
        this.e=date;
        this.f=img;
        this.g=info;
    }

    @Override
    public int getCount() {

        return c.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {


        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.activity_custom_checking_app, null);

        }
        else
        {
            gridView=(View)convertview;

        }

        TextView tv=(TextView)gridView.findViewById(R.id.appl_id);

        ImageView im=(ImageView)gridView.findViewById(R.id.imageView);
        Button b1=(Button)gridView.findViewById(R.id.check_id);
       // b1.setTag(g.get(position));
        b1.setTag(position);
        im.setImageDrawable(f.get(position));


        b1.setOnClickListener(this);




        tv.setTextColor(Color.BLACK);
        tv.setText(c.get(position));




        return gridView;
    }


    @Override
    public void onClick(View view) {

        int ab=Integer.parseInt(view.getTag().toString());

        PackageInfo pkg_info=g.get(ab);

        a=pkg_info.applicationInfo.packageName;
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(Context);
        Editor ed=sp.edit();
        ed.putString("info1",pkg_info.applicationInfo.loadLabel(Context.getPackageManager()).toString());
        ed.putString("info",pkg_info.applicationInfo.packageName);
        ed.putString("version_name",pkg_info.versionName);
        ed.putString("img",ab+"");

        ed.putString("m","1");

        ed.commit();
        // Toast.makeText(Context,pkg_info.applicationInfo.packageName,Toast.LENGTH_LONG).show();



//        Intent i=new Intent(Context,Viewdetails.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Context.startActivity(i);
    }
}
