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

public class AddCard extends AppCompatActivity {

    TextView cn,mn,yr,cvv,add;
    String CN,MN,YR,CVV,PIN;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        getSupportActionBar().setTitle("Add Card");

        cn = findViewById(R.id.no);
        mn = findViewById(R.id.mn);
        yr = findViewById(R.id.yr);
        cvv = findViewById(R.id.cvv);
        add = findViewById(R.id.add);

        CN = getIntent().getStringExtra("cn");
        MN = getIntent().getStringExtra("mn");
        YR = getIntent().getStringExtra("yr");
        CVV = getIntent().getStringExtra("cvv");

        if(CN.equals("") && MN.equals("") && YR.equals("") && CVV.equals("")){
            getSupportActionBar().setTitle("Add Card");
            add.setVisibility(View.VISIBLE);
            cn.setText("");
            mn.setText("");
            yr.setText("");
            cvv.setText("");
        }
        else {
            getSupportActionBar().setTitle("Your Card");
            add.setVisibility(View.INVISIBLE);

            final SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            final String pin = sharedPreferences.getString(Config.PIN_SHARED_PREF, "Not Available");

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
                        cn.setText(CN);
                        cn.setFocusable(false);
                        mn.setText(MN);
                        mn.setFocusable(false);
                        yr.setText(YR);
                        yr.setFocusable(false);
                        cvv.setText(CVV);
                        cvv.setFocusable(false);
                    }
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onBackPressed();
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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(AddCard.this,MainActivity.class);
        startActivity(in);
        finish();
    }

    public void addCard(){
        if (cn.getText().toString().equals("") || mn.getText().toString().equals("") || yr.getText().toString().equals("") || cvv.getText().toString().equals("") || cn.getText().toString().length()!=16 || cvv.getText().toString().length()!=3){
            AlertDialog.Builder builder = new AlertDialog.Builder(AddCard.this);
            builder.setMessage("Please Enter Proper Entries.")
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setCancelable(false)
                    .create()
                    .show();
        }
        else {

            Intent i = new Intent(AddCard.this,dialoglayout.class);
            i.putExtra("cn",cn.getText().toString());
            i.putExtra("mn",mn.getText().toString());
            i.putExtra("yr",yr.getText().toString());
            i.putExtra("cvv",cvv.getText().toString());
            startActivity(i);
            finish();
        }
    }
}
