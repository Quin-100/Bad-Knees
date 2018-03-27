package com.badknees.esssquad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    TextView cn,d,a,sg,cg,t,ty,pay;
    String CN,D,A,SG,CG,T,TY,PIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        getSupportActionBar().setTitle("Payment");

        cn = findViewById(R.id.cn);
        d = findViewById(R.id.d);
        a = findViewById(R.id.a);
        sg = findViewById(R.id.sg);
        cg = findViewById(R.id.cg);
        t = findViewById(R.id.t);
        ty = findViewById(R.id.ty);
        pay = findViewById(R.id.pay);

        CN = getIntent().getStringExtra("cn");
        D = getIntent().getStringExtra("d");
        A = getIntent().getStringExtra("a");
        SG = getIntent().getStringExtra("sg");
        CG = getIntent().getStringExtra("cg");
        T = getIntent().getStringExtra("t");
        TY = getIntent().getStringExtra("ty");

        cn.setText(CN);
        d.setText(D);
        a.setText(A);
        sg.setText(SG);
        cg.setText(CG);
        t.setText(T);
        ty.setText(TY);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment();
            }
        });
    }

    private void payment() {
        final SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String pin = sharedPreferences.getString(Config.PIN_SHARED_PREF, "Not Available");
        final String no = sharedPreferences.getString(Config.NO_SHARED_PREF, "Not Available");

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialoglayout, (ViewGroup) findViewById(android.R.id.content), false);
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        alertDialog.setView(viewInflated);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PIN = input.getText().toString();
                if (PIN.equals(pin)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Payment.this);
                    builder.setMessage("Your payment of ₹ "+T+" has been successfully done, by using card (XXXX-XXXX-XXXX-"+no.substring(12,16)+").")

                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent in = new Intent(Payment.this,MainActivity.class);
                                    startActivity(in);
                                    finish();
                                }
                            })
                            .setCancelable(false)
                            .create()
                            .show();
                }
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        final AlertDialog dialog = alertDialog.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(input.getText().toString().equals(pin))){
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    input.setError("Please Enter correct OTP.");
                }
                else{
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(Payment.this,Market.class);
        startActivity(in);
        finish();
    }
}
