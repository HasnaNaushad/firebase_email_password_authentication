package com.example.auth;

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

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login, register;
    FirebaseAuth fauth;

        @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            email = findViewById(R.id.emailid);
            password = findViewById(R.id.passwordid);
            login = findViewById(R.id.btn1);
            register = findViewById(R.id.btn2);
            fauth = FirebaseAuth.getInstance();

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, register.class);
                    startActivity(intent);
                }
            });
        login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String lmail = email.getText().toString().trim();
                    String lpassword = password.getText().toString().trim();

                    fauth.signInWithEmailAndPassword(lmail, lpassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "login successfull", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(MainActivity.this, "login fail", Toast.LENGTH_SHORT).show();

                                    }
                                }


                            });
                }
            });

        }
}