package com.lizardapp.android.dmdesingplus.entidades;

/**
 * Created by joserojas on 25/5/17.
 */

public class Recurso {

    private int id;
    private String name;
    private int type;
    private String url;
    private int position1;
    private int position2;
    private int position3;
    private int position4;
    private  int position5;
    private int position6;
    private int position7;
    private int position8;
    private int position9;
    private int position10;


    private int font_heigt1;
    private int font_heigt2;
    private int font_heigt3;
    private int font_heigt4;
    private boolean status;
    private int id_mini;
    private String colorrgb;


    public int getId(){return  id;}
    public void  setId(int id){this.id = id;}

    public String getName(){
        return  name;
    }
    public void  setName(String name){this.name = name;}

    public int getType(){
        return  type;
    }
    public void  setType(int type){this.type = type;}

    public String getUrl(){ return  url;}
    public void  setUrl(String url){this.url = url;}

    public int getPosition1(){return  position1;}
    public void  setPosition1(int position1){this.position1 = position1;}

    public int getPosition2(){return  position2;}
    public void  setPosition2(int position2){this.position2 = position2;}

    public int getPosition3(){return  position3;}
    public void  setPosition3(int position3){this.position3 = position3;}

    public int getPosition4(){return  position4;}
    public void  setPosition4(int position4){this.position4 = position4;}

    public int getPosition5(){return  position5;}
    public void  setPosition5(int position5){this.position5= position5;}

    public int getPosition6(){return  position6;}
    public void  setPosition6(int position6){this.position6 = position6;}


    public int getFont_heigt1(){return  font_heigt1;}
    public void  setFont_heigt1(int font_heigt1){this.font_heigt1 = font_heigt1;}

    public int getFont_heigt2(){return  font_heigt2;}
    public void  setFont_heigt2(int font_heigt2){this.font_heigt2 = font_heigt2;}

    public int getFont_heigt3(){return  font_heigt3;}
    public void  setFont_heigt3(int font_heigt3){this.font_heigt3 = font_heigt3;}

    public int getFont_heigt4(){return  font_heigt4;}
    public void  setFont_heigt4(int font_heigt4){this.font_heigt4 = font_heigt4;}

    public boolean getStatus(){return  status;}
    public void  setStatus1(boolean status){this.status = status;}

    public int getIdmini(){return  id_mini;}
    public void  setIdmini(int id_mini){this.id_mini = id_mini;}

    public String getColorrgb(){ return  colorrgb;}
    public void  setColorrgb(String colorrgb){this.colorrgb = colorrgb;}

    public int getPosition7(){return  position7;}
    public void  setPosition7(int position7){this.position7= position7;}

    public int getPosition8(){return  position8;}
    public void  setPosition8(int position8){this.position8 = position8;}

    public int getPosition9(){return  position9;}
    public void  setPosition9(int position9){this.position9= position9;}

    public int getPosition10(){return  position10;}
    public void  setPosition10(int position10){this.position10 = position10;}






    public Recurso  setearObjeto(int drawable,String nombre, int idmipmap, String colorargb,
                                 int posiciontitulox, int posiciontituloy, int posiciondirecx, int posiciondirecy,
                                 int postiontelefx, int positiontelefy, int pospreciox, int posprecioy, int descpx, int descpy){

        Recurso recurso = new Recurso();

        recurso.setId(drawable);
        recurso.setName(nombre);
        recurso.setUrl("xxx");
        recurso.setType(1);
        recurso.setStatus1(true);
        recurso.setStatus1(true);
        recurso.setPosition1(posiciontitulox);
        recurso.setPosition2(posiciontituloy);
        recurso.setPosition3(posiciondirecx);
        recurso.setPosition4(posiciondirecy);
        recurso.setPosition5(postiontelefx);
        recurso.setPosition6(positiontelefy);
        recurso.setPosition7(pospreciox);
        recurso.setPosition8(posprecioy);
        recurso.setPosition9(descpx);
        recurso.setPosition10(descpy);
        recurso.setFont_heigt1(100);
        recurso.setFont_heigt2(40);
        recurso.setFont_heigt3(40);
        recurso.setFont_heigt4(40);
        recurso.setIdmini(idmipmap);
        recurso.setColorrgb(colorargb);

        return recurso;



    }

}
