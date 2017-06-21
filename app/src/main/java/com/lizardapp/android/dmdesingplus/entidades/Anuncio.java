package com.lizardapp.android.dmdesingplus.entidades;

/**
 * Created by joserojas on 8/5/17.
 */

public class Anuncio {

    private String encabezado;
    private String telefono;
    private String direccion;
    private String precio;
    private String descripción;


    public String getEncabezado(){return  encabezado;}
    public void  setEncabezado(String encabezado){
        this.encabezado = encabezado;
    }

    public String getTelefono(){
        return  telefono;
    }
    public void  setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getDireccion(){
        return  direccion;
}
    public void  setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getPrecio(){
        return  precio;
    }
    public void  setPrecio(String precio){
        this.precio = precio;
    }

    public String getDescripción(){
        return  descripción;
    }
    public void  setDescripción(String descripción){
        this.descripción = descripción;
    }





}
