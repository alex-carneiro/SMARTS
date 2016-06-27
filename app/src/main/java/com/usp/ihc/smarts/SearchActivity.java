package com.usp.ihc.smarts;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Marker ps1;
    private Marker cl1;
    private Marker ma1;
    private Marker pa1;
    private Marker os1;

    private Bundle bund;
    private LinearLayout linearLayout;
    private ScrollView scroll;
    private ListView places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bund = getIntent().getExtras();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, bund.getStringArrayList("markers"));

//        places = new ListView(this);
//
//        places.setAdapter(adapter1);
//        places.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//
//        linearLayout = (LinearLayout)findViewById(R.id.places_list);
//        linearLayout.addView(places);
//
//

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void mapSearch(View v){
        Intent map = new Intent(getApplicationContext(), MapsActivity.class);
        map.putExtras(bund);

        startActivity(map);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ime = new LatLng(-23.558461,-46.7333438);

        loadMarkers();
        createListener();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ime));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(ime.latitude, ime.longitude), 12.0f));
    }

    public void loadMarkers(){
        // Pronto socorro
        ps1 = mMap.addMarker(new MarkerOptions().position(new LatLng(-23.5660094,-46.7244228)).title("Pronto socorro 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_gen_icon)));

        // Clinicas
        cl1 = mMap.addMarker(new MarkerOptions().position(new LatLng(-23.568586, -46.744850)).title("Clinica 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_gen_icon)));

        // Maternidades
        ma1 = mMap.addMarker(new MarkerOptions().position(new LatLng(-23.553874, -46.720861)).title("Maternidade 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_gen_icon)));

        // Postos de saude
        pa1 = mMap.addMarker(new MarkerOptions().position(new LatLng(-23.558461,-46.7131418)).title("Posto de saude 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_gen_icon)));

        // Outros servicos
        os1 = mMap.addMarker(new MarkerOptions().position(new LatLng(-23.553205, -46.707300)).title("Outro serviço 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_gen_icon)));
    }

    public void createListener(){
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker m) {
                Snackbar.make(getWindow().getDecorView().getRootView(), m.getTitle() + " foi clicado!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });
    }

    public ArrayList<String> getVisibleMarkers(){
        ArrayList<String> lm = new ArrayList<String>();

        if(ps1.isVisible()) lm.add(ps1.toString());

        if(cl1.isVisible()) lm.add(cl1.toString());

        if(ma1.isVisible()) lm.add(ma1.toString());

        if(pa1.isVisible()) lm.add(pa1.toString());

        if(os1.isVisible()) lm.add(os1.toString());

        return lm;
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

    @Override
    public boolean onSupportNavigateUp(){
        this.finish();

        return true;
    }}
