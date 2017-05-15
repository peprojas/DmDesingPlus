package com.lizardapp.android.dmdesingplus;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


//import org.apache.http.client;

/**
 * Created by joserojas on 5/4/17.
 */

public class TareaInsertarDatosUser extends AsyncTask <String,String,String>{

    //JSONParser


    URL url =  null;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;



    @Override
    protected String doInBackground(String... strings) {
      //  JSONObject json = new jParser

        JSONObject parametros = new JSONObject();
        try {

           parametros.put("nombre","Jose ttt");
            parametros.put("correo","ppp@pp.com");
            parametros.put("empresa","lizrd");
            parametros.put("nombreusuario","prueba");
            parametros.put("telefono","999999");
            url = new URL("http://201.183.231.7:5000/usuario");
            Log.d("coxion)","entra a conectar");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
         //   urlConnection.setReadTimeout(15000 /* milliseconds */);
          //  urlConnection.setConnectTimeout(15000 /* milliseconds */);
           urlConnection.setRequestMethod("POST");
           // urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            Log.d("coxion)","se conecto");


            //OutputStream outputStream = urlConnection.getOutputStream();
            OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
           // InputStream inputStream = urlConnection.getInputStream();

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

           // urlConnection.setFixedLengthStreamingMode(getPostDataString(parametros).getBytes().length);
            bufferedWriter.write(getPostDataString(parametros).toString());
          //  outputStream.write((parametros).toString());

           bufferedWriter.flush();
            outputStream.flush();
            bufferedWriter.close();

            //urlConnection.connect();

            int responseCode=urlConnection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String line ="";

                while ((line = bufferedReader.readLine())!= null){

                    buffer.append(line);
                    break;
                }

                bufferedReader.close();
                return buffer.toString();


            }

            else{

                return new String("false : "+responseCode);

            }





        }  catch (IOException e) {
            e.printStackTrace();
            Log.d("conexion","error en url conection: "+e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("conexion","error en buffer: "+e.toString());
        } finally {
            if (bufferedReader != null){

                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;


    }

  @Override
    protected  void  onPostExecute(String Result) {
        super.onPostExecute(Result);
        Log.d("result",Result);


    }




    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }

        Log.d("from",result.toString());
        return result.toString();
    }


}
