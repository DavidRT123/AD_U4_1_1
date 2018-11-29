/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_u4_1_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    private ArrayList<Jugador> listaJugadores;
    private Document documento;
    private boolean cambios;
    private Scanner sc;

    public AD_U4_1_1() {
        int opcion;
        cambios = false;
        sc = new Scanner(System.in);
        listaJugadores = new ArrayList<Jugador>();

        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

        try {
            punto7();

            DocumentBuilder builder = factoria.newDocumentBuilder();
            documento = builder.parse(new File(RUTA + "Jugadores.xml"));
            //A partir del documento xml convertir los nodos en objetos de Jugador y guardarlos en listaJugadores
            rellenarJugadores();
            do {
                System.out.println("Selecciona opción (número): ");
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
                        System.out.println("BYE, BYE");
                        if (cambios) {
                            guardarCambios();

                        }
                        break;
                    default:
                        System.err.println("Tu elección esta equivocada. Vuelvela a hacer pero bien");
                        ;
                }
            } while (opcion != 0);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AD_U4_1_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AD_U4_1_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AD_U4_1_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AD_U4_1_1();
    }

    /**
     * Función para visualizar las estadísticas de cada jugador
     */
    public void punto2() {
        int i;
        for (i = 0; i < listaJugadores.size(); i++) {
            System.out.println("************************************");
            System.out.println("***** CODIGO JUGADOR: " + i + "*****");
            listarJugador(listaJugadores.get(i));
            System.out.println("************************************");
        }
    }

    /**
     * Función para cambiar el nivel de un determinado jugador (se cambia de
     * nivel sumando uno al nivel actual)
     */
    public void punto3() {
        int jugador;
        //Llamo para listar los jugadores y que elija el que quiera
        jugador = seleccionarJugador();
        //Obtengo el valor antiguo y le sumo uno
        listaJugadores.get(jugador).setNivel(listaJugadores.get(jugador).getNivel() + 1);
        System.out.println("El jugador " + listaJugadores.get(jugador).getNombre() + " ha subido 1 nivel");
    }

    /**
     * Función para sumar la puntuación obtenida (dato obtenido desde teclado) a
     * su acumulado.
     */
    public void punto4() {
        int jugador, puntuacion = 0;
        //Llamo para listar los jugadores y que elija el que quiera
        jugador = seleccionarJugador();
        System.out.println("Introduce la puntuación que le quieres sumar al jugador " + listaJugadores.get(jugador));
        puntuacion = sc.nextInt();
        
        //Obtengo el valor antiguo y le sumo la puntuación introducida
        listaJugadores.get(jugador).setPuntuacion(listaJugadores.get(jugador).getPuntuacion() + puntuacion);
        System.out.println("El jugador " + listaJugadores.get(jugador).getNombre() + " ha aumentado su puntuación hasta " + listaJugadores.get(jugador).getPuntuacion());
    }

    public void punto5() {
        int jugador;
        System.out.println("Selecciona el jugador que quieres eliminiar: ");
        jugador = seleccionarJugador();
        System.out.println("El siguiente jugador ha sido eliminado: ");
        listarJugador(listaJugadores.get(jugador));
        listaJugadores.remove(jugador);
    }

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
                System.out.println("Introduce las horas que le quieres sumar al jugador " + listaJugadores.get(jugador));
                horas = sc.nextInt();
                //Obtengo el valor antiguo y le sumo la puntuación introducida
                listaJugadores.get(jugador).setHoras(listaJugadores.get(jugador).getHoras() + horas);
                System.out.println("El jugador " + listaJugadores.get(jugador).getNombre() + " ha aumentado su horas hasta " + listaJugadores.get(jugador).getHoras());
                break;
        }
    }

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
                BufferedWriter bW = new BufferedWriter(new FileWriter("RUTAJugadores.xml"));
                bW.write(respuesta);
                bW.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(AD_U4_1_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarCambios() {

    }

    public void rellenarJugadores() {
        int i, j;
        Jugador jugador;
        NodeList jugadores = documento.getElementsByTagName("Jugador"), propiedades;

        for (i = 0; i < jugadores.getLength(); i++) {
            propiedades = jugadores.item(i).getChildNodes();
            jugador = new Jugador();
            //Bucle para recorrer los hijos del nodo jugador y setear las propiedades del objeto jugador
            for (j = 0; j < propiedades.getLength(); j++) {
                switch (propiedades.item(j).getNodeName()) {
                    case "nombre":
                        jugador.setNombre(propiedades.item(j).getTextContent());
                        break;
                    case "horas_jugadas":
                        jugador.setHoras(Integer.parseInt(propiedades.item(j).getTextContent()));
                        break;
                    case "nivel":
                        jugador.setNivel(Integer.parseInt(propiedades.item(j).getTextContent()));
                        break;
                    case "puntuacion":
                        jugador.setPuntuacion(Integer.parseInt(propiedades.item(j).getTextContent()));
                        break;
                }
            }
            //Accedo a los atributos del jugador, en concreto a la nacionalidad, y la seteo
            jugador.setNacionalidad(jugadores.item(i).getAttributes().getNamedItem("nacionalidad").getTextContent());
            listaJugadores.add(jugador);
        }
    }

    public void listarJugador(Jugador jugador) {
        System.out.println("Nombre: " + jugador.getNombre());
        System.out.println("Nacionalidad: " + jugador.getNacionalidad());
        System.out.println("Puntuación: " + jugador.getPuntuacion());
        System.out.println("Nivel: " + jugador.getNivel());
        System.out.println("Horas: " + jugador.getHoras());
    }

    private int seleccionarJugador() {
        punto2();
        int jugador = 0;
        System.out.println("Selecciona un jugador (nº CÓDIGO JUGADOR): ");
        jugador = sc.nextInt();
        while (jugador > listaJugadores.size() || jugador < 0) {
            System.err.println("Selecciona un jugador válido (entre 0 y " + listaJugadores.size());
            jugador = sc.nextInt();
        }
        
        return jugador;
    }
}
