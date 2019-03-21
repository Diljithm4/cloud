package com.example.user;

import java.net.URISyntaxException;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class FileUtils {
	
	public static String getPath(Context context, Uri uri) throws URISyntaxException {
	    if ("content".equalsIgnoreCase(uri.getScheme())) {   //if selected item is not a file getScheme() works
	        String[] projection = { "_data" };   //_data contains a string within that the path is contained
	        Cursor cursor = null;  //cursor is datatype to store a format(table) data

	        try {
	            cursor = context.getContentResolver().query(uri, projection, null, null, null);
	            int column_index = cursor.getColumnIndexOrThrow("_data"); //column_index contains the path. it is get by  the getColumnIndex
	            if (cursor.moveToFirst()) {
	                return cursor.getString(column_index);
	            }
	        } catch (Exception e) {
	            
	        }
	    }
	    else if ("file".equalsIgnoreCase(uri.getScheme())) {
	        return uri.getPath();
	    }

	    return null;
	} 

}
