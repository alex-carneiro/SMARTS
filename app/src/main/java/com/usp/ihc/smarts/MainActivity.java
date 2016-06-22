package com.usp.ihc.smarts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
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

        bund.putString("type", "Pronto socorro");
        search.putExtras(bund);

        startActivity(search);
    }

    public void search2(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", "Clínicas");
        search.putExtras(bund);

        startActivity(search);
    }

    public void search3(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", "Maternidades");
        search.putExtras(bund);

        startActivity(search);
    }

    public void search4(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", "Posto de saúde");
        search.putExtras(bund);

        startActivity(search);
    }

    public void search5(View v){
        Intent search = new Intent(getApplicationContext(), SearchActivity.class);
        Bundle bund = new Bundle();

        bund.putString("type", "Outros serviços");
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
