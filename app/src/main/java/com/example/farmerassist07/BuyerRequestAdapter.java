package com.example.farmerassist07;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.farmerassist07.Modal.BuyerRequest;
import com.example.farmerassist07.Modal.User;

import java.util.ArrayList;

public class BuyerRequestAdapter extends BaseAdapter {
private LayoutInflater inflater;
private Activity mActivity;
private ArrayList<BuyerRequest> buyerRequestArrayList;

    public BuyerRequestAdapter( Activity mActivity, ArrayList<BuyerRequest> buyerRequestArrayList) {

        this.mActivity = mActivity;
        this.buyerRequestArrayList = buyerRequestArrayList;
        this.inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return buyerRequestArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return buyerRequestArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView =(itemView==null)? inflater.inflate(R.layout.buyer_request_layout,null):itemView;

        TextView mBuyerName = itemView.findViewById(R.id.txtBuyerName);
        TextView mProductName =itemView.findViewById(R.id.txtProductName);
        TextView mBuyerPhone = itemView.findViewById(R.id.txtPhone);
        TextView mquntity = itemView.findViewById(R.id.txtquntity);
        TextView mUnitPrice = itemView.findViewById(R.id.txtUnitPrice);
       // TextView mBuyerAddress = itemView.findViewById(R.id)

        BuyerRequest buyerRequest = buyerRequestArrayList.get(position);
        User buyer = buyerRequest.getPostedBy();

       mBuyerName.setText(buyer.getName());
       mBuyerPhone.setText(buyer.getPhone());
        mquntity.setText(buyerRequest.getQuantity()+"KG");
        mProductName.setText(buyerRequest.getProductName());
        mUnitPrice.setText(String.valueOf(buyerRequest.getUnitPrice()));


        return itemView;
    }
}
