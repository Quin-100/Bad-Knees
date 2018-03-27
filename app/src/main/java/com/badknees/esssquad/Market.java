package com.badknees.esssquad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Market extends AppCompatActivity {

    ImageView j,m,a,b,f,s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        getSupportActionBar().setTitle("Pay For");

        j = findViewById(R.id.jabong);
        m = findViewById(R.id.mmt);
        a = findViewById(R.id.amazon);
        b = findViewById(R.id.bigbasket);
        f = findViewById(R.id.flipkart);
        s = findViewById(R.id.shopclues);

        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Market.this,Payment.class);
                i.putExtra("cn","Jabong");
                i.putExtra("d","2 pair of tshirts");
                i.putExtra("a","1000");
                i.putExtra("sg","100");
                i.putExtra("cg","100");
                i.putExtra("t","1200");
                i.putExtra("ty","card payment");
                startActivity(i);
                finish();
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Market.this,Payment.class);
                i.putExtra("cn","Make My Trip");
                i.putExtra("d","4 tickets of Jumanji(20:15 GST) screen 3A");
                i.putExtra("a","2000");
                i.putExtra("sg","150");
                i.putExtra("cg","150");
                i.putExtra("t","2300");
                i.putExtra("ty","card payment");
                startActivity(i);
                finish();

            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Market.this,Payment.class);
                i.putExtra("cn","Amazon");
                i.putExtra("d","1 speaker, 2 glass frames, 2 bed sheets");
                i.putExtra("a","2450");
                i.putExtra("sg","120");
                i.putExtra("cg","120");
                i.putExtra("t","2690");
                i.putExtra("ty","card payment");
                startActivity(i);
                finish();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Market.this,Payment.class);
                i.putExtra("cn","Big Basket");
                i.putExtra("d","1 kg tomato, 5 kg potato, 5 kg onion");
                i.putExtra("a","270");
                i.putExtra("sg","25");
                i.putExtra("cg","25");
                i.putExtra("t","320");
                i.putExtra("ty","card payment");
                startActivity(i);
                finish();
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Market.this,Payment.class);
                i.putExtra("cn","Flipkart");
                i.putExtra("d","1 mivi headset, 1 canon lens");
                i.putExtra("a","5900");
                i.putExtra("sg","300");
                i.putExtra("cg","300");
                i.putExtra("t","6500");
                i.putExtra("ty","card payment");
                startActivity(i);
                finish();
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Market.this,Payment.class);
                i.putExtra("cn","Shopclues");
                i.putExtra("d","5 tshirts, 2 pair of sunglass");
                i.putExtra("a","3000");
                i.putExtra("sg","250");
                i.putExtra("cg","250");
                i.putExtra("t","3500");
                i.putExtra("ty","card payment");
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(Market.this,MainActivity.class);
        startActivity(in);
        finish();
    }
}
