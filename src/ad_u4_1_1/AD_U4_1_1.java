/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_u4_1_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author mdfda
 */
public class AD_U4_1_1 {

    //Ruta absoluta donde se encuentra el archivo xml
    private final String RUTA = "C:\\Users\\mdfda\\Documents\\NetBeansProjects\\AD_U4_1_1\\src\\ad_u4_1_1\\documento\\";
    private NodeList jugadores;
    private Document documento;
    private Scanner sc;

    public AD_U4_1_1() {
        int opcion;
        sc = new Scanner(System.in);

        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

        try {
            punto7();

            DocumentBuilder builder = factoria.newDocumentBuilder();
            documento = builder.parse(new File(RUTA + "Jugadores.xml"));
            jugadores = documento.getElementsByTagName("Jugador");
            do {
                System.out.println("\nSelecciona opción (número): ");
                System.out.println("1. Obtener estadísticas de los jugadores");
                System.out.println("2. Cambiar nivel de jugador");
                System.out.println("3. Sumarle puntuación a jugador");
                System.out.println("4. Eliminar jugador");
                System.out.println("5. Sumar a campo");
                System.out.println("0. Salir");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        punto2();
                        break;
                    case 2:
                        punto3();
                        break;
                    case 3:
                        punto4();
                        break;
                    case 4:
                        punto5();
                        break;
                    case 5:
                        punto6();
                        break;
                    case 0:
                        System.out.println("\nBYE, BYE");
                        break;
                    default:
                        System.err.println("Tu elección esta equivocada. Vuelvela a hacer pero bien");
                        ;
                }
            } while (opcion != 0);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(AD_U4_1_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Todopoderoso método main que crea una nueva instancia de esta misma clase
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AD_U4_1_1();
    }

    /**
     * Método para visualizar las estadísticas de cada jugador
     */
    public void punto2() {
        int i;
        for (i = 0; i < jugadores.getLength(); i++) {
            System.out.println("************************************");
            System.out.println("***** CODIGO JUGADOR: " + i + " *****");
            listarJugador(i);
            System.out.println("************************************\n");
        }
    }

    /**
     * Método para cambiar el nivel de un determinado jugador (se cambia de
     * nivel sumando uno al nivel actual)
     */
    public void punto3() {
        int jugador, nivel;
        //Llamo para listar los jugadores y que elija el que quiera
        jugador = seleccionarJugador();
        nivel = Integer.parseInt(getNivel(jugador));
        //Le sumo 1 y lo seteo de nuevo
        nivel++;
        setNivel(jugador, String.valueOf(nivel));
        System.out.println("El jugador " + getNombre(jugador) + " ha subido 1 nivel (Nivel: "+ getNivel(jugador) +")\n");
    }

    /**
     * Método para sumar la puntuación obtenida (dato obtenido desde teclado) a
     * su acumulado.
     */
    public void punto4() {
        int jugador, puntuacion = 0;
        //Llamo para listar los jugadores y que elija el que quiera
        jugador = seleccionarJugador();
        System.out.println("Introduce la puntuación que le quieres sumar al jugador " + getNombre(jugador));
        puntuacion = sc.nextInt();

        //Obtengo el valor antiguo y le sumo la puntuación introducida
        puntuacion += Integer.parseInt(getPuntuacion(jugador));
        
        //Setear nueva puntuación 
        setPuntuacion(jugador, String.valueOf(puntuacion));
        
        System.out.println("El jugador " + getNombre(jugador) + " ha aumentado su puntuación hasta " + getPuntuacion(jugador) + "\n");
    }

    /**
     * Método para eliminar un determinado jugador
     */
    public void punto5() {
        int jugador;
        System.out.println("Selecciona el jugador que quieres eliminiar: ");
        jugador = seleccionarJugador();
        System.out.println("\nEl siguiente jugador ha sido eliminado: ");
        listarJugador(jugador);
        //Elimino el nodo del documento (.getDocumentElement() = obtener nodo raiz)
        documento.getDocumentElement().removeChild(jugadores.item(jugador));
    }

    /**
     * Método que combina los puntos 3 y 4 y que permite sumar cualquier cantidad a un determinado 
     * campo numérico de un determinado jugador
     */    
    public void punto6() {
        int jugador, horas = 0, seleccion = 1;
        jugador = seleccionarJugador();
        System.out.println("Selecciona el campo al que deseas sumarle una cantidad: ");
        System.out.println("1. Puntuación");
        System.out.println("2. Nivel");
        System.out.println("3. Horas");
        seleccion = sc.nextInt();
        while (seleccion > 3 || seleccion < 1) {
            System.err.println("Elige entre 1 y 3: ");
            seleccion = sc.nextInt();
        }

        switch (seleccion) {
            case 1:
                punto4();
                break;
            case 2:
                punto3();
                break;
            case 3:
                System.out.println("Introduce las horas que le quieres sumar al jugador " + getNombre(jugador));
                horas = sc.nextInt();
                //Obtengo el valor antiguo y le sumo la puntuación introducida, después lo parseo a String
                setHoras(jugador, String.valueOf(Integer.parseInt(getHoras(jugador)) + horas));
                System.out.println("El jugador " + getNombre(jugador) + " ha aumentado su horas hasta " + getHoras(jugador) + "\n");
                break;
        }
    }

    /**
     * Método para cargar un fichero xml existente o crear directamente uno nuevo introduciendo
     * el código a lo bestia 
     */
    public void punto7() {
        try {
            String respuesta;
            System.out.println("Introduce el nombre del archivo a cargar: ");
            respuesta = sc.nextLine();

            File xml = new File(RUTA + respuesta + ".xml");
            if (!xml.exists()) {
                System.err.println("El nombre del archivo introducido no existe");
                System.out.println("Introduce el código del archivo xml (a lo bestia y sin errores, ¿eh? que nos conocemos :)");
                respuesta = sc.nextLine();
                //Creación a lo bestia de un nuevo xml
                BufferedWriter bW = new BufferedWriter(new FileWriter(RUTA + "Jugadores.xml"));
                bW.write(respuesta);
                bW.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(AD_U4_1_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para transformar los nombres de los nodos de cada jugador 
     * en una cadena más elegante visualmente hablando
     * Una pijada en toda regla
     * @param jugador 
     */
    public void listarJugador(int jugador) {
        //Acceder al nodo jugador, despues a sus atributos, después al atributo nacionalidad y después obtener el contenido de este
        System.out.println("Nacionalidad: " + getNacionalidad(jugador));
        System.out.println("Nombre: " + getNombre(jugador));
        System.out.println("Horas: " + getHoras(jugador));
        System.out.println("Nivel: " + getNivel(jugador));
        System.out.println("Puntuación: " + getPuntuacion(jugador));

    }

    /**
     * Método que, dado un valor introducido por teclado, se encarga de que este 
     * dentro del rango de jugadores y devuelve la selección
     * @return 
     */
    private int seleccionarJugador() {
        //Lista los jugadores junto con sus datos y un código para seleccionarlos
        punto2();
        int jugador = 0;
        System.out.println("Selecciona un jugador (nº CÓDIGO JUGADOR): ");
        jugador = sc.nextInt();
        while (jugador > jugadores.getLength() || jugador < 0) {
            System.err.println("Selecciona un jugador válido (entre 0 y " + jugadores.getLength());
            jugador = sc.nextInt();
        }

        return jugador;
    }
    
    /**
     * Método para abreviarse el tocho de código y obtener el nombre de manera sencilla
     * @param jugador
     * @return 
     */
    private String getNombre(int jugador){
        return jugadores.item(jugador).getChildNodes().item(0).getTextContent();
    }
    
    /**
     * Método para abreviarse el tocho de código y obtener las horas de manera sencilla
     * @param jugador
     * @return 
     */
    private String getHoras(int jugador){
        return jugadores.item(jugador).getChildNodes().item(1).getTextContent();
    }
    
    /**
     * Método para abreviarse el tocho de código y obtener el nivel de manera sencilla
     * @param jugador
     * @return 
     */
    private String getNivel(int jugador){
        return jugadores.item(jugador).getChildNodes().item(2).getTextContent();
    }
    
    /**
     * Método para abreviarse el tocho de código y obtener la puntuacion de manera sencilla
     * @param jugador
     * @return 
     */
    private String getPuntuacion(int jugador){
        return jugadores.item(jugador).getChildNodes().item(3).getTextContent();
    }
    
    /**
     * Método para abreviarse el tocho de código y obtener la nacionalidad de manera sencilla
     * @param jugador
     * @return 
     */
    private String getNacionalidad(int jugador){
        return jugadores.item(jugador).getAttributes().item(0).getTextContent();
    }
    
    /**
     * Método para abreviarse el tocho de código y setear el nombre de manera sencilla
     * @param jugador
     * @param nombre
     */
    private void setNombre(int jugador, String nombre){
        jugadores.item(jugador).getChildNodes().item(0).setTextContent(nombre);
    }
    
    /**
     * Método para abreviarse el tocho de código y setear las horas de manera sencilla
     * @param jugador
     * @param nivel
     */
    private void setHoras(int jugador, String horas){
        jugadores.item(jugador).getChildNodes().item(1).setTextContent(horas);
    }
    
    /**
     * Método para abreviarse el tocho de código y setear el nivel de manera sencilla
     * @param jugador
     * @param nivel
     */
    private void setNivel(int jugador, String nivel){
        jugadores.item(jugador).getChildNodes().item(2).setTextContent(nivel);
    }
    
    /**
     * Método para abreviarse el tocho de código y setear la puntuacion de manera sencilla
     * @param jugador
     * @param puntuacion
     */
    private void setPuntuacion(int jugador, String puntuacion){
        jugadores.item(jugador).getChildNodes().item(3).setTextContent(puntuacion);
    }
    
    /**
     * Método para abreviarse el tocho de código y setear la nacionalidad de manera sencilla
     * @param jugador
     * @param nacionalidad 
     */
    private void setNacionalidad(int jugador, String nacionalidad){
        jugadores.item(jugador).getAttributes().item(0).setTextContent(nacionalidad);
    }
}
