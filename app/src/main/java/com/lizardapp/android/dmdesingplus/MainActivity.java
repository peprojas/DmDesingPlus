package com.lizardapp.android.dmdesingplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent iniciologin = new Intent(MainActivity.this, IngresoActivity.class);

        // SetearPreferencias();

            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    startActivity(iniciologin);
                    finish();
                };
            }, AUTO_HIDE_DELAY_MILLIS);

        }



    public boolean ObtenerPreferecias()  {

        SharedPreferences misPreferencias = getSharedPreferences("PreferenciasUsuarios", Context.MODE_PRIVATE);

        return misPreferencias.getBoolean("cheked",false);
    }

}
