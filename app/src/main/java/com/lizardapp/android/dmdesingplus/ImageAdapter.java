package com.lizardapp.android.dmdesingplus;

/**
 * Created by joserojas on 7/5/17.
 */

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.lizardapp.android.dmdesingplus.entidades.Recurso;

import java.util.List;

/**
 * Created by joserojas on 7/5/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Recurso> listaResources;

    public ImageAdapter(Context c, List<Recurso> listaResources) {
        this.listaResources = listaResources;
        mContext = c;
    }

    @Override
    public int getCount() {

        return listaResources.size();
    }

    @Override
    public Object getItem(int pos) {

        return listaResources.get(pos);
    }

    @Override
    public long getItemId(int pos) {

        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Adding dynamic image simillarly we had added to Image Switcher
        ImageView i = new ImageView(mContext);
        Log.d("paso id","paso1");
        i.setImageResource(listaResources.get(position).getIdmini());
        Log.d("paso id","paso2");
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Setting background resource
        i.setBackgroundResource(listaResources.get(0).getIdmini());

        return i;
    }

}