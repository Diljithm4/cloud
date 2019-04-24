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

public class Custom_apps extends BaseAdapter {

	private Context Context;
	ArrayList<String> c;
	ArrayList<String> d;
	ArrayList<String> e;
	ArrayList<String> f;
	ArrayList<String> g;
	ArrayList<Drawable> h;

	public Custom_apps(Context applicationContext, ArrayList<String> c, ArrayList<String> d, ArrayList<String> e, ArrayList<String> f, ArrayList<String> g, ArrayList<Drawable> h) {

		this.Context=applicationContext;
		this.c=c;
		this.d=d;
		this.e=e;
		this.f=f;
		this.g=g;
		this.h=h;
		
	}

	@Override
	public int getCount() {
		
		return d.size();
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

		
		LayoutInflater inflator=(LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View gridView;
		if(convertview==null)
		{
			gridView=new View(Context);
			gridView=inflator.inflate(R.layout.custom_apps, null);
			
		}
		else
		{
			gridView=(View)convertview;
			
		}
		
		TextView tv1=(TextView)gridView.findViewById(R.id.textView1);
		TextView tv2=(TextView)gridView.findViewById(R.id.textView2);
		TextView tv4=(TextView)gridView.findViewById(R.id.textView4);
		ImageView img=(ImageView)gridView.findViewById(R.id.imageView1);
		tv1.setTextColor(Color.BLACK);
		tv2.setTextColor(Color.BLACK);
		tv4.setTextColor(Color.BLACK);
		
		img.setImageDrawable(h.get(position));
		tv1.setText("App:   "+g.get(position));		
		tv2.setText("Ver:   "+c.get(position));
		tv4.setText("Date: "+d.get(position));

		return gridView;
	}


	
}
