//package com.example.user;
//
//public class offload {
//}
package com.example.user;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class offload extends Activity {
    Button b1,b2;
    EditText ed1;
    String namespace="urn:demo";
    String method="filetable";
    String url="http://169.254.175.90/encst/webservice2.php?wsdl";
    String soapAction="urn:demo/filetable";
    long BeforeTime=0,TotalTxBeforeTest=0;
    String path,atype,attach,id,sz="0",fname,uid;


    private String UPLOAD_URL ="api/api_upload.php";
    private String KEY_IMAGE = "image";
    private String KEY_ID = "uid";
    private String KEY_ID1 = "uid";

    byte[] byteArray = null;

    private static final int FILE_SELECT_CODE = 0;

    String method1="uptable";
    String soapAction1="urn:demo/uptable";
    String method2="uptable1";
    String soapAction2="urn:demo/uptable1";
    String method3="cfile";
    String soapAction3="urn:demo/cfile";
    Uri uri=null;
    ////////////////////
    float startbtry,endbtry,consumedbtry,frstsz,scndsz;
    String nw,upldspd,dwnspd,ress;
    String con;
    String cdate="";
    ArrayList<Double> btry=new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offload);
        b1=(Button)findViewById(R.id.offload1);
        ed1=(EditText)findViewById(R.id.edit1);
        b2=(Button)findViewById(R.id.browse1);

        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        uid=sh.getString("id", "");

