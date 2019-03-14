package com.example.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class registration extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText13);
        e3=(EditText)findViewById(R.id.editText21);
        e4=(EditText)findViewById(R.id.editText22);
        b1=(Button)findViewById(R.id.button);
    }
}
