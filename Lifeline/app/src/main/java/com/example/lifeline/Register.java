package com.example.lifeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lifeline-42a1d-default-rtdb.firebaseio.com/");

        final EditText name = findViewById(R.id.editTextName);
        final EditText mobile = findViewById(R.id.editTextMobile);
        final EditText password = findViewById(R.id.editTextPassword);
        final AppCompatButton registerButton = findViewById(R.id.buttonRegister);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nameText = name.getText().toString();
                final String mobileText = mobile.getText().toString();
                final String passwordText = password.getText().toString();
                Log.d("ClICKED", "onClick: Clicked");
                if(nameText.isEmpty()) {
                    Toast.makeText(Register.this, "Please Enter a Name for Registration", Toast.LENGTH_SHORT).show();
                }else if (mobileText.isEmpty()) {
                    Toast.makeText(Register.this, "Please Enter an Email for Registration", Toast.LENGTH_SHORT).show();
                } else if (passwordText.isEmpty()) {
                    Toast.makeText(Register.this, "Please Enter a Password for Registration", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            progressDialog.dismiss();

                            if(snapshot.child("users").hasChild(mobileText)) {
                                Toast.makeText(Register.this, "An account already exists under this number", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("users").child(mobileText).child("mobile").setValue(mobileText);
                                databaseReference.child("users").child(mobileText).child("name").setValue(nameText);
                                databaseReference.child("users").child(mobileText).child("password").setValue(passwordText);

                                Intent intent = new Intent(Register.this, MainActivity.class);
                                intent.putExtra("mobile", mobileText);
                                intent.putExtra("name", nameText);
                                intent.putExtra("password", passwordText);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
    }
}