package com.diazbumma.coffee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int amount;
    int price;
    boolean withWhippedCream = false;
    boolean withChocolate = false;
    EditText buyer;
    TextView totalCoffee;
    TextView totalPrice;
    CheckBox whippedCream;
    CheckBox chocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buyer = (EditText) findViewById(R.id.buyers_name);
        totalCoffee = (TextView)findViewById(R.id.total_product);
        totalPrice = (TextView)findViewById(R.id.total_price);
        whippedCream = (CheckBox)findViewById(R.id.check_whipped_cream);
        chocolate = (CheckBox)findViewById(R.id.check_chocolate);
    }

    public void addOrder(View view) {
        amount++;
        price += 15000;
        if(withWhippedCream){
            price += 5000;
        }
        if(withChocolate){
            price += 8000;
        }
        if(totalCoffee!=null){
            totalCoffee.setText(amount+"");
            totalPrice.setText("Rp "+price+"");
        }
    }

    public void reduceOrder(View view) {
        if(amount>0){
            amount--;
            price -=15000;
            if(withWhippedCream){
                price -= 5000;
            }
            if(withChocolate){
                price -= 8000;
            }
            if(totalCoffee!=null){
                totalCoffee.setText(amount+"");
                totalPrice.setText("Rp "+price+"");
            }
        }
        if(amount==0){
            whippedCream.setChecked(false);
            withWhippedCream = false;
            chocolate.setChecked(false);
            withChocolate = false;
        }
    }

    public void addWhippedCream(View view) {
        if(amount!=0){
            boolean checked = ((CheckBox) view).isChecked();

            switch (view.getId()){
                case R.id.check_whipped_cream:
                    if(checked){
                        withWhippedCream = true;
                        price += 5000*amount;
                        totalPrice.setText("Rp "+price+"");
                    }
                    else {
                        withWhippedCream = false;
                        price -= 5000*amount;
                        totalPrice.setText("Rp "+price+"");
                    }
                    break;
            }
        }
        else{
            withWhippedCream = false;
            whippedCream.setChecked(false);
            Toast.makeText(this, "Please enter quantity first", Toast.LENGTH_SHORT).show();
        }
    }

    public void addChocolate(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(amount!=0){
            switch (view.getId()){
                case R.id.check_chocolate:
                    if(checked){
                        withChocolate = true;
                        price += 8000*amount;
                        totalPrice.setText("Rp "+price+"");
                    }
                    else {
                        withChocolate = false;
                        price -= 8000*amount;
                        totalPrice.setText("Rp "+price+"");
                    }
                    break;
            }
        }
        else{
            withChocolate = false;
            chocolate.setChecked(false);
            Toast.makeText(this, "Please enter quantity first", Toast.LENGTH_SHORT).show();
        }
    }

    public void orderCoffee(View view) {
        String nama = buyer.getText().toString();

        if (amount<=0) {
            Toast.makeText(this,"Please make order first", Toast.LENGTH_SHORT).show();
        }
        else if (nama.isEmpty()){
            Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent order = new Intent(this, Receipt.class);
            order.putExtra("BUYER",buyer.getText().toString());
            order.putExtra("PRICE",price);
            order.putExtra("AMOUNT",amount);
            order.putExtra("WHIPPEDCREAM",withWhippedCream);
            order.putExtra("CHOCOLATE",withChocolate);
            startActivity(order);
        }
    }
}
