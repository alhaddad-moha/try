package com.hell.verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerviyOTPActivity extends AppCompatActivity {
        private EditText inputeCode1, inputeCode2, inputeCode3,inputeCode4, inputeCode5,inputeCode6;
       private String verificationId;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verviy_o_t_p);

        TextView textMobile= findViewById(R.id.textMobile);
        textMobile.setText(String.format(
                "+967- %s",getIntent().getStringExtra("Mobile")
        ));

        inputeCode1= findViewById(R.id.inputCode1);
        inputeCode2= findViewById(R.id.inputCode2);
        inputeCode3= findViewById(R.id.inputCode3);
        inputeCode4= findViewById(R.id.inputCode4);
        inputeCode5= findViewById(R.id.inputCode5);
        inputeCode6= findViewById(R.id.inputCode6);

        setupOTPInput5();
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final Button buttonVerify= findViewById(R.id.buttonVerify);

        verificationId = getIntent().getStringExtra("verificationId");

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(inputeCode1.getText().toString().trim().isEmpty()
             ||   inputeCode2.getText().toString().trim().isEmpty()
             ||   inputeCode3.getText().toString().trim().isEmpty()
             ||   inputeCode4.getText().toString().trim().isEmpty()
             ||   inputeCode5.getText().toString().trim().isEmpty()
             ||   inputeCode6.getText().toString().trim().isEmpty()){

                 Toast.makeText(VerviyOTPActivity.this, "Please Enter Valid Number",Toast.LENGTH_LONG);
                 return;
             }

             String code=
                     inputeCode1.getText().toString()+
                             inputeCode2.getText().toString()+
                             inputeCode3.getText().toString()+
                             inputeCode4.getText().toString()+
                             inputeCode5.getText().toString()+
                             inputeCode6.getText().toString();


             if(verificationId!= null)
             {
                 progressBar.setVisibility(View.VISIBLE);
                 buttonVerify.setVisibility(View.INVISIBLE);
                 PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                         verificationId,
                         code
                 );

                 FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                         .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 progressBar.setVisibility(View.GONE);
                                 buttonVerify.setVisibility(View.VISIBLE);

                                 if(task.isSuccessful())
                                 {
                                     Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                     startActivity(intent);
                                 }

                                 else
                                 {
                                     Toast.makeText(VerviyOTPActivity.this, "The code is inValid..!",Toast.LENGTH_LONG);

                                 }


                             }
                         });
             }


            }
        });
    }

    public void setupOTPInput5(){

        inputeCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!s.toString().trim().isEmpty())
            {
                inputeCode2.requestFocus();
            }
            else{
                Toast.makeText(VerviyOTPActivity.this,"Enter Mobile",Toast.LENGTH_LONG).show();

            }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        inputeCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputeCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        inputeCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputeCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        inputeCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputeCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        inputeCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputeCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });



    }
}