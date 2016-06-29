package com.usp.ihc.smarts;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class PlaceActivity extends AppCompatActivity {
    private Bundle bund;
    private TextView name;
    private TextView dist;
    private TextView time;
    private TextView aval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bund = getIntent().getExtras();

        name = (TextView)findViewById(R.id.nome);
        dist = (TextView)findViewById(R.id.dist);
        time = (TextView)findViewById(R.id.time);
        aval = (TextView)findViewById(R.id.aval);

        if(!isLargeScreen(this)){
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            dist.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            aval.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }

        name.setText(bund.getStringArrayList("names").get(bund.getInt("id")));
        dist.setText("Distância: " + bund.getStringArrayList("dist").get(bund.getInt("id")) + " km");
        time.setText("Tempo do percurso em ônibus: " + bund.getStringArrayList("time").get(bund.getInt("id")) + " min");
        aval.setText("Média de avaliações: " + bund.getStringArrayList("aval").get(bund.getInt("id")));
    }

    public static boolean isLargeScreen(Context context) {
        int screenSize = context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void goMap(View v){
        // Creates an Intent that will load a map of IME
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + bund.getStringArrayList("names").get(bund.getInt("id")).replace(" ", "+") +"&mode=b");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        mapIntent.setPackage("com.google.android.apps.maps");

        startActivity(mapIntent);
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
            Snackbar.make(findViewById(android.R.id.content), "Confira o prestador de serviços e, se desejar, veja-o no Google Maps", Snackbar.LENGTH_LONG)
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
