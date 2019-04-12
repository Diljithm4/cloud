package com.example.user;


import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class file_custom extends BaseAdapter{

    private Context context;


    ArrayList<String> c;
    SharedPreferences sp;


    public file_custom(Context applicationContext,ArrayList<String> file) {
        this.context=applicationContext;
        //this.a=x;
        this.c=file;

    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertView==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_image_custom, null);

        }
        else
        {
            gridView=(View)convertView;

        }


        ImageView img=(ImageView)gridView.findViewById(R.id.imageView2);

        sp=PreferenceManager.getDefaultSharedPreferences(context);
        java.net.URL thumb_u;
        try {
            String url="http://"+sp.getString("ip","")+"/static/file_upload/"+c.get(position);

            Toast.makeText(context, url, Toast.LENGTH_LONG).show();

            thumb_u = new java.net.URL("http://"+sp.getString("ip","")+"/static/file_upload/"+c.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            img.setImageDrawable(thumb_d);


        }
        catch(Exception e){

        }

//		java.net.URL thumb_u;
//		try {
////
//
//
//			String urll="http://"+Ipsettings.ip+"//women%20security//upload//"+b[position];
//			Picasso.with(Context)
//		    .load(urll)
//		    //.transform(new Circulartransform())
//		    .error(R.drawable.c)
//		    .into(img);
//
//
//
//		}
//		catch(Exception e){
//
//		}
//



        return gridView;

    }



}
