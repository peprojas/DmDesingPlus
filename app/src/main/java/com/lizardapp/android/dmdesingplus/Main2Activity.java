package com.lizardapp.android.dmdesingplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.lizardapp.android.dmdesingplus.entidades.Anuncio;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import layout.InfoFragment;

import static com.lizardapp.android.dmdesingplus.ImagenFragment.REQUEST_IMAGE_CAPTURE;
import static com.lizardapp.android.dmdesingplus.ImagenFragment.newInstance;

public class Main2Activity extends AppCompatActivity implements  DatosFragment.OnFragmentInteractionListener, ImagenFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener{

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
    final ImagenFragment imgfragment = newInstance("bb","bb");
    DatosFragment datosFragment;
    Anuncio anuncio;
    FloatingActionButton fab;



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


         fab = (FloatingActionButton) findViewById(R.id.fab);
          fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anuncio= datosFragment.onSetearData();
               /* Snackbar.make(view, "Se guardo la data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                mViewPager.setCurrentItem(1);
                fab.setVisibility(View.INVISIBLE);

            }
        });


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position==0){fab.setVisibility(View.VISIBLE);}
                if (position==1){ anuncio= datosFragment.onSetearData();
                    fab.setVisibility(View.INVISIBLE);;}
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
            Toast.makeText(this, "Cerrar Sesión", Toast.LENGTH_SHORT);
            DeletePreferencias();
            LoginManager.getInstance().logOut();
            Intent cerrar = new Intent(Main2Activity.this, LoginActivity.class);
            startActivity(cerrar);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {



    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }


    public void DeletePreferencias(){

        SharedPreferences misPrefencias = getSharedPreferences("PreferenciasUsuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPrefencias.edit();
        miEditor.putBoolean("cheked",false);
        miEditor.putString("params","");
        miEditor.commit();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        }
        else if (requestCode == Crop.REQUEST_CROP) {

            Uri ur = Crop.getOutput(result);
            Log.d("uri",ur.toString());

            imgfragment.onActivityResult(requestCode, resultCode, result);
            imgfragment.setearDataAnuncio(anuncio);
        }
    }


    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);

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
                fab.setVisibility(View.VISIBLE);
                return datosFragment;

            }
            if (position==1){

                //imgfragment = ImagenFragment.newInstance("a","b");

                return imgfragment;


            }
            if (position==2){

                return InfoFragment.newInstance("aa","bb");

            }
            else{

                return InfoFragment.newInstance("aa","bb");
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
                    return "Info";
            }
            return null;
        }

    }
}
