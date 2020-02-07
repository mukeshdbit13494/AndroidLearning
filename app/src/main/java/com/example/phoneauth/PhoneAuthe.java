package com.example.phoneauth;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneAuthe extends AppCompatActivity {
    EditText et_phone,et_otp;
    FirebaseAuth mAuth;
    String codesend;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_auth);


        et_phone=(EditText)findViewById(R.id.et_phone);
        et_otp=(EditText)findViewById(R.id.et_otp);
        mAuth=FirebaseAuth.getInstance();

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        if(connected)
        {
            findViewById(R.id.btn_phone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendOTP();
                    Toast.makeText(getApplicationContext(),"OTP sending...",Toast.LENGTH_SHORT).show();
                }
            });

            findViewById(R.id.btn_otp).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyOTP();
                    Toast.makeText(getApplicationContext(),"OTP Verifying...",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public  void sendOTP()
    {
        String phoneNumber=et_phone.getText().toString();
        if(phoneNumber.isEmpty())
        {
            Toast.makeText(this,"Please Enter Mobile number!",Toast.LENGTH_LONG).show();
            return;
        }

        else{
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks

        }

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codesend=s;
        }
    };

    public void verifyOTP()
    {
        String code=et_otp.getText().toString();
        if(code.isEmpty() || codesend.isEmpty())
        {
            Toast.makeText(this,"Please try again!",Toast.LENGTH_SHORT).show();

        }
        else{
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesend, code);
            signInWithPhoneAuthCredential(credential);
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(PhoneAuthe.this,UserInsertData.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"WELOME TO REGISTRATION ACTIVITY",Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "Login Failed please try again!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

}