/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MIGUEL  MARQUEZ
 */
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MiPanel extends JPanel {

    Pila pila = new Pila();
    int pp = 0, vida = 3, puntos = 0, puntosaux = 0;
    private yo yo;
    private Peñon peñon;
    Nodo[] nodosCaidos;
    ListaEnlazada lista = new ListaEnlazada();
    ArrayList<Enemigo> enemigos = new ArrayList<>();
    Timer timer = new Timer(100, e -> {
        for (Enemigo enemigo : enemigos) {
            enemigo.moverAbajo();
        }
        peñon.moverArriba();
        repaint();
    });
    int numeroJugador = obtenerNuevoNumero();

    //Esta variable eleccionJugador es el inidice del nodo(enemigo)
    //asi que como tal el metodo obtenerNuevoNodo tendras que ponerlo tu con lo grafico
    //Dependiendo de a cual nodo le de el usuario
    //Necesaria
    public static int evaluarExpresion(String expresion) { //Convirtiendo la expresion a numero
        String[] operandos = expresion.split("\\+");
        int resultado = Integer.parseInt(operandos[0]) + Integer.parseInt(operandos[1]);
        return resultado;
    }

    //Si la expresion corresponde al numero entonces se devuelve este numero
    public static boolean esExpresionCorrecta(int numero, String expresion) {
        int resultado = evaluarExpresion(expresion);
        return numero == resultado;
    }

    //Necesaria
    private static int obtenerNuevoNumero() {
        Random rand = new Random();
        return rand.nextInt(2, 18);  // Generar un número aleatorio del 1 al 18 (ajusta según tus necesidades).
    }

    private static int obtenerNuevoNumeroo(Nodo[] nodosCaidos, int i) {
        Random rand = new Random();

        int numeroAleatorio = rand.nextInt(5); // Genera un número aleatorio entre 0 y 4
        while (i == numeroAleatorio) {
            numeroAleatorio = rand.nextInt(5);
        }
        return evaluarExpresion(nodosCaidos[numeroAleatorio].expresion);
    }

    //No necesaria, cambiala quitala segun tus necesidades, solo recuerda que eleccionJugador es el 
    //indice del nodo
    public MiPanel() {
        System.out.println("DFGHJKL");
        yo = new yo();
        peñon = new Peñon(yo.getX(), yo.getY());
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                String expresion = i + "+" + j;
                lista.agregarElemento(expresion);
            }
        }

        System.out.println("Mi valor:" + yo.getVal());
        nodosCaidos = lista.seleccionarNodosAleatorios(numeroJugador);
        lista.compararNodos(nodosCaidos, numeroJugador);
        for (int i = 0; i < 5; i++) {
            enemigos.add(new Enemigo(10 + (100 * i), i, nodosCaidos[i].expresion)); // La posición x de cada enemigo es distinta
            System.out.println("[" + i + "] " + nodosCaidos[i].expresion);
        }
        numeroJugador = obtenerNuevoNumeroo(nodosCaidos, 6);
        yo.setVal(numeroJugador);
        timer.start();
        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yo.keyPressed(evt);
                repaint(); // Asegura que el panel sea actualizado después de cada movimiento
                revalidate();
                if (peñon.isVisible() == false && evt.getKeyCode() == KeyEvent.VK_SPACE) {
                    peñon.setX(yo.getX() + 30);
                    peñon.setVisible(true);
                }
            }
        });
    }

    public Enemigo encontrarEnemigoMasCercano() {
        Enemigo enemigoMasCercano = enemigos.get(0); // Empezamos asumiendo que el primer enemigo es el más cercano
        int distanciaMinima = Math.abs(enemigoMasCercano.getX() - yo.getX()); // Distancia mínima inicial

        for (Enemigo enemigo : enemigos) {
            int distanciaActual = Math.abs(enemigo.getX() - yo.getX());
            if (distanciaActual < distanciaMinima) {
                distanciaMinima = distanciaActual; // Actualiza la distancia mínima
                enemigoMasCercano = enemigo; // Actualiza el enemigo más cercano
            }
        }

        return enemigoMasCercano;
    }

    void mostrar(int i, int eleccionJugador, Enemigo enemigo) {
        if (i == 0) {
            System.out.println("Valor del " + eleccionJugador + " es de:" + enemigo.getPro());
        }
    }

    private void verificarColisionEnemigo() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.getY() >= 320) {
                abrirNuevoGUI();
                // Cierra el GUI actual
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
                break; // Detiene el bucle una vez que cierra el GUI actual
            }
        }
    }

    // Método para abrir el nuevo GUI3
    private void abrirNuevoGUI() {
        // Código para crear e iniciar GUI3
        // Suponiendo que GUI3 es una clase que extiende JFrame
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    currentFrame.dispose();
        GUI3 gui3 = new GUI3(pila);
                //pilaMiPanel.mostrarContenido(a);
        gui3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui3.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int eleccionJugador = 0;
        super.paintComponent(g);
        yo.pintar(g);
        for (Enemigo enemigo : enemigos) {
            enemigo.pintar(g);
        }
        if (peñon.isVisible()) {
            peñon.pintar(g);
        }
        g.setColor(Color.WHITE);
        g.drawString("Vida: " + vida, 10, 20);

        // Dibuja el valor de puntos en la esquina superior derecha
        FontMetrics metrics = g.getFontMetrics();
        int stringWidth = metrics.stringWidth("Puntos: " + puntos);
        g.drawString("Puntos: " + puntos, getWidth() - stringWidth - 10, 20);

        // Resto del código para dibujar los objetos
        // Verifica si hay colisión entre el peñon y los enemigos
        for (Enemigo enemigo : enemigos) {
            mostrar(pp, eleccionJugador, enemigo);
            if (peñon.isVisible() && hayColisión(peñon, enemigo)) {
                System.out.println("hola" + nodosCaidos[eleccionJugador].expresion);
                if (esExpresionCorrecta(numeroJugador, nodosCaidos[eleccionJugador].expresion)) {
                    puntos++;
                    puntosaux++;
                    if (puntosaux >= 10) {
                        puntosaux = 0;
                        vida++;
                    }
                    enemigo.setY(-90);
                    System.out.println("¡Correcto! El nodo desaparece.");
                    Random rand = new Random();
                    int azar = rand.nextInt(1, 5);
                    // Reemplazar el nodo seleccionado por uno nuevo
                    numeroJugador = obtenerNuevoNumeroo(nodosCaidos, eleccionJugador);

                    if (azar == 1) {
                        nodosCaidos[eleccionJugador] = lista.obtenerNodoAlAzar();
                        System.out.println("Entro a 1");
                    } else {
                        Enemigo eaxu = encontrarEnemigoMasCercano();
                        nodosCaidos[eleccionJugador].expresion = eaxu.getPro();
                        System.out.println("Entro a 2");
                    }
                    enemigo.setPro(nodosCaidos[eleccionJugador].expresion);
                    yo.setVal(numeroJugador);
                    System.out.println("Mi valor:" + yo.getVal());
                    peñon.setVisible(false);
                    peñon.setY(yo.getY());
                    pp = 0;
                    // Otra lógica si se detecta la colisión
                    for (int i = 0; i < 5; i++) {
                        if (nodosCaidos[i] != null) {
                            System.out.println("[" + i + "] " + nodosCaidos[i].expresion);
                        }
                    }
                    break;
                } else {
                    System.out.println("¡Incorrecto! Intenta de nuevo.");
                    pila.apilar(nodosCaidos[eleccionJugador].expresion + "≠" + numeroJugador);
                    vida--;
                    if (vida < 1) {
                        abrirNuevoGUI();
                    }
                }
                peñon.setVisible(false);
                peñon.setY(yo.getY());
            }
            eleccionJugador++;
        }
        pp++;
        verificarColisionEnemigo();
    }

    // Método para verificar la colisión entre el Peñon y un Enemigo
    private boolean hayColisión(Peñon peñon, Enemigo enemigo) {
        return peñon.getX() < enemigo.getX() + enemigo.getAncho()
                && peñon.getX() + peñon.getAncho() > enemigo.getX()
                && peñon.getY() < enemigo.getY() + enemigo.getAlto()
                && peñon.getY() + peñon.getAlto() > enemigo.getY();
    }
}
