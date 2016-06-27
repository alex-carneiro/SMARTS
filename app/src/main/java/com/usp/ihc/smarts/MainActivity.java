package com.usp.ihc.smarts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab_emergency);
        assert fab1 != null;
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Chamada para serviço de emergência!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void search1(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", getResources().getString(R.string.categ1));
        search.putExtras(bund);

        startActivity(search);
    }

    public void search2(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", getResources().getString(R.string.categ2));
        search.putExtras(bund);

        startActivity(search);
    }

    public void search3(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", getResources().getString(R.string.categ3));
        search.putExtras(bund);

        startActivity(search);
    }

    public void search4(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", getResources().getString(R.string.categ4));
        search.putExtras(bund);

        startActivity(search);
    }

    public void search5(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", getResources().getString(R.string.categ5));
        search.putExtras(bund);

        startActivity(search);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
