/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MIGUEL  MARQUEZ
 */
import java.awt.Font;
import java.util.Stack;
import javax.swing.JTextArea;

public class Pila {

    private Stack<String> pila;

    public Pila() {
        pila = new Stack<>();
    }

    public void apilar(String elemento) {
        pila.push(elemento);
    }

  

    public void mostrarContenido(JTextArea a) {
        Font font = new Font("Arial", Font.PLAIN, 20); // Cambiar la fuente y el tamaño
        a.setFont(font);
        System.out.println("Contenido de la pila:");
        for (String elemento : pila) {
            a.append(elemento);
            a.append("\n");
            System.out.println(elemento);
        }
    }

    public boolean estaVacia() {
        return pila.isEmpty();
    }
}
