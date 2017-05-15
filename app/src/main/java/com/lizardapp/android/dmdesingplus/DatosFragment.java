package com.lizardapp.android.dmdesingplus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lizardapp.android.dmdesingplus.entidades.Anuncio;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View vistadatos;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    private OnFragmentInteractionListener mListener;

    public DatosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosFragment newInstance(String param1, String param2) {
        DatosFragment fragment = new DatosFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vistadatos = inflater.inflate(R.layout.fragment_datos, container, false);

        // Inflate the layout for this fragment
        return vistadatos;
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


    public Anuncio onSetearData(){
        EditText edi1;
        Anuncio anuncio = new Anuncio()
                ;

        edi1 = (EditText) (vistadatos.findViewById(R.id.txtencabezado));
        anuncio.setEncabezado(edi1.getText().toString());
        edi1 = (EditText) (vistadatos.findViewById(R.id.txtdireccion));
        anuncio.setDireccion(edi1.getText().toString());
        edi1 = (EditText) (vistadatos.findViewById(R.id.txttelefono));
        anuncio.setTelefono(edi1.getText().toString());
        edi1 = (EditText) (vistadatos.findViewById(R.id.txtprice));
        anuncio.setPrecio(edi1.getText().toString());


        return anuncio;

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
}
