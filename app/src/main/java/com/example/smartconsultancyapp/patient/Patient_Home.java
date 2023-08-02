package com.example.smartconsultancyapp.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartconsultancyapp.Data;
import com.example.smartconsultancyapp.MainActivity;
import com.example.smartconsultancyapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Patient_Home extends AppCompatActivity {
    TextInputEditText Symptom1;
    TextInputEditText Symptom2;
    TextInputEditText Symptom3;
    TextInputEditText Symptom4;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        Symptom1 = findViewById(R.id.EDsymptom1);
        Symptom2 = findViewById(R.id.EDsymptom2);
        Symptom3 = findViewById(R.id.EDsymptom3);
        Symptom4 = findViewById(R.id.EDsymptom4);
        btnSave = findViewById(R.id.btnSave);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hashMap=new HashMap<String,String>();
                hashMap.put("Symtom1",Symptom1.getText().toString());
                hashMap.put("Symptom2",Symptom2.getText().toString());
                hashMap.put("Symptom3",Symptom3.getText().toString());
                hashMap.put("Symptom4",Symptom4.getText().toString());
                ref.child("Patients").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),
                                        "Sent successful!",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
                Toast.makeText(getApplicationContext(),
                                "Sent failed!",
                                Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}

