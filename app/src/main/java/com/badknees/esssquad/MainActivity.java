package com.badknees.esssquad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Bad Knees");

        bt1 = findViewById(R.id.btn1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Market.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.savedcard) {

            final SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String no = sharedPreferences.getString(Config.NO_SHARED_PREF, "");
            String mn = sharedPreferences.getString(Config.MN_SHARED_PREF, "");
            String yr = sharedPreferences.getString(Config.YR_SHARED_PREF, "");
            String cvv = sharedPreferences.getString(Config.CVV_SHARED_PREF, "");

            Intent i = new Intent(MainActivity.this,AddCard.class);
            i.putExtra("cn",no);
            i.putExtra("mn",mn);
            i.putExtra("yr",yr);
            i.putExtra("cvv",cvv);
            startActivity(i);
            finish();
        }
        if (id == R.id.addcard) {
            Intent i = new Intent(MainActivity.this,AddCard.class);
            i.putExtra("cn","");
            i.putExtra("mn","");
            i.putExtra("yr","");
            i.putExtra("cvv","");
            startActivity(i);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
