package com.example.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class upload_file extends AppCompatActivity {
    EditText e7;
    Button b4, b5;
    private static final int FILE_SELECT_CODE = 0;
    String path1="";
    //String id="",ip="",ur="",caption="";
    //String path,fileName,attach,type,fname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);


            e7 = (EditText) findViewById(R.id.editText2);
            b4 = (Button) findViewById(R.id.button5);
            b5 = (Button) findViewById(R.id.button7);
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //getting all types of files
                    intent.setType("*/*");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);

                    try {
                        startActivityForResult(Intent.createChooser(intent, ""), FILE_SELECT_CODE);
                    } catch (android.content.ActivityNotFoundException ex) {

                        Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                    }

                }

            });
            b5.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub


                    int res = uploadFile(path1);
                    if (res == 1) {
                        Toast.makeText(getApplicationContext(), " uploaded", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), cloud_home_new.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), " error", Toast.LENGTH_LONG).show();
                    }
                }


            });
        }


    }}