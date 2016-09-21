package com.example.admin.timepickerapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
int qty=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
private int calcPrice(boolean addCream,boolean addChoc){
    int basePrice=5;
    if (addCream) {
        basePrice=basePrice+1;
    }

    if (addChoc) {
       basePrice= basePrice+2;
    }

    return qty*basePrice;
}
    public void increment(View view) {
      if(qty==100){
          Toast.makeText(this,"you cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
          return;
      }
        qty = qty + 1;
        displayQty(qty);
    }
public void decrement(View view){
    if (qty == 1) {
        Toast.makeText(this,"you cannot have less than 1 cup of coffee ",Toast.LENGTH_SHORT).show();
        return;
    }
        qty=qty-1;
        displayQty(qty);
    }

    public void submitOrder(View view){


        EditText txtName=(EditText)findViewById(R.id.name_field);
        String name=txtName.getText().toString();

        CheckBox whippedCreamCheckBox=(CheckBox)findViewById(R.id.chkWcream);
        boolean hasWhippedCream=whippedCreamCheckBox.isChecked();

        CheckBox chocCheckBox=(CheckBox)findViewById(R.id.chkChoc);
        boolean hasChoc=chocCheckBox.isChecked();

    int price=calcPrice(hasWhippedCream,hasChoc);
        String priceMessage=createOrderSummary(name,price,hasWhippedCream,hasChoc);


        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }
        displayMessage(priceMessage);
    }

    private void displayQty(int value){
        TextView qtyTextView=(TextView)findViewById(R.id.txtQty);
        qtyTextView.setText("" + value);
    }
private  void displayMessage(String message){
    TextView orderSummaryTextView=(TextView)findViewById(R.id.txtOrderSummary);
    orderSummaryTextView.setText(message);
}

    private String createOrderSummary(String name,int price,boolean addCream,boolean addChoc){

        String priceMessage="Name: " + name;
        priceMessage+="\nadd whipped cream ? "+ addCream;
        priceMessage+="\nadd Chocolate ? "+ addChoc;
        priceMessage+="\nQuantity: "+ qty;
        priceMessage+="\nTotal: R"+price;
        priceMessage+=  "\nThank you!!";
        return  priceMessage;
    }
}
