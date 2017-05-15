package com.lizardapp.android.dmdesingplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.lizardapp.android.dmdesingplus.entidades.Anuncio;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import static com.lizardapp.android.dmdesingplus.ImagenFragment.REQUEST_IMAGE_CAPTURE;

public class Main2Activity extends AppCompatActivity implements DatosFragment.OnFragmentInteractionListener, ImagenFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    Uri imageUri;
    ImagenFragment imgfragment;
    DatosFragment datosFragment;
    Anuncio anuncio;







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {



        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            beginCrop(result.getData());

        }

        else if (requestCode == Crop.REQUEST_CROP) {


        imgfragment.onActivityResult(requestCode, resultCode, result);



        }



    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            //imagen.setImageURI(Crop.getOutput(result));
               imageUri = Crop.getOutput(result);


        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                anuncio= datosFragment.onSetearData();
                Snackbar.make(view, "Se guardo la data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
    public void onFragmentInteraction(Uri uri) {



    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId()==R.id.action_settings){

            Toast.makeText(this,"Cerrar Sesión",Toast.LENGTH_SHORT);
            DeletePreferencias();
            Intent cerrar = new Intent(Main2Activity.this,IngresoActivity.class);

            startActivity(cerrar);

        }


        return false;
    }


    public void DeletePreferencias(){

        SharedPreferences misPrefencias = getSharedPreferences("PreferenciasUsuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPrefencias.edit();
        miEditor.putBoolean("cheked",false);
        miEditor.putString("params","");
        miEditor.commit();


    }


    /**
     * A placeholder fragment containing a simple view.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).


            if (position==0){


                datosFragment = DatosFragment.newInstance("aa","bb");

                  return datosFragment;

            }
            if (position==1){

                 imgfragment = ImagenFragment.newInstance("a","b");

                return imgfragment;


            }
            if (position==2){
                return DatosFragment.newInstance("aa","bb");

            }
            else{

                return DatosFragment.newInstance("aa","bb");
            }



        }

        

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Datos";
                case 1:
                    return "Imagen";
                case 2:
                    return "Recursos";
            }
            return null;
        }

    }
}
