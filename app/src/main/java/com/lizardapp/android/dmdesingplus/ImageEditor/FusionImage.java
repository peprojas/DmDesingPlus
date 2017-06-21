package com.lizardapp.android.dmdesingplus.ImageEditor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by joserojas on 8/5/17.
 */

public class FusionImage  {

    public  FusionImage(){

    }

    private Bitmap resource;
    Bitmap result;
    Bitmap resultado;

    public Bitmap thelast(Bitmap src, Bitmap marca, String titulo, String color_letter, String telefonos, String dirección,
                          String precio,String descrip, int posiciontitulox, int posiciontiuloy, int posicioxtelef,int posicioytelef,
                          int posicionxdireccion, int posicionydireccion, int posicionpreciox, int posicionprecioy,
                          int posiciondescripcionx, int posiciondescripciony ){

        String[] colordeletras = color_letter.split("/");

        Bitmap image1=redimensionarImagen(src,1000,1000);
        Bitmap image2=marca;
        Paint npaint = new Paint();
        Paint.Style estilo = Paint.Style.FILL_AND_STROKE;

        // Bitmap image3=BitmapUtils.getResizedBitmap(BitmapUtils.de codeBase64(Lie.GetBanner().GetImagen()),Lie.GetBan ner().GetTamano().GetWidth(),Lie.GetBanner().GetTa mano().GeHeight());
        result = Bitmap.createBitmap(image1.getWidth(), image1.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);//Create the canvas to your image
        Rect srcRect = new Rect(0, 0, image1.getWidth(), image1.getHeight());
        Rect dstRect = new Rect(srcRect);
        Rect srcRect2 = new Rect(0, 0, image2.getWidth(), image2.getHeight());
        Rect dstRect2 = new Rect(srcRect);
        npaint.setAntiAlias(true);
        canvas.drawBitmap(image1, srcRect, dstRect,npaint);
       // dstRect.offset(image1.getWidth(), 0);
        canvas.drawBitmap(image2, srcRect2, dstRect2, null);


        //recycle


        int color = Color.rgb(Integer.valueOf(colordeletras[0]),Integer.valueOf(colordeletras[1]),Integer.valueOf(colordeletras[2]));

        //titulo
       dibujartexto(canvas,npaint,titulo,posiciontitulox,posiciontiuloy,90, estilo, color);
        //dirección
        dibujartexto(canvas,npaint,"Dirección: "+dirección,posicionxdireccion,posicionydireccion,40,estilo,color);
        //telefonos
        dibujartexto(canvas,npaint,"Teléfono: "+telefonos,posicioxtelef,posicioytelef,40,estilo,color);
        //dibujar precio
       dibujarprecio(canvas,posicionpreciox,posiciondescripciony,precio,descrip);
        //dibujar descripción
        //dibujardescripcion(canvas,posiciondescripcionx,posiciondescripciony,descrip);
       dibujartexto(canvas,npaint,descrip,400,400,30,estilo,color);



        return result;



    }


    public void dibujardescripcion(Canvas canvas, int positionx, int positiony, String decrip){
        Paint paint = new Paint();
        paint.setColor(Color.rgb(255,255,255));
        paint.setAntiAlias(true);
        paint.setTextSize(100);
        Rect rect = new Rect(positionx-20,positiony-20,positionx-20,positiony-20);


        paint.setColor(Color.argb(1,255,255,255));
        canvas.drawRect(rect,paint);
        paint.setColor(Color.rgb(0,0,0));
        //canvas.drawText(precio,positionx,positiony,paint);


    }

    public void dibujarprecio(Canvas canvas, int positionx, int positiony, String precio, String descrip){
        Paint paint = new Paint();
        paint.setColor(Color.rgb(255,255,255));
        paint.setAntiAlias(true);
        paint.setTextSize(100);
        canvas.drawCircle(positionx+120,positiony-30,140,paint);
        paint.setColor(Color.rgb(0,0,0));
        canvas.drawText(precio,positionx,positiony,paint);


    }

    public void dibujartexto(Canvas canvas, Paint paint, String texto, int positionx, int positiony, int size, Paint.Style estilo, int color){

        float tamañoletras = texto.length() * size;
       // String txt[] = ajustartexto(tamañoletras,texto,18,size);

        String txt[] = texto.split("//");
        paint.setStyle(estilo);
        texto.length();

      //  canvas.translate(positionx, positiony);

        // make the entire canvas white
        canvas.drawColor(Color.TRANSPARENT);

        // draw some text using STROKE style
        paint.setStyle(estilo);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        paint.setDither(false);
        paint.setAntiAlias(true);
        paint.setTextSize(size);
        paint.setStrokeJoin(Paint.Join.MITER);

        float lineSpace = size * 0.2f;

        for (int i = 0; i < txt.length; ++i) {
            canvas.drawText(txt[i], positionx, positiony + (size + lineSpace) * i, paint);
        }
       // canvas.drawText(texto, 0, 0, paint);


    }








    public Bitmap redimensionarImagen(Bitmap mBitmap, float newWidth, float newHeigth){
        //Redimensionamos
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeigth) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
    }


    double getDPFromPixels(double pixels, int densidad) {
        DisplayMetrics metrics = new DisplayMetrics();
      //  getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.d("densidad", String.valueOf(densidad));
        switch(densidad){
            case DisplayMetrics.DENSITY_LOW:
                pixels = pixels * 1.5;
                Log.d("densidad", String.valueOf(densidad));
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                Log.d("densidad", String.valueOf(densidad));
                //pixels = pixels * 1;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                Log.d("densidad", String.valueOf(densidad));
                pixels = pixels * 0.75;
                break;
        }
        return pixels;
    }




}