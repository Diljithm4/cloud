package com.example.user;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class upload_file extends AppCompatActivity {
    EditText e7;
    Button b4, b5;
    private static final int FILE_SELECT_CODE = 0;
    String path1="";

    String id="",ip="",ur="",caption="";
    JSONParser jsonParser = new JSONParser();
    String path,fileName,attach,type,fname;



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


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        path1 = FileUtils.getPath(this, uri);
                        //e4.setText(path1);
                        Log.d("path", path1);
                        File fil = new File(path1);
                        int fln=(int) fil.length();
                        e7.setText(path1);

//						File file = new File(path);

                        byte[] byteArray = null;
                        try
                        {
                            InputStream inputStream = new FileInputStream(fil);
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            byte[] b = new byte[fln];
                            int bytesRead =0;

                            while ((bytesRead = inputStream.read(b)) != -1)
                            {
                                bos.write(b, 0, bytesRead);
                            }

                            byteArray = bos.toByteArray();
                            inputStream.close();
//				        Bitmap bmp=BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//				        if(bmp!=null){
//
//
//							img1.setVisibility(View.VISIBLE);
//				        	 img1.setImageBitmap(bmp);
//				        }
                        }
                        catch (Exception e) {
                            // TODO: handle exception
                        }
                    }

                    catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(this,"Please select suitable file", Toast.LENGTH_LONG).show();
                }
                break;



        }


    }
    public int uploadFile(String sourceFileUri) {

        fileName = sourceFileUri;
        String upLoadServerUri ="http://"+ip+":5000/Post_img";
        // Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();
        FileUpload fp = new FileUpload(fileName);
        Map mp = new HashMap<String,String>();

        mp.put("id", id);

        fp.multipartRequest(upLoadServerUri, mp, fileName, "files", "application/octet-stream");
        return 1;
    }

    public boolean isVaalidname(String value)
    {
        Pattern ps= Pattern.compile("^[a-zA-Z]+$");
        Matcher ms=ps.matcher(value);
        boolean bs=ms.matches();
        return bs;
    }

    }