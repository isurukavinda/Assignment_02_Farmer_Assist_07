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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FarmerOfferActivity extends AppCompatActivity {

    Button mAddProduct;
    EditText etProductName, etProductPrice, etQuntity;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_offer);
        etProductName = findViewById(R.id.etFProductName);
        etProductPrice = findViewById(R.id.etFUnitPrice);
        etQuntity = findViewById(R.id.etFAvailable);
        mAddProduct = findViewById(R.id.btnAddProduct);


        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = etProductName.getText().toString();
                int quentity = Integer.parseInt(etQuntity.getText().toString());
                float price = Float.parseFloat(etProductPrice.getText().toString());

                String uid = mAuth.getUid();
                CollectionReference userReference = db.collection("Users");
                Query query = userReference.whereEqualTo("uid",uid);
                query.get().addOnSuccessListener(queryDocumentSnapshots -> {
                    User user = queryDocumentSnapshots.getDocuments().get(0).toObject(User.class);
                    BuyerRequest buyerRequest = new BuyerRequest(user,productName,quentity,price);

                    db.collection("FarmerOffer").add(buyerRequest).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(
                                    FarmerOfferActivity.this,
                                    "Your offer successfuly posted",
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(FarmerOfferActivity.this,FarmerHome.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("On Post Req",e.toString());
                        }
                    });
                });
            }
        });
    }
}