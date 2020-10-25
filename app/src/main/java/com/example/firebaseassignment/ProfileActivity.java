package com.example.firebaseassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    TextView nametext,emailtext,mobiletext,rolltext;
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nametext=findViewById(R.id.tvname);
        rolltext=findViewById(R.id.tvroll);
        emailtext=findViewById(R.id.tvemail);
        mobiletext=findViewById(R.id.tvphone);
        //Show data
        Intent intent=getIntent();
        String user_name=intent.getStringExtra("sname");
        String user_roll=intent.getStringExtra("sroll");
        String user_email=intent.getStringExtra("semail");
        String user_phone=intent.getStringExtra("sphone");

        nametext.setText(user_name);
        emailtext.setText(user_email);
        mobiletext.setText(user_phone);
        rolltext.setText(user_roll);

    }


    public void Update(View view) {
        
    }
}