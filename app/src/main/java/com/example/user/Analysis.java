package com.example.user;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class Analysis extends Activity {
	
ListView l;

ArrayList<String> id,fname,fsz,ftype,conbtry,nw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offload);
        
        l=(ListView)findViewById(R.id.list2);
        
        id=new ArrayList<String>();
        fname=new ArrayList<String>();
       // fid=new ArrayList<String>();
        fsz=new ArrayList<String>();
        ftype=new ArrayList<String>();
      //  con=new ArrayList<String>();
        nw=new ArrayList<String>();
        conbtry=new ArrayList<String>();
      //  conversion=new ArrayList<String>();
        
        try
        {
        	SQLiteDatabase sqdb=openOrCreateDatabase("taskoff", SQLiteDatabase.CREATE_IF_NECESSARY, null);
			String qry="create table if not exists uploaddb1(id integer PRIMARY KEY AUTOINCREMENT,uid text,filename text,fsize text,ftype text,conbtry text,nw text)";
			sqdb.execSQL(qry);
			
			String q="select * from uploaddb1";
			Cursor cr=sqdb.rawQuery(q, null);
			if(cr.getCount()>0){
				cr.moveToFirst();
				do{
					id.add(cr.getString(0));
					fname.add(cr.getString(2));
					fsz.add(cr.getString(3));
					ftype.add(cr.getString(4));
					conbtry.add(cr.getString(5));
					nw.add(cr.getString(6));
				//	conversion.add(cr.getString(7));
					
					
				}while(cr.moveToNext());
				
			//	Toast.makeText(getApplicationContext(), id.size()+""+"\n"+fname.size()+""+"\n"+fsz+""+"\n"+ftype.size()+"\n"+conbtry.size()+"\n"+nw.size()+"\n"+conversion.size()+"", Toast.LENGTH_LONG).show();

				l.setAdapter(new Custom(getApplicationContext(), fname, fsz, conbtry, nw));
			}
			cr.close();
			sqdb.close();
			
        }catch(Exception e)
        {
        	Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
			
        }
        
        
    }

}
