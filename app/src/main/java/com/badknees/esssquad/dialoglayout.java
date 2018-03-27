package com.badknees.esssquad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by M.Qasim on 25-03-2018.
 */

public class dialoglayout extends AppCompatActivity{

    EditText input,input2;
    Button bt1;
    String CN,MN,YR,CVV,PIN,PIN2;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialoglayout);

        getSupportActionBar().setTitle("Create Pin");

        input = findViewById(R.id.input);
        input2 = findViewById(R.id.input2);
        bt1 = findViewById(R.id.btn1);

        CN = getIntent().getStringExtra("cn");
        MN = getIntent().getStringExtra("mn");
        YR = getIntent().getStringExtra("yr");
        CVV = getIntent().getStringExtra("cvv");

        input2.setVisibility(View.VISIBLE);
        bt1.setVisibility(View.VISIBLE);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PIN = input.getText().toString();
                PIN2 = input2.getText().toString();

                if (!PIN.equals(PIN2)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(dialoglayout.this);
                    builder.setMessage("Please enter same pins in both the fields.")
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setCancelable(false)
                            .create()
                            .show();
                }
                else{
                    if(!PIN.equals("") || !PIN2.equals("")){

                        sharedPreferences = dialoglayout.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //Adding values to editor
                        editor.putString(Config.NO_SHARED_PREF, CN);
                        editor.putString(Config.MN_SHARED_PREF, MN);
                        editor.putString(Config.YR_SHARED_PREF, YR);
                        editor.putString(Config.CVV_SHARED_PREF, CVV);
                        editor.putString(Config.PIN_SHARED_PREF, PIN);

                        //Saving values to editor
                        editor.commit();

                        AlertDialog.Builder builder = new AlertDialog.Builder(dialoglayout.this);
                        builder.setMessage("Your card has been saved for further payments. check in saved cards menu")
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent in = new Intent(dialoglayout.this,MainActivity.class);
                                        startActivity(in);
                                        finish();
                                    }
                                })
                                .setCancelable(false)
                                .create()
                                .show();

                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(dialoglayout.this);
                        builder.setMessage("Please Enter Proper 4 digit pin.")
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                })
                                .setCancelable(false)
                                .create()
                                .show();
                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(dialoglayout.this,AddCard.class);
        in.putExtra("cn","");
        in.putExtra("mn","");
        in.putExtra("yr","");
        in.putExtra("cvv","");
        startActivity(in);
        finish();
    }

}
