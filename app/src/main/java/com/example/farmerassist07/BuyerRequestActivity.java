package com.example.farmerassist07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmerassist07.Modal.BuyerRequest;
import com.example.farmerassist07.Modal.User;
import com.example.farmerassist07.Modal.UserType;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class BuyerRequestActivity extends AppCompatActivity {

    private EditText txtPrice, txtProductName, txtquentity;
    private Button btnPostReq;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_request);

        txtProductName = findViewById(R.id.etReqProductName);
        txtPrice = findViewById(R.id.etOfferingPrice);
        txtquentity = findViewById(R.id.etReqquentity);
        btnPostReq = findViewById(R.id.btnPostReq);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        btnPostReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String productName = txtProductName.getText().toString();
                    int quentity = Integer.parseInt(txtquentity.getText().toString());
                    float price = Float.parseFloat(txtPrice.getText().toString());

                String uid = mAuth.getUid();
                CollectionReference userReference = db.collection("Users");
                Query query = userReference.whereEqualTo("uid",uid);
                query.get().addOnSuccessListener(queryDocumentSnapshots -> {
                    User user = queryDocumentSnapshots.getDocuments().get(0).toObject(User.class);
                    BuyerRequest buyerRequest = new BuyerRequest(user,productName,quentity,price);

                    db.collection("BuyerReq").add(buyerRequest).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(
                                    BuyerRequestActivity.this,
                                    "Request Successfuly posted",
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(BuyerRequestActivity.this,BuyerHome.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("On Post Req",e.toString());
                        }
                    });
                });
                /*db.collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = null;
                        if(documentSnapshot.exists()){
                            user = documentSnapshot.toObject(User.class);


                        }
                    }
                });*/




            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(BuyerRequestActivity.this,BuyerHome.class);
        startActivity(intent);
    }
}