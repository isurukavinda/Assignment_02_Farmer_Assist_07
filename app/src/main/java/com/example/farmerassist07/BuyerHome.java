package com.example.farmerassist07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.farmerassist07.Modal.BuyerRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BuyerHome extends AppCompatActivity {

    private Button btnPostReq;

    private FirebaseFirestore fs;
    private ArrayList<BuyerRequest> buyerRequestArrayList;
    private ListView listView;
    private BuyerRequestAdapter buyerRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);

        btnPostReq = findViewById(R.id.btnReq);

        fs = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.lstFarmerOffer);
        fs.collection("FarmerOffer").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            buyerRequestArrayList = new ArrayList<>();
                            for (BuyerRequest buyerRequest: task.getResult().toObjects(BuyerRequest.class)){
                                buyerRequestArrayList.add(buyerRequest);
                                Log.d("BUYERRE",buyerRequest.toString());
                            }
                            buyerRequestAdapter = new BuyerRequestAdapter(BuyerHome.this,buyerRequestArrayList);
                            listView.setAdapter(buyerRequestAdapter);
                            buyerRequestAdapter.notifyDataSetChanged();
                        }
                    }
                });

        btnPostReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyerHome.this,BuyerRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}