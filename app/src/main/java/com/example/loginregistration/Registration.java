package com.example.loginregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gps-system-eeb27-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText fullname = findViewById( R.id.fullname);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById( R.id.phone);
        final EditText password = findViewById(R.id.password);
        final EditText conpassword = findViewById(R.id.conpassword);

        final Button RegisterBtn = findViewById(R.id.RegisterBtn);
        final TextView LoginNowBtn = findViewById(R.id.LoginNow);

        RegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                final String fullnameTxt = fullname.getText().toString();
                final String emailTxt = email.getText().toString();
                final String phoneTxt = phone.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String conpasswordTxt = conpassword.getText().toString();

                if(fullnameTxt.isEmpty() || emailTxt.isEmpty() ||phoneTxt.isEmpty() ||passwordTxt.isEmpty()){
                    Toast.makeText(Registration.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else if(!passwordTxt.equals(conpasswordTxt)){
                    Toast.makeText(Registration.this, "Passwords are not Matching", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phoneTxt)) {
                                Toast.makeText(Registration.this, "The Phone Number is Already Registered", Toast.LENGTH_SHORT).show();

                            }else{

                                    databaseReference.child("users").child(phoneTxt).child(fullnameTxt).setValue(fullnameTxt);
                                    databaseReference.child("users").child(phoneTxt).child(emailTxt).setValue(emailTxt);
                                    databaseReference.child("users").child(phoneTxt).child(passwordTxt).setValue(passwordTxt);

                                    Toast.makeText(Registration.this,  "User registered Successfully", Toast.LENGTH_SHORT).show();
                                    finish();

                                }
                            }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });





                }


            }

        });

        LoginNowBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}