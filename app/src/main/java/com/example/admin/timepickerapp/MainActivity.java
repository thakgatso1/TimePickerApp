package com.example.admin.timepickerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
int qty=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){

        qty=qty+1;
        display(qty);
    }
    public void decrement(View view){

        qty=qty-1;
        display(qty);
    }

    public void submitOrder(View view){
      displayPrice(5*qty);
    }

    private void display(int value){
        TextView qtyTextView=(TextView)findViewById(R.id.txtQty);
        qtyTextView.setText("" + value);
    }

    private void displayPrice(int num){
        TextView priceTextView=(TextView)findViewById(R.id.txtPrice);
        priceTextView.setText(String.valueOf(num) + "\nThank you!");
    }
}
