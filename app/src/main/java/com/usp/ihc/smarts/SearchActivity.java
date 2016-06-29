package com.usp.ihc.smarts;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Marker mkr0;
    private Marker mkr1;
    private Marker mkr2;
    private Marker mkr3;
    private Marker mkr4;

    private Bundle bund;
    private TextView tp;
    private TextView lab1;
    private TextView lab2;
    private TextView lab3;

    private ArrayList<Float> pos = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> dist = new ArrayList<>();
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> aval = new ArrayList<>();
    private BitmapDescriptor img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bund = getIntent().getExtras();
        tp = (TextView)findViewById(R.id.service_type);
        lab1 = (TextView)findViewById(R.id.text_dist);
        lab2 = (TextView)findViewById(R.id.text_time);
        lab3 = (TextView)findViewById(R.id.text_aval);

        if(!isLargeScreen(this)){
            tp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            lab1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            lab2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            lab3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }

        tp.setText(tp.getText() + bund.getString("type"));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void goListRanking(View v){
        Intent list = new Intent(getApplicationContext(), ListActivity.class);
        ArrayList<String> content = new ArrayList<>();

        content.add(names.get(0) + "\nMédia de valiações: " + aval.get(0).toString());
        content.add(names.get(1) + "\nMédia de valiações: " + aval.get(1).toString());
        content.add(names.get(2) + "\nMédia de valiações: " + aval.get(2).toString());
        content.add(names.get(3) + "\nMédia de valiações: " + aval.get(3).toString());
        content.add(names.get(4) + "\nMédia de valiações: " + aval.get(4).toString());

        bund.putStringArrayList("markers", content);
        bund.putString("sortby", "avaliações");
        list.putExtras(bund);

        startActivity(list);
    }

    public void goListTime(View v){
        Intent list = new Intent(getApplicationContext(), ListActivity.class);
        ArrayList<String> content = new ArrayList<>();

        content.add(names.get(0) + "\nTempo de percurso em ônibus: " + time.get(0).toString() + " min");
        content.add(names.get(1) + "\nTempo de percurso em ônibus: " + time.get(1).toString() + " min");
        content.add(names.get(2) + "\nTempo de percurso em ônibus: " + time.get(2).toString() + " min");
        content.add(names.get(3) + "\nTempo de percurso em ônibus: " + time.get(3).toString() + " min");
        content.add(names.get(4) + "\nTempo de percurso em ônibus: " + time.get(4).toString() + " min");

        bund.putStringArrayList("markers", content);
        bund.putString("sortby", "tempo em de percurso em ônibus");
        list.putExtras(bund);

        startActivity(list);
    }

    public void goListDistance(View v){
        Intent list = new Intent(getApplicationContext(), ListActivity.class);
        ArrayList<String> content = new ArrayList<>();

        content.add(names.get(0) + "\nDistância: " + dist.get(0).toString() + " km");
        content.add(names.get(1) + "\nDistância: " + dist.get(1).toString() + " km");
        content.add(names.get(2) + "\nDistância: " + dist.get(2).toString() + " km");
        content.add(names.get(3) + "\nDistância: " + dist.get(3).toString() + " km");
        content.add(names.get(4) + "\nDistância: " + dist.get(4).toString() + " km");

        bund.putStringArrayList("markers", content);
        bund.putStringArrayList("names", names);
        bund.putStringArrayList("dist", dist);
        bund.putStringArrayList("time", time);
        bund.putStringArrayList("aval", aval);
        bund.putString("sortby", "distâncias");
        list.putExtras(bund);

        startActivity(list);
    }

    public static boolean isLargeScreen(Context context) {
        int screenSize = context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE;
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
        if(bund.getInt("cat") == 1){
            img = BitmapDescriptorFactory.fromResource(R.drawable.prontosocorro_pin);

            names.add("Hospital Metropolitano Butantã");
            pos.add(new Float(-23.5778783));
            pos.add(new Float(-46.710237));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Pronto Socorro Butantã");
            pos.add(new Float(-23.5845095));
            pos.add(new Float(-46.7389941));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Pronto Socorro Municipal da Lapa");
            pos.add(new Float(-23.5376878));
            pos.add(new Float(-46.7223644));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Pompéia Pronto Socorro");
            pos.add(new Float(-23.530763));
            pos.add(new Float(-46.6856718));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Pronto Socorro Hospital Samaritano");
            pos.add(new Float(-23.5397619));
            pos.add(new Float(-46.6617802));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");
        }else if(bund.getInt("cat") == 2){
            img = BitmapDescriptorFactory.fromResource(R.drawable.maternidade_pin);

            names.add("Hospital Municipal Maternidade Prof Mario Degni");
            pos.add(new Float(-23.577892));
            pos.add(new Float(-46.7649257));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Hospital e Maternidade Metropolitano");
            pos.add(new Float(-23.5305761));
            pos.add(new Float(-46.6970766));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Hospital e Maternidade Jardins");
            pos.add(new Float(-23.5657179));
            pos.add(new Float(-46.6866803));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Unidade Avançada Hospital e Maternidade Renascença");
            pos.add(new Float(-23.5376091));
            pos.add(new Float(-46.7782509));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Hospital e Maternindade Sino Brasileiro");
            pos.add(new Float(-23.5317663));
            pos.add(new Float(-46.7816037));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");
        }else if(bund.getInt("cat") == 3){
            img = BitmapDescriptorFactory.fromResource(R.drawable.dentista_pin);

            names.add("Unicodonto Dentista Butantã");
            pos.add(new Float(-23.5707529));
            pos.add(new Float(-46.70964));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Vital Odonto");
            pos.add(new Float(-23.5715445));
            pos.add(new Float(-46.7070276));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Coore Odontologia");
            pos.add(new Float(-23.5536657));
            pos.add(new Float(-46.7473358));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Odontologia Jaguaré");
            pos.add(new Float(-23.5483646));
            pos.add(new Float(-46.7480117));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("RH Odontologia");
            pos.add(new Float(-23.580596));
            pos.add(new Float(-46.7348796));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");
        }else{
            img = BitmapDescriptorFactory.fromResource(R.drawable.map_gen_icon);

            names.add("Local 1");
            pos.add(new Float(-23.5660094));
            pos.add(new Float(-46.7244228));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Local 2");
            pos.add(new Float(-23.568586));
            pos.add(new Float(-46.744850));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Local 3");
            pos.add(new Float(-23.553874));
            pos.add(new Float(-46.720861));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Local 4");
            pos.add(new Float(-23.558461));
            pos.add(new Float(-46.7131418));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");

            names.add("Local 5");
            pos.add(new Float(-23.553205));
            pos.add(new Float(-46.707300));
            dist.add("10,0");
            time.add("20,0");
            aval.add("3,4");
        }

        // Adicionando markers
        mkr0 = mMap.addMarker(new MarkerOptions().position(new LatLng(pos.get(0), pos.get(1))).title(names.get(0)).icon(img));
        mkr1 = mMap.addMarker(new MarkerOptions().position(new LatLng(pos.get(2), pos.get(3))).title(names.get(1)).icon(img));
        mkr2 = mMap.addMarker(new MarkerOptions().position(new LatLng(pos.get(4), pos.get(5))).title(names.get(2)).icon(img));
        mkr3 = mMap.addMarker(new MarkerOptions().position(new LatLng(pos.get(6), pos.get(7))).title(names.get(3)).icon(img));
        mkr4 = mMap.addMarker(new MarkerOptions().position(new LatLng(pos.get(8), pos.get(9))).title(names.get(4)).icon(img));
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
    }
}
