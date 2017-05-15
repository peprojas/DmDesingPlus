package com.lizardapp.android.dmdesingplus.ImageEditor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by joserojas on 8/5/17.
 */

public class FusionImage  {

    public  FusionImage(){

    }

    private Bitmap resource;
    Bitmap result;
    Bitmap resultado;

    public Bitmap thelast(Bitmap src, Bitmap marca){



        Bitmap image1=src;
        Bitmap image2=marca;
        // Bitmap image3=BitmapUtils.getResizedBitmap(BitmapUtils.de codeBase64(Lie.GetBanner().GetImagen()),Lie.GetBan ner().GetTamano().GetWidth(),Lie.GetBanner().GetTa mano().GeHeight());
        result = Bitmap.createBitmap(image1.getWidth(), image1.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);//Create the canvas to your image
        Rect srcRect = new Rect(0, 0, image1.getWidth(), image1.getHeight());
        Rect dstRect = new Rect(srcRect);
        Rect srcRect2 = new Rect(0, 0, image2.getWidth(), image2.getHeight());
        Rect dstRect2 = new Rect(srcRect);
        canvas.drawBitmap(image1, srcRect, dstRect, null);
        dstRect.offset(image1.getWidth(), 0);
        canvas.drawBitmap(image2, srcRect2, dstRect2, null);
        dibujartexto(canvas, "Hola 1");


        return result;

    }
    public Bitmap loadImageFromURL(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Bitmap d = BitmapFactory.decodeStream(is);
            return d;
        } catch (Exception e) {

            Log.d("error imagen 1",e.toString());
            return null;
        }
    }

    public void dibujartexto(Canvas canvas, String texto){

        int x = 0;
        int y = 0;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        canvas.save();
        canvas.translate(100, 200);

        // make the entire canvas white
        canvas.drawColor(Color.TRANSPARENT);

        // draw some text using STROKE style
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);

        canvas.drawText(texto, 0, 0, paint);

    }




}