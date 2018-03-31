package com.diazbumma.coffee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Receipt extends AppCompatActivity {

    TextView buyers;
    TextView coffees;
    TextView toppings;
    TextView bills;
    String tambahan = "With ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        buyers = (TextView)findViewById(R.id.buyer);
        coffees = (TextView)findViewById(R.id.coffee);
        toppings = (TextView)findViewById(R.id.topping);
        bills = (TextView)findViewById(R.id.bill);

        Intent receipt = getIntent();
        if(receipt.hasExtra("BUYER")){
            String pembeli = receipt.getStringExtra("BUYER");
            buyers.setText(pembeli+"");
        }
        if(receipt.hasExtra("AMOUNT")){
            Integer jumlah = receipt.getIntExtra("AMOUNT",0);
            if(jumlah>1){
                coffees.setText(jumlah+" Cups of Coffee");
            }
            else
                coffees.setText(jumlah+" Cup of Coffee");
        }
        if(receipt.hasExtra("WHIPPEDCREAM") || receipt.hasExtra("CHOCOLATE")){
            Boolean krim = receipt.getBooleanExtra("WHIPPEDCREAM",false);
            Boolean coklat = receipt.getBooleanExtra("CHOCOLATE",false);
            if(krim && coklat){
                tambahan += "Whipped Cream and Chocolate toppings";
            }
            else if(krim){
                tambahan += "Whipped Cream topping";
            }
            else if(coklat){
                tambahan += "Chocolate topping";
            }
            else
                tambahan = "";
        }
        if(receipt.hasExtra("PRICE")){
            Integer harga = receipt.getIntExtra("PRICE", 0);
            if(tambahan.isEmpty()){
                toppings.setVisibility(View.GONE);
            }
            else
                toppings.setText(tambahan+"");
            bills.setText("Rp "+harga+"");
        }
    }
}
