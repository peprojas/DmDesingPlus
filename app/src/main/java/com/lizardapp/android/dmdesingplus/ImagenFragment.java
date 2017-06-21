package com.lizardapp.android.dmdesingplus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;
import com.lizardapp.android.dmdesingplus.ImageEditor.FusionImage;
import com.lizardapp.android.dmdesingplus.entidades.Anuncio;
import com.lizardapp.android.dmdesingplus.entidades.Recurso;
import com.soundcloud.android.crop.Crop;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImagenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImagenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class ImagenFragment extends Fragment implements AdapterView.OnItemSelectedListener,
        ViewSwitcher.ViewFactory{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "ImagenFragment";

    private static final int PICK_IMAGE = 100;
    public   ImageView imagen;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button botonfoto;
    Button botongallery;
    Button botoncamara;
    Button compartir;
    ImageButton logo;
    ShareButton shareButton;
    private  Uri imageUri;
    private Gallery g;
    public Anuncio anuncio;
    ArrayList<Recurso> ListaRecursos;
    Recurso recurso;
    Bitmap imageFinal;
    SendButton sendButton;

    String colorx;


    private  View vista;
    private  TextView texto;
    private ImageSwitcher mswitcher;
    private Integer[] mImageIds = { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher };

    private OnFragmentInteractionListener mListener;

    public ImagenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImagenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImagenFragment newInstance(String param1, String param2) {
        ImagenFragment fragment = new ImagenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }




    public void  setearDataAnuncio(Anuncio data){
        anuncio = data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       vista = inflater.inflate(R.layout.fragment_imagen, container, false);

        imagen = (ImageView)vista.findViewById(R.id.imagenfrag);
       // texto = (TextView)vista.findViewById(R.id.textView3);
        botongallery = (Button) vista.findViewById(R.id.button4);
        sendButton = (SendButton) vista.findViewById(R.id.fb_send_button);

         shareButton = (ShareButton)vista.findViewById(R.id.fb_shared_button);
        logo = (ImageButton) vista.findViewById(R.id.imageButtonLogo);


        //Gallery for placing images
         g = (Gallery) vista.findViewById(R.id.gallery);
        ListaRecursos = new ArrayList<Recurso>();

        recurso = new Recurso();


        ListaRecursos.add(recurso.setearObjeto(R.drawable.pic001,"pic1",R.mipmap.pic001_mini,"255/255/255",70,150,50,940,50,980,700,200,500,500));
        ListaRecursos.add(recurso.setearObjeto(R.drawable.pic002,"pic2",R.mipmap.pic002_mini,"255/255/255",70,150,50,940,50,980,700,200,500,500));
        ListaRecursos.add(recurso.setearObjeto(R.drawable.pic003,"pic3",R.mipmap.pic003_mini,"255/255/255",70,150,50,940,50,980,700,200,500,500));
        ListaRecursos.add(recurso.setearObjeto(R.drawable.pic004,"pic4",R.mipmap.pic004_mini,"255/255/255",70,200,50,940,50,980,700,200,500,500));

        //Setting adapter over gallery
        g.setAdapter(new ImageAdapter(getContext(), ListaRecursos));
        //Implementing itemselected listener over gallery
        g.setOnItemSelectedListener(ImagenFragment.this);
        botongallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Crop.pickImage(getActivity());

            }
        });
        botoncamara = (Button) vista.findViewById(R.id.botoncam);
        botoncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Crop.pickImage(getActivity(),2023);

            }
        });

        return vista;
    }

    public void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {


         if (requestCode == Crop.REQUEST_CROP) {
             Log.d("URL4","se setio imagen");
             Uri ur = Crop.getOutput(result);
             Log.d("uri",ur.toString());
            handleCrop(resultCode,result);

         }



    }


    private  void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            //   imagen.setImageURI(Crop.getOutput(result));

//            imagen.setImageURI(null);
            imageUri = Crop.getOutput(result);

            Log.d("uri",imageUri.toString());
            imagen.setImageURI(imageUri);
            imagen.setVisibility(View.VISIBLE);
            g.setVisibility(View.VISIBLE);


        } else if (resultCode == Crop.RESULT_ERROR) {
            // Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       // mswitcher.setImageResource(mImageIds[i]);

        //imageFinal.recycle();


        try {
          final Bitmap  recursox = BitmapFactory.decodeResource(getActivity().getResources(), ListaRecursos.get(i).getId());

            colorx =ListaRecursos.get(i).getColorrgb();
            FusionImage fusionImage = new FusionImage();
            Bitmap mp = loadImageFromURL(imageUri.toString());
            imageFinal = fusionImage.thelast(mp,recursox,anuncio.getEncabezado(),colorx,
                    anuncio.getTelefono(),anuncio.getDireccion(),anuncio.getPrecio(),
                    anuncio.getDescripción(),ListaRecursos.get(i).getPosition1(),ListaRecursos.get(i).getPosition2(),ListaRecursos.get(i).getPosition3(),
                    ListaRecursos.get(i).getPosition4(),ListaRecursos.get(i).getPosition5() ,ListaRecursos.get(i).getPosition6(),
                    ListaRecursos.get(i).getPosition7(),ListaRecursos.get(i).getPosition8(),ListaRecursos.get(i).getPosition9(),
                    ListaRecursos.get(i).getPosition10());





            imagen.setImageBitmap(imageFinal);


            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(imageFinal)
                    .build();

            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            shareButton.setShareContent(content);
            sendButton.setShareContent(content);




        }catch(Exception e){

            Log.d("Error",e.toString());
        }


    }
    public Bitmap loadImageFromURL(String url) {
        try {
            BitmapFactory.Options btfctory = new BitmapFactory.Options();
            btfctory.inSampleSize=2;


            InputStream is = (InputStream) new URL(url).getContent();
            Bitmap d = BitmapFactory.decodeStream(is,null,btfctory);
            return d;
        } catch (Exception e) {

            Log.d("error imagen 1",e.toString());
            return null;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public View makeView() {
        ImageView i = new ImageView(getContext());

        //Setting background color
        i.setBackgroundColor(0xFF000000);

        //Setting scale type
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);

        //Now setting layout parameters for image view
        i.setLayoutParams(new ImageSwitcher.LayoutParams(
                ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT));

        //Returning imageview
        return i;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private class TaskImagen extends AsyncTask<Integer,Integer,Boolean>{


        @Override
        protected Boolean doInBackground(Integer... params) {


            try{

                FusionImage fusionImage = new FusionImage();
              /*  imageFinal = fusionImage.thelast(fusionImage.loadImageFromURL(imageUri.toString()),recursox,anuncio.getEncabezado(),colorx,
                        anuncio.getTelefono(),anuncio.getDireccion(),anuncio.getPrecio(),
                        anuncio.getDescripción(),(params[1]),params[2],params[3],
                        params[4],params[5],params[6],
                        params[7],params[8],params[9],
                        params[10]);*/

            }
            catch (Exception e){};

            return null;
        }
    }

}
