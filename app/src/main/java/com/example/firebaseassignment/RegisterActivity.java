package com.example.firebaseassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseassignment.databinding.ActivityRegister2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegister2Binding binding;
    String umail,upass,uroll,uphone,uname;
    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register2);
        //Authentication
        auth=FirebaseAuth.getInstance();
        //Firebase RealTime Database
        database=FirebaseDatabase.getInstance();
        reference= database.getReference("Data");
    }

    public void register(View view) {
        umail=binding.et3.getText().toString();
        upass=binding.et5.getText().toString();
        uroll=binding.et2.getText().toString();
        uphone=binding.et4.getText().toString();
        uname=binding.et1.getText().toString();
        if (umail.isEmpty() | upass.isEmpty()){
            Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();
        }
        else if (upass.length()<6){
            Toast.makeText(this, "Password should be more than 6 digits", Toast.LENGTH_SHORT).show();
        }
        else{
            auth.createUserWithEmailAndPassword(umail,upass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        MyModel model=new MyModel(uname,uroll,uphone,umail);
        reference.child(uname).setValue(model);
    }
}