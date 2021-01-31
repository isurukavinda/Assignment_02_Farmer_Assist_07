package com.example.farmerassist07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.farmerassist07.Modal.User;
import com.example.farmerassist07.Modal.UserType;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;
    private ProgressBar mPbLoading;
    private TextView txtNotLogin;
    private Button btnRegisterRedirect, btnLoginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        mPbLoading = findViewById(R.id.pbLoading);
        txtNotLogin = findViewById(R.id.txtNotLoginAlert);
        btnRegisterRedirect = findViewById(R.id.btnRegisterRedirect);
        btnLoginRedirect = findViewById(R.id.btnLoginRederect);

        btnRegisterRedirect.setOnClickListener(v -> {
            Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(registerIntent);
        });

        btnLoginRedirect.setOnClickListener(v -> {
            Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(loginIntent);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPbLoading.setVisibility(View.VISIBLE);



        Handler handler = new Handler();
        handler.postDelayed(this::startFunction,2000);
    }

    void startFunction(){
        if(currentUser ==null){
            Log.d("USER","user is nulll");
            txtNotLogin.setVisibility(View.VISIBLE);
            btnLoginRedirect.setVisibility(View.VISIBLE);
            btnRegisterRedirect.setVisibility(View.VISIBLE);
            mPbLoading.setVisibility(View.INVISIBLE);
        }else {
            String uid = currentUser.getUid();

           CollectionReference userReference = db.collection("Users");
            Query query = userReference.whereEqualTo("uid",uid);
            query.get().addOnSuccessListener(queryDocumentSnapshots -> {
                User user = queryDocumentSnapshots.getDocuments().get(0).toObject(User.class);
                Intent intent;
                assert user != null;
                if(user.getUserType() == UserType.FARMER){
                    intent = new Intent(MainActivity.this, FarmerHome.class);

                }else {
                    intent = new Intent(MainActivity.this, BuyerHome.class);
                }
                startActivity(intent);
            });

        }
    }
}