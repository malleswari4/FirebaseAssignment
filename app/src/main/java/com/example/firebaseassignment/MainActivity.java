package com.example.firebaseassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,pass,user;
    FirebaseAuth auth;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.et1);
        pass=findViewById(R.id.et2);
        auth=FirebaseAuth.getInstance();
        reg=findViewById(R.id.reg);
        user=findViewById(R.id.et3);
        reg.setOnClickListener(this);
    }

    public void EmailAuthenticate(View view) {
        final String umail=email.getText().toString();
        final String upass=pass.getText().toString();

        if (umail.isEmpty()| upass.isEmpty())
        {
            Toast.makeText(this, "Fill the details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(umail,upass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        final String uname=user.getText().toString();
                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Data");
                        Query checkuser=reference.orderByChild("name").equalTo(uname);
                        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists())
                                {
                                    String passwordfromDB=dataSnapshot.child(uname).child("pass").getValue(String.class);
                                    String emailFromDb=dataSnapshot.child(uname).child("email").getValue(String.class);
                                    if (emailFromDb.equals(umail))
                                    {
                                        String nameFromDb=dataSnapshot.child(uname).child("name").getValue(String.class);
                                        String rollFromDb=dataSnapshot.child(uname).child("roll").getValue(String.class);
                                        String phoneFromDb=dataSnapshot.child(uname).child("phone").getValue(String.class);

                                        Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);

                                        intent.putExtra("sname",nameFromDb);
                                        intent.putExtra("sroll",rollFromDb);
                                        intent.putExtra("semail",emailFromDb);
                                        intent.putExtra("sphone",phoneFromDb);
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        Toast.makeText(MainActivity.this, "Email Doesn't Exist in DB", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "No user exist", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(MainActivity.this, "Do Register", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }
}