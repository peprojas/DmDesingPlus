package com.lizardapp.android.dmdesingplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent iniciologin = new Intent(MainActivity.this, LoginActivity.class);

        // SetearPreferencias();




        if  (ObtenerPreferecias()){

            Intent pintent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(pintent);
        }

        else{

            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    startActivity(iniciologin);

                    finish();
                }
            }, AUTO_HIDE_DELAY_MILLIS);

        }

    }

    public boolean ObtenerPreferecias()  {

        SharedPreferences misPreferencias = getSharedPreferences("PreferenciasUsuarios", Context.MODE_PRIVATE);
        Log.d()

        return misPreferencias.getBoolean("cheked",false);
        }

    }
