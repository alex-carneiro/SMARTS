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
import android.widget.Button;
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
    private Button lab1;
    private Button lab2;
    private Button lab3;

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
        lab1 = (Button)findViewById(R.id.bt_dist);
        lab2 = (Button)findViewById(R.id.bt_time);
        lab3 = (Button)findViewById(R.id.bt_aval);

        if(!isLargeScreen(this)){
            tp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            lab1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            lab2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            lab3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }

        tp.setText(tp.getText() + bund.getString("type"));

        buildData();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void goListRanking(View v){
        Intent list = new Intent(getApplicationContext(), ListActivity.class);
        ArrayList<String> content = new ArrayList<>();
        String saux;
        String daux;
        String taux;
        String aaux;

        buildData();

        switch (bund.getInt("cat")){
            case 1: // 4 0 2 3 1
                saux = names.get(4);
                daux = dist.get(4);
                taux = time.get(4);
                aaux = aval.get(4);

                names.set(4, names.get(1));
                dist.set(4, dist.get(1));
                time.set(4, time.get(1));
                aval.set(4, aval.get(1));

                names.set(1, names.get(0));
                dist.set(1, dist.get(0));
                time.set(1, time.get(0));
                aval.set(1, aval.get(0));

                names.set(0, saux);
                dist.set(0, daux);
                time.set(0, taux);
                aval.set(0, aaux);
                break;
            case 2: //
                saux = names.get(3);
                daux = dist.get(3);
                taux = time.get(3);
                aaux = aval.get(3);

                names.set(3, names.get(4));
                dist.set(3, dist.get(4));
                time.set(3, time.get(4));
                aval.set(3, aval.get(4));

                names.set(4, saux);
                dist.set(4, daux);
                time.set(4, taux);
                aval.set(4, aaux);
                break;
            case 3:
                saux = names.get(0);
                daux = dist.get(0);
                taux = time.get(0);
                aaux = aval.get(0);

                names.set(0, saux);
                dist.set(0, daux);
                time.set(0, taux);
                aval.set(0, aaux);
                break;
            default:
        }

        content.add(names.get(0) + "\nMédia de valiações: " + (aval.get(0).toString().length() > 0 ? aval.get(0).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(0).toString() + " min" +
                "\nDistância: " + dist.get(0).toString() + " km");
        content.add(names.get(1) + "\nMédia de valiações: " + (aval.get(1).toString().length() > 0 ? aval.get(1).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(1).toString() + " min" +
                "\nDistância: " + dist.get(1).toString() + " km");
        content.add(names.get(2) + "\nMédia de valiações: " + (aval.get(2).toString().length() > 0 ? aval.get(2).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(2).toString() + " min" +
                "\nDistância: " + dist.get(2).toString() + " km");
        content.add(names.get(3) + "\nMédia de valiações: " + (aval.get(3).toString().length() > 0 ? aval.get(3).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(3).toString() + " min" +
                "\nDistância: " + dist.get(3).toString() + " km");
        content.add(names.get(4) + "\nMédia de valiações: " + (aval.get(4).toString().length() > 0 ? aval.get(4).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(4).toString() + " min" +
                "\nDistância: " + dist.get(4).toString() + " km");

        bund.putStringArrayList("markers", content);
        bund.putStringArrayList("names", names);
        bund.putStringArrayList("dist", dist);
        bund.putStringArrayList("time", time);
        bund.putStringArrayList("aval", aval);
        bund.putString("sortby", "avaliações");
        list.putExtras(bund);

        startActivity(list);
    }

    public void goListTime(View v){
        Intent list = new Intent(getApplicationContext(), ListActivity.class);
        ArrayList<String> content = new ArrayList<>();
        String saux;
        String daux;
        String taux;
        String aaux;

        buildData();

        switch (bund.getInt("cat")){
            case 1:
                saux = names.get(0);
                daux = dist.get(0);
                taux = time.get(0);
                aaux = aval.get(0);

                names.set(0, names.get(2));
                dist.set(0, dist.get(2));
                time.set(0, time.get(2));
                aval.set(0, aval.get(2));

                names.set(2, names.get(1));
                dist.set(2, dist.get(1));
                time.set(2, time.get(1));
                aval.set(2, aval.get(1));

                names.set(1, saux);
                dist.set(1, daux);
                time.set(1, taux);
                aval.set(1, aaux);
                break;
            case 2: // 2 4 0 1 3
                saux = names.get(0);
                daux = dist.get(0);
                taux = time.get(0);
                aaux = aval.get(0);

                names.set(0, names.get(2));
                dist.set(0, dist.get(2));
                time.set(0, time.get(2));
                aval.set(0, aval.get(2));

                names.set(2, saux);
                dist.set(2, daux);
                time.set(2, taux);
                aval.set(2, aaux);

                saux = names.get(1);
                daux = dist.get(1);
                taux = time.get(1);
                aaux = aval.get(1);

                names.set(1, names.get(4));
                dist.set(1, dist.get(4));
                time.set(1, time.get(4));
                aval.set(1, aval.get(4));

                names.set(4, names.get(3));
                dist.set(4, dist.get(3));
                time.set(4, time.get(3));
                aval.set(4, aval.get(3));

                names.set(3, saux);
                dist.set(3, daux);
                time.set(3, taux);
                aval.set(3, aaux);
                break;
            case 3:
                saux = names.get(0);
                daux = dist.get(0);
                taux = time.get(0);
                aaux = aval.get(0);

                names.set(0, saux);
                dist.set(0, daux);
                time.set(0, taux);
                aval.set(0, aaux);
                break;
            default:
        }

        content.add(names.get(0) + "\nTempo de percurso em ônibus: " + time.get(0).toString() + " min" +
                "\nMédia de valiações: " + (aval.get(0).toString().length() > 0 ? aval.get(0).toString() : "?") +
                "\nDistância: " + dist.get(0).toString() + " km");
        content.add(names.get(1) + "\nTempo de percurso em ônibus: " + time.get(1).toString() + " min" +
                "\nMédia de valiações: " + (aval.get(1).toString().length() > 0 ? aval.get(1).toString() : "?") +
                "\nDistância: " + dist.get(1).toString() + " km");
        content.add(names.get(2) + "\nTempo de percurso em ônibus: " + time.get(2).toString() + " min" +
                "\nMédia de valiações: " + (aval.get(2).toString().length() > 0 ? aval.get(2).toString() : "?") +
                "\nDistância: " + dist.get(2).toString() + " km");
        content.add(names.get(3) + "\nTempo de percurso em ônibus: " + time.get(3).toString() + " min" +
                "\nMédia de valiações: " + (aval.get(3).toString().length() > 0 ? aval.get(3).toString() : "?") +
                "\nDistância: " + dist.get(3).toString() + " km");
        content.add(names.get(4) + "\nTempo de percurso em ônibus: " + time.get(4).toString() + " min" +
                "\nMédia de valiações: " + (aval.get(4).toString().length() > 0 ? aval.get(4).toString() : "?") +
                "\nDistância: " + dist.get(4).toString() + " km");

        bund.putStringArrayList("markers", content);
        bund.putStringArrayList("names", names);
        bund.putStringArrayList("dist", dist);
        bund.putStringArrayList("time", time);
        bund.putStringArrayList("aval", aval);
        bund.putString("sortby", "tempo em de percurso em ônibus");
        list.putExtras(bund);

        startActivity(list);
    }

    public void goListDistance(View v){
        Intent list = new Intent(getApplicationContext(), ListActivity.class);
        ArrayList<String> content = new ArrayList<>();
        String saux;
        String daux;
        String taux;
        String aaux;

        buildData();

        switch (bund.getInt("cat")){
            case 1:
                saux = names.get(0);
                daux = dist.get(0);
                taux = time.get(0);
                aaux = aval.get(0);

                names.set(0, saux);
                dist.set(0, daux);
                time.set(0, taux);
                aval.set(0, aaux);
                break;
            case 2:
                saux = names.get(0);
                daux = dist.get(0);
                taux = time.get(0);
                aaux = aval.get(0);

                names.set(0, names.get(2));
                dist.set(0, dist.get(2));
                time.set(0, time.get(2));
                aval.set(0, aval.get(2));

                names.set(2, names.get(1));
                dist.set(2, dist.get(1));
                time.set(2, time.get(1));
                aval.set(2, aval.get(1));

                names.set(1, saux);
                dist.set(1, daux);
                time.set(1, taux);
                aval.set(1, aaux);
                break;
            case 3:
                saux = names.get(2);
                daux = dist.get(2);
                taux = time.get(2);
                aaux = aval.get(2);

                names.set(2, names.get(4));
                dist.set(2, dist.get(4));
                time.set(2, time.get(4));
                aval.set(2, aval.get(4));

                names.set(4, names.get(3));
                dist.set(4, dist.get(3));
                time.set(4, time.get(3));
                aval.set(4, aval.get(3));

                names.set(3, saux);
                dist.set(3, daux);
                time.set(3, taux);
                aval.set(3, aaux);
                break;
            default:
        }

        content.add(names.get(0) + "\nDistância: " + dist.get(0).toString() + " km" +
                "\nMédia de valiações: " + (aval.get(0).toString().length() > 0 ? aval.get(0).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(0).toString() + " min");
        content.add(names.get(1) + "\nDistância: " + dist.get(1).toString() + " km" +
                "\nMédia de valiações: " + (aval.get(1).toString().length() > 0 ? aval.get(1).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(1).toString() + " min");
        content.add(names.get(2) + "\nDistância: " + dist.get(2).toString() + " km" +
                "\nMédia de valiações: " + (aval.get(2).toString().length() > 0 ? aval.get(2).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(2).toString() + " min");
        content.add(names.get(3) + "\nDistância: " + dist.get(3).toString() + " km" +
                "\nMédia de valiações: " + (aval.get(3).toString().length() > 0 ? aval.get(3).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(3).toString() + " min");
        content.add(names.get(4) + "\nDistância: " + dist.get(4).toString() + " km" +
                "\nMédia de valiações: " + (aval.get(4).toString().length() > 0 ? aval.get(4).toString() : "?") +
                "\nTempo de percurso em ônibus: " + time.get(4).toString() + " min");

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

        mMap.addMarker(new MarkerOptions().position(ime).title("Estou aqui"));

        loadMarkers();
        createListener();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ime));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(ime.latitude, ime.longitude), 12.0f));
    }

    public void loadMarkers(){
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
                try {
                    Intent intent = new Intent(SearchActivity.this, PlaceActivity.class);
                    ArrayList<String> content = new ArrayList<>();
                    String n = m.getTitle();
                    String d = dist.get(names.indexOf(n));
                    String t = time.get(names.indexOf(n));
                    String a = aval.get(names.indexOf(n));

                    content.add(m.getTitle() + "\nDistância: " + d + " km" +
                            "\nMédia de valiações: " + (a.length() > 0 ? a : "?") +
                            "\nTempo de percurso em ônibus: " + t + " min");

                    names.clear();
                    dist.clear();
                    time.clear();
                    aval.clear();

                    names.add(n);
                    dist.add(d);
                    time.add(t);
                    aval.add(a);

                    bund.putStringArrayList("markers", content);
                    bund.putStringArrayList("names", names);
                    bund.putStringArrayList("dist", dist);
                    bund.putStringArrayList("time", time);
                    bund.putStringArrayList("aval", aval);
                    bund.putInt("id", 0);
                    intent.putExtras(bund);

                    startActivity(intent);
                }catch (IndexOutOfBoundsException e){
                    Snackbar.make(findViewById(android.R.id.content), m.getTitle(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                return true;
            }
        });
    }

    public void buildData(){
        if(names.size() > 0) names.clear();
        if(pos.size() > 0) pos.clear();
        if(dist.size() > 0) dist.clear();
        if(time.size() > 0) time.clear();
        if(aval.size() > 0) aval.clear();

        if(bund.getInt("cat") == 1){
            img = BitmapDescriptorFactory.fromResource(R.drawable.prontosocorro_pin);

            names.add("Hospital Metropolitano Butantã");
            pos.add(new Float(-23.5778783));
            pos.add(new Float(-46.710237));
            dist.add("4,3");
            time.add("29");
            aval.add("3,8");

            names.add("Pronto Socorro Butantã");
            pos.add(new Float(-23.5845095));
            pos.add(new Float(-46.7389941));
            dist.add("5,8");
            time.add("43");
            aval.add("?");

            names.add("Pronto Socorro Municipal da Lapa");
            pos.add(new Float(-23.5376878));
            pos.add(new Float(-46.7223644));
            dist.add("6,1");
            time.add("26");
            aval.add("3,3");

            names.add("Pompéia Pronto Socorro");
            pos.add(new Float(-23.530763));
            pos.add(new Float(-46.6856718));
            dist.add("10,5");
            time.add("56");
            aval.add("?");

            names.add("Pronto Socorro Hospital Samaritano");
            pos.add(new Float(-23.5397619));
            pos.add(new Float(-46.6617802));
            dist.add("12,4");
            time.add("1h 11");
            aval.add("4,1");
        }else if(bund.getInt("cat") == 2){
            img = BitmapDescriptorFactory.fromResource(R.drawable.maternidade_pin);

            names.add("Hospital Municipal Maternidade Prof Mario Degni");
            pos.add(new Float(-23.577892));
            pos.add(new Float(-46.7649257));
            dist.add("7");
            time.add("49");
            aval.add("4,6");

            names.add("Hospital e Maternidade Metropolitano");
            pos.add(new Float(-23.5305761));
            pos.add(new Float(-46.6970766));
            dist.add("7,9");
            time.add("54");
            aval.add("3,2");

            names.add("Hospital e Maternidade Jardins");
            pos.add(new Float(-23.5657179));
            pos.add(new Float(-46.6866803));
            dist.add("6,7");
            time.add("28");
            aval.add("2,5");

            names.add("Unidade Avançada Hospital e Maternidade Renascença");
            pos.add(new Float(-23.5376091));
            pos.add(new Float(-46.7782509));
            dist.add("9,1");
            time.add("58");
            aval.add("?");

            names.add("Hospital e Maternindade Sino Brasileiro");
            pos.add(new Float(-23.5317663));
            pos.add(new Float(-46.7816037));
            dist.add("10,4");
            time.add("48");
            aval.add("2,5");
        }else if(bund.getInt("cat") == 3){
            img = BitmapDescriptorFactory.fromResource(R.drawable.dentista_pin);

            names.add("Unicodonto Dentista Butantã");
            pos.add(new Float(-23.5707529));
            pos.add(new Float(-46.70964));
            dist.add("3,8");
            time.add("21");
            aval.add("?");

            names.add("Vital Odonto");
            pos.add(new Float(-23.5715445));
            pos.add(new Float(-46.7070276));
            dist.add("3,9");
            time.add("24");
            aval.add("?");

            names.add("Coore Odontologia");
            pos.add(new Float(-23.5536657));
            pos.add(new Float(-46.7473358));
            dist.add("5,3");
            time.add("33");
            aval.add("?");

            names.add("Odontologia Jaguaré");
            pos.add(new Float(-23.5483646));
            pos.add(new Float(-46.7480117));
            dist.add("5,4");
            time.add("35");
            aval.add("?");

            names.add("RH Odontologia");
            pos.add(new Float(-23.580596));
            pos.add(new Float(-46.7348796));
            dist.add("5,2");
            time.add("42");
            aval.add("?");
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
    }

    @Override
    public void onResume(){
        super.onResume();

        buildData();
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
            Snackbar.make(findViewById(android.R.id.content), "Escolha um local no mapa ou organize as informações como preferir", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

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
