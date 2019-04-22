package com.example.fairplay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Viewdetails extends Activity implements View.OnClickListener {

    ImageView img;
    TextView app_name,version,r1,r2,r3,r4,r5,total_review,pos,neg,neutral,pr;
    Button review,ok;
    String vn="";
    String ip="uu",url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetails);
        img=(ImageView)findViewById(R.id.imageView1);
        //rate1=(EditText)findViewById(R.id.editText1);
        //rate2=(EditText)findViewById(R.id.editText2);
        //rate3=(EditText)findViewById(R.id.editText3);
        //rate4=(EditText)findViewById(R.id.editText4);
        //rate5=(EditText)findViewById(R.id.editText5);
        app_name=(TextView)findViewById(R.id.textView21);
        version=(TextView)findViewById(R.id.textView22);
        pr=(TextView)findViewById(R.id.textView1);
        total_review=(TextView)findViewById(R.id.textView2);
        pos=(TextView)findViewById(R.id.textView3);
        neutral=(TextView)findViewById(R.id.textView4);
        neg=(TextView)findViewById(R.id.textView5);
        r1=(TextView)findViewById(R.id.textView6);
        r2=(TextView)findViewById(R.id.textView7);
        r3=(TextView)findViewById(R.id.textView8);
        r4=(TextView)findViewById(R.id.textView9);
        r5=(TextView)findViewById(R.id.textView10);

        review=(Button)findViewById(R.id.button1);
        ok=(Button)findViewById(R.id.button2);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String n=sh.getString("info","");
        final String n1=sh.getString("info1","");
        int im=Integer.parseInt( sh.getString("img",""));
        vn=sh.getString("version_name","");
        final String p=sh.getString("permission","");
        String aaa=sh.getString("m","");
        if(aaa.equalsIgnoreCase("1")) {
            Drawable dr = Viewapps.drawables.get(im);
            img.setImageDrawable(dr);
        }
        else
        {
            Drawable dr = NewApplicationCheck.dr;
            img.setImageDrawable(dr);
        }
        review.setOnClickListener(this);
        ok.setOnClickListener(this);


        app_name.setText(n1);
        version.setText(vn);

        SharedPreferences s=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("ip", "");
        url = ip+"get_reviews";

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {


                            JSONObject js = new JSONObject(response);
                            //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            String status = js.getString("status");
                            if (status.equalsIgnoreCase("ok")) {





                                String f = js.getString("five_str");
                                r5.setText(f);
                                String fr = js.getString("four_str");
                                r4.setText(fr);
                                String th = js.getString("three_str");
                                r3.setText(th);
                                String tw = js.getString("two_str");
                                r2.setText(tw);
                                String on = js.getString("one_str");
                                r1.setText(on);

                                String permission=js.getString("abc");
                                pr.setText(permission);

                                String malware=js.getString("ms");
                                if(malware.equalsIgnoreCase("Yes")==true)
                                    ok.setVisibility(View.VISIBLE);
                                else
                                    ok.setVisibility(View.INVISIBLE);

                                String r=js.getString("reviews");
                                //Toast.makeText(getApplicationContext(),r,Toast.LENGTH_LONG).show();
                                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor e=sharedPreferences.edit();
                                e.putString("rev",r);
                                e.commit();


                                Float fv=Float.parseFloat(f);
                                Float fr1=Float.parseFloat(fr);
                                Float th1=Float.parseFloat(th);
                                Float tw1=Float.parseFloat(tw);
                                Float on1=Float.parseFloat(on);
                                Float sum=fv+fr1+th1+tw1+on1;


                                if(permission.equalsIgnoreCase("0")==true)
                                    ok.setVisibility(View.INVISIBLE);
                                else
                                    ok.setVisibility(View.VISIBLE);




                                // ConstraintLayout.LayoutParams.

//                                int f1=(int)(((fv)/(sum))*100) ;
//                                if(f1<50)
//                                {
//                                    f1 = f1 * 20;
//                                }
//                                LinearLayout.LayoutParams a1=(LinearLayout.LayoutParams)rate1.getLayoutParams();
//                                //Toast.makeText(getApplicationContext(),f1+"",Toast.LENGTH_LONG).show();
//                                a1.width=f1;
//                                a1.height=50;
//                                //rate1.setBackgroundColor(Color.BLUE);
//                                //rate1.setText(f1+"");
//                               // rate1.setEnabled(false);
//
//
//                                int f2=(int)(((fr1)/(sum))*100) ;
//                                if(f2<50)
//                                {
//                                    f2 = f2 * 20;
//                                }
//                                LinearLayout.LayoutParams a2=(LinearLayout.LayoutParams)rate2.getLayoutParams();
//                                //Toast.makeText(getApplicationContext(),f2+"",Toast.LENGTH_LONG).show();
//                                a2.width=f2;
//                                a2.height=50;
//                                //rate2.setBackgroundColor(Color.RED);
//                                //rate2.setEnabled(false);
//
//
//                                int f3=(int)(((th1)/(sum))*100) ;
//                                if(f3<50)
//                                {
//                                    f3 = f3 * 20;
//                                }
//                                LinearLayout.LayoutParams a3=(LinearLayout.LayoutParams)rate3.getLayoutParams();
//                                //Toast.makeText(getApplicationContext(),f3+"",Toast.LENGTH_LONG).show();
//                                a3.width=f3;
//                                a3.height=50;
//                                //rate3.setBackgroundColor(Color.GRAY);
//                                rate3.setEnabled(false);
//
//                                int f4=(int)(((tw1)/(sum))*100) ;
//                                if(f4<50)
//                                {
//                                    f4 = f4 * 20;
//                                }
//                                LinearLayout.LayoutParams a4=(LinearLayout.LayoutParams)rate4.getLayoutParams();
//                                //Toast.makeText(getApplicationContext(),f4+"",Toast.LENGTH_LONG).show();
//                                a4.width=f4;
//                                a4.height=50;
//                                //rate4.setBackgroundColor(Color.YELLOW);
//                                rate4.setEnabled(false);
//
//
//                                int f5=(int)(((on1)/(sum))*100) ;
//                                if(f5<50)
//                                {
//                                    f5 = f5 * 20;
//                                }
//                                LinearLayout.LayoutParams a5=(LinearLayout.LayoutParams)rate5.getLayoutParams();
//                                //Toast.makeText(getApplicationContext(),f5+"",Toast.LENGTH_LONG).show();
//                                a5.width=f5;
//                                a5.height=50;
//                                //rate5.setBackgroundColor(Color.BLACK);
//                                rate5.setEnabled(false);





                                String tr = js.getString("total");
                                total_review.setText(tr);

                                String p = js.getString("pos");
                                pos.setText(p);
                                String n = js.getString("neg");
                                neg.setText(n);
                                String nt = js.getString("neu");
                                neutral.setText(nt);

                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to get app details", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception ex) {

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("package_name", n);
                params.put("permission",p);



                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(postRequest);
    }

    @Override
    public void onClick(View view) {
        if(view==review) {
            Intent i = new Intent(getApplicationContext(), Reviews.class);
            startActivity(i);
        }
        if(view==ok)
        {
            SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String app=sh.getString("info","");
            Toast.makeText(getApplicationContext(),app,Toast.LENGTH_LONG).show();
            Uri packageURI = Uri.parse("package:"+app);
            Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageURI);
            startActivity(uninstallIntent);
            Intent p=new Intent(getApplicationContext(),Viewapps.class);
            startActivity(p);
        }
    }
}
