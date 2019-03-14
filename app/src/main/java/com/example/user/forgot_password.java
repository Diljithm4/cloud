package com.example.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class forgot_password extends AppCompatActivity {
    EditText e7,e8;
    Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        b4=(Button)findViewById(R.id.button6);
        e7=(EditText)findViewById(R.id.editText5);
        e8=(EditText)findViewById(R.id.editText6);
    }
}
