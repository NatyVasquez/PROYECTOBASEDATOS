/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_archivos;

import java.util.Calendar;

/**
 *
 * @author Andres Guevara
 * @author Camilo Suarez
 * @author Juan Estevan Santiago
 * @author Angie Cobo
 */
public class Composicion {
    
    private int dia,mes,año,diaActual,mesActual,añoActual,edad;
    
    public Composicion(){
        dia = mes = año = edad = 0;
        diaActual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mesActual = Calendar.getInstance().get(Calendar.MONTH) + 1;
        añoActual = Calendar.getInstance().get(Calendar.YEAR);
    }
    
    public Composicion(int dia, int mes, int año){
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        diaActual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mesActual = Calendar.getInstance().get(Calendar.MONTH) + 1;
        añoActual = Calendar.getInstance().get(Calendar.YEAR);
        edad = añoActual - año + (mesActual > mes ?  0  : ((mesActual == mes && diaActual >= dia) ? 0  : -1));
    }
    
    public int getEdad(){
        return edad;
    }
    
    public void setFnacimiento(int dia, int mes, int año){
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        edad = añoActual - año + (mesActual > mes ?  0  : ((mesActual == mes && diaActual >= dia) ? 0  : -1));
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getDiaActual() {
        return diaActual;
    }

    public void setDiaActual(int diaActual) {
        this.diaActual = diaActual;
    }

    public int getMesActual() {
        return mesActual;
    }

    public void setMesActual(int mesActual) {
        this.mesActual = mesActual;
    }

    public int getAñoActual() {
        return añoActual;
    }

    public void setAñoActual(int añoActual) {
        this.añoActual = añoActual;
    }
    
    public String getFnacimiento(){
        return dia+"/"+mes+"/"+año;
    }
    
    public String getFregistro(){
        return diaActual+"/"+mesActual+"/"+añoActual;
    }

    @Override
    public String toString() {
        return dia+"/"+mes+"/"+año+";"+edad+";"+diaActual+"/"+mesActual+"/"+añoActual;
     }
    
}