//        con=Home.con;
//
//
//        nw=Home.nw;
        //SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip", "");

        url="http://"+ip+"/encst/webservice2.php?wsdl";
        UPLOAD_URL="http://"+ip+"/encst/api_upload.php";

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //uploadfile();

                new Loads().execute();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String a=ed1.getText().toString();
                startbtry=getbattery();

                showfilechooser();
            }
        });
    }

    protected String uploadfile()
    {
        String res="";
        try
        {
            String avrgenrgy=getavgenrgy();
            BeforeTime = System.currentTimeMillis();
            TotalTxBeforeTest = TrafficStats.getTotalTxBytes();
            long TotalRxBeforeTest = TrafficStats.getTotalRxBytes();
            Date date;
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            cdate = df.format(new Date());
            KEY_ID=cdate+"."+atype;

            sendtoserver();
            int leng=byteArray.length;

            if(leng>128000)
            {
                int cn=(int)leng/128000;
                int x=0,y=0;
                for(int i=0;i<cn;i++)
                {
                    byte[] bs=new byte[128000];

                    x=i*128000;
                    y=(i+1)*128000;
                    int k=0;
                    for(int j=x;j<y;j++)
                    {
                        bs[k]=byteArray[j];
                        k++;
                    }
                    String str = Base64.encodeToString(bs, Base64.DEFAULT);
                    sendtoserverbytearray(str);
                }
            }
            else{
                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                sendtoserverbytearray(str);
            }

            /////////////////////////////////////////////////////////////
//            try
//            {
//                SoapObject sop=new SoapObject(namespace, method3);
//                sop.addProperty("fname",KEY_ID);
//
//                SoapSerializationEnvelope snv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                snv.setOutputSoapObject(sop);
//
//                HttpTransportSE hp=new HttpTransportSE(url);
//                hp.call(soapAction3, snv);
//
//                String result=snv.getResponse().toString();
////		        		Log.d("", result+"--");
//                if(result.equalsIgnoreCase("ok"))
//                {
//                    endbtry=getbattery();
////Log.d("BATTERIESSSS----", startbtry+"  "+endbtry+"  ");
//                    consumedbtry=Math.abs(endbtry-startbtry);
//
//                    long TotalTxAfterTest = TrafficStats.getTotalTxBytes();
////	long TotalRxAfterTest = TrafficStats.getTotalRxBytes();
//                    long AfterTime = System.currentTimeMillis();
//                    double TimeDifference = AfterTime - BeforeTime;
//                    Log.d("TimeDifference--", TimeDifference+"--- ");
////double rxDiff = TotalRxAfterTest - TotalRxBeforeTest;
//                    double txDiff = TotalTxAfterTest - TotalTxBeforeTest;
//                    Log.d("txDiff--", txDiff+"--- ");
//
//                    if((txDiff != 0)) {
//                        // double rxBPS = (rxDiff / (TimeDifference/1000)); // total rx bytes per second.
//                        double txBPS = (txDiff / (TimeDifference/1000)); // total tx bytes per second.
//                        Log.d("txDiff-timediffffff/1000--", txDiff+"--"+TimeDifference+"--");
//                        Log.d("txBPS--", txBPS+"====--- ");
//                        //  dwnspd = String.valueOf(rxBPS) + "B/s. Total rx = " + rxDiff;
//                        upldspd= String.valueOf(txBPS) + "B/s. Total tx = " + txDiff;
//
//                    }else {
//                        // dwnspd = "No uploaded or downloaded bytes.";
//                    }
//                    fname=cdate+"."+atype;
//                    res=savedetails(fname,sz,atype,consumedbtry+"",nw,"upload",upldspd);
//
//                }
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
            //////////////////////////////
        } catch (Exception e11) {
        }

        return res;

    }

    private void showfilechooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //getting all types of files
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"),FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.",Toast.LENGTH_SHORT).show();
        }

    }


    public String getRealPathFromURI(Uri contentUri)
    {
        String[] proj = { MediaStore.Audio.Media.DATA };
        Cursor cursor =  getContentResolver().query(contentUri, null, null, null, null);
        // managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode)
        {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK)
                {
                    // Get the Uri of the selected file
                    uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    //String path = null;
                    try
                    {
                        path = getRealPathFromURI(uri);//FileUtils.getPath(this, uri);
                        Toast.makeText(this,"File Name & PATH are:"+path, Toast.LENGTH_LONG).show();

                        fname="";
                        ed1.setText(path);

                        File fil = new File(path);
                        float fln=(float) (fil.length()/1024);
                        atype=path.substring(path.lastIndexOf(".")+1);
                        sz=fln+"";
                        //ed2.setText(sz+"");
                    }
                    catch (Exception e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

//
//			           // send image to server
//
                    File file = new File(path);
                    int ln=(int) file.length();
                    //  byte[] byteArray = null;
                    try
                    {
                        InputStream inputStream = new FileInputStream(file);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] b = new byte[ln];
                        int bytesRead =0;

                        while ((bytesRead = inputStream.read(b)) != -1)
                        {
                            bos.write(b, 0, bytesRead);
                        }

                        byteArray = bos.toByteArray();
                    }
                    catch (IOException e)
                    {
                        Toast.makeText(this,"String :"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                    String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    attach=str;
                }
        }




    }
    private String savedetails(String fname, String sz, String atype, String conbtry, String nw, String string, String speed) {
        String res="";
//        try
//        {
//            SoapObject sop=new SoapObject(namespace, method1);
//            sop.addProperty("fname",fname);
//            sop.addProperty("sz",sz);
//            sop.addProperty("atype",atype);
//            sop.addProperty("conbtry",conbtry);
//            sop.addProperty("nw",nw);
//            sop.addProperty("dvc",uid );
//            sop.addProperty("ftype",string );
//            sop.addProperty("model",android.os.Build.BRAND+"-"+ android.os.Build.MODEL);
//            sop.addProperty("place",LocationService.place);
//            sop.addProperty("speed",speed);
//
//            SoapSerializationEnvelope snv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            snv.setOutputSoapObject(sop);
//
//            HttpTransportSE hp=new HttpTransportSE(url);
//            hp.call(soapAction1, snv);
//
//            res=snv.getResponse().toString();
//
//            if(!res.equalsIgnoreCase("Not Exist")){
//            }
//        }
//        catch(Exception e){
//
//        }
//
//        try{
//
//            SQLiteDatabase sqdb=openOrCreateDatabase("taskoff", SQLiteDatabase.CREATE_IF_NECESSARY, null);
//            String qry="create table if not exists uploaddb1(id integer PRIMARY KEY AUTOINCREMENT,uid text,filename text,fsize text,ftype text,conbtry text,nw text)";
//            sqdb.execSQL(qry);
//
//            ContentValues cv=new ContentValues();
//
//            cv.put("uid", uid);
//            cv.put("filename",fname);
//            cv.put("fsize", sz);
//            cv.put("ftype", atype);
//            cv.put("conbtry", conbtry);
//            cv.put("nw", nw);
//
//            sqdb.insert("uploaddb1", null, cv);
//
//            sqdb.close();
//
//        }catch(Exception e){
//
//        }

        return res;
    }
    public float getbattery() {
        Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        // Error checking that probably isn't needed but I added just in case.
        if(level == -1 || scale == -1) {
            return 50.0f;
        }

//    return ((float)level / (float)scale) * 100.0f;
        return ((float)level / (float)scale) ;
    }

    private String getavgenrgy() {

        double avgenr=0.0;
        String sz1=String.valueOf(frstsz);
        String sz2=String.valueOf(scndsz);

        Double av=0.0;

//        try{
//            //	Toast.makeText(getApplicationContext(), "inside avg energy", Toast.LENGTH_SHORT).show();
//
//            SQLiteDatabase sqdb=openOrCreateDatabase("taskoff", SQLiteDatabase.CREATE_IF_NECESSARY, null);
//            String qry="create table if not exists uploaddb1(id integer PRIMARY KEY AUTOINCREMENT,uid text,filename text,fsize text,ftype text,conbtry text,nw text)";
//            sqdb.execSQL(qry);
//
//            String qry1="SELECT conbtry FROM uploaddb1 WHERE nw='"+nw+"'";
//
//            Cursor cr=sqdb.rawQuery(qry1, null);
//            //Toast.makeText(getApplicationContext(), cr.getCount()+" count", Toast.LENGTH_SHORT).show();
//            if(cr.getCount()>0){
//                cr.moveToFirst();
//                do{
//
//                    btry.add(Double.parseDouble(cr.getString(0)));
//
//                }while(cr.moveToNext());
//            }
//
//            cr.close();
//            sqdb.close();
//
//            //	Toast.makeText(getApplicationContext(), btry.size()+" arraylist size",Toast.LENGTH_SHORT).show();
//
//            for(int i=0;i<btry.size();i++){
//
//                av+=btry.get(i);
//
//            }
//            avgenr=av/btry.size();
//
//        }catch(Exception e){
//
//            //Toast.makeText(getApplicationContext(), "ERRR "+e.getMessage(), Toast.LENGTH_LONG).show();
//        }
        return avgenr+"";
    }

    protected void sendtoserver() {
//        try
//        {
//            SoapObject sop=new SoapObject(namespace, method);
//            sop.addProperty("uid", uid);
//            sop.addProperty("ftyp", atype);
//            sop.addProperty("fsize", sz);
//            sop.addProperty("fname", cdate);
//
//            SoapSerializationEnvelope snv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            snv.setOutputSoapObject(sop);
//            HttpTransportSE hp=new HttpTransportSE(url);
//            hp.call(soapAction, snv);
//
//            String result=snv.getResponse().toString();
//
//            if(result.equalsIgnoreCase("uploaded"))
//            {
////        	   endbtry=getbattery();
////				Log.d("BATTERIESSSS", startbtry+"  "+endbtry+"  ");
////				consumedbtry=Math.abs(endbtry-startbtry);
////				long TotalTxAfterTest = TrafficStats.getTotalTxBytes();
////			//	long TotalRxAfterTest = TrafficStats.getTotalRxBytes();
////				long AfterTime = System.currentTimeMillis();
////				double TimeDifference = AfterTime - BeforeTime;
////				Log.d("TimeDifference--", TimeDifference+"--- ");
////				//double rxDiff = TotalRxAfterTest - TotalRxBeforeTest;
////				double txDiff = TotalTxAfterTest - TotalTxBeforeTest;
////				Log.d("txDiff--", txDiff+"--- ");
////
////				if((txDiff != 0)) {
////				   // double rxBPS = (rxDiff / (TimeDifference/1000)); // total rx bytes per second.
////				    double txBPS = (txDiff / (TimeDifference/1000)); // total tx bytes per second.
////				    Log.d("txDiff-timediffffff/1000--", txDiff+"--"+TimeDifference+"--");
////				    Log.d("txBPS--", txBPS+"====--- ");
////					//  dwnspd = String.valueOf(rxBPS) + "B/s. Total rx = " + rxDiff;
////				    upldspd= String.valueOf(txBPS) + "B/s. Total tx = " + txDiff;
////				    Toast.makeText(getApplicationContext(), "uploading speed : "+upldspd,Toast.LENGTH_SHORT).show();
////				}else {
////				  // dwnspd = "No uploaded or downloaded bytes.";
////			}
////			fname=cdate+"."+atype;
////			savedetails(fname,sz,atype,consumedbtry+"",nw,"upload");
////			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
//            }
//            else {
//            }
//        }
//        catch (Exception e) {
//
//        }
    }

    public void sendtoserverbytearray(String data) {
//        try
//        {
//            SoapObject sop=new SoapObject(namespace, method2);
//
////	Log.d("------bytarray-----", data);
////	Log.d("------fname-----", cdate+"."+atype);
//            sop.addProperty("bytarray", data);
//            sop.addProperty("fname", cdate+"."+atype);
//
//            SoapSerializationEnvelope snv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            snv.setOutputSoapObject(sop);
//
//            HttpTransportSE hp=new HttpTransportSE(url);
//            hp.call(soapAction2, snv);
//
//            String result=snv.getResponse().toString();
//            Log.d("------1-----", result);
//            Thread.sleep(2000);
//            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
//            if(result.equalsIgnoreCase("uploaded")){
////
//            }
//            else{}
//        }
//        catch (Exception e) {
//            // TODO: handle exception
//            Log.d("----error------", e.toString());
//            //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//        }

    }

    public ProgressDialog pd;
    private class Loads extends AsyncTask<String, Void, String>
    {
        int lstinx;
        public Loads() {
        }
        protected void onPreExecute() {
            pd=new ProgressDialog(offload.this);
            pd.setTitle("ENERGY COST MODEL");
            pd.setMessage("Uploading....");
            pd.show();
        }
        protected String doInBackground(String... urls) {
            String rs= uploadfile();
            return rs;
        }
        protected void onCancelled() {
        }
        protected void onPostExecute(String res) {
            if(res.equalsIgnoreCase("ok"))
            {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Not Uploaded.Try Again..!", Toast.LENGTH_LONG).show();
            }

            pd.dismiss();
        }
    }

}
