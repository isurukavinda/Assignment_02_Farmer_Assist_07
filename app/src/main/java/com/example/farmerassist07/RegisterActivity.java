package com.example.farmerassist07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.farmerassist07.Modal.User;
import com.example.farmerassist07.Modal.UserType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etName, etAddress, etPhone;
    private RadioButton rbFarmer;

    private FirebaseAuth mAuth;
    FirebaseFirestore db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.etRegEmail);
        etPassword = findViewById(R.id.etRegPassword);
        etAddress = findViewById(R.id.etRegAddress);
        etName = findViewById(R.id.etRegName);
        etPhone = findViewById(R.id.etRegPhone);
        rbFarmer = findViewById(R.id.rbRegFarmer);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String name = etName.getText().toString();
            String address = etAddress.getText().toString();
            String phone = etPhone.getText().toString();
           final UserType userType = rbFarmer.isChecked()?UserType.FARMER:UserType.BUYER;

            mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(
                    authResult -> {
                        User user = new User(
                                Objects.requireNonNull(authResult.getUser()).getUid(),
                                name,
                                phone,
                                address,
                                userType );

                        db.collection("Users").add(user).addOnSuccessListener(
                                documentReference -> {
                                    Toast.makeText(
                                            RegisterActivity.this,
                                            "Registration success",
                                            Toast.LENGTH_LONG).show();
                                    if(userType.equals(UserType.FARMER)){
                                        Intent farmerHome = new Intent(
                                                RegisterActivity.this,FarmerHome.class
                                        );
                                        startActivity(farmerHome);
                                    }else{
                                        Intent buyerHome = new Intent(
                                                RegisterActivity.this,BuyerHome.class
                                        );
                                        startActivity(buyerHome);
                                    }
                                }
                        );
                    });
        });

    }
}