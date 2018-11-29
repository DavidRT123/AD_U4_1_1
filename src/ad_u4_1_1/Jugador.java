/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_u4_1_1;

/**
 *
 * @author mdfda
 */
public class Jugador {
    
    private String nacionalidad;
    private String nombre;
    private int puntuacion;
    private int nivel;
    private int horas;
    
    public Jugador(){
    
    }

    public String getNacionalidad() {
        return obtenerNacionalidad();
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
    private String obtenerNacionalidad(){
        String nombreLargo = "";
        switch(nacionalidad){
            case "ES":
                nombreLargo = "Española";
                break;
            case "US":
                nombreLargo = "Estadounidense";
                break;
            case "CO":
                nombreLargo = "Colombiana";
                break;
            case "FR":
                nombreLargo = "Francesa";
                break;
            case "IT":
                nombreLargo = "Italiano";
                break;
        }
        return nombreLargo;
    }
}
