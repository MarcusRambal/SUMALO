/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MIGUEL  MARQUEZ
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Enemigo {

    private int x, y = -90, ancho = 90, alto = 80, i;
        private ImageIcon imagen = new ImageIcon("C:\\Primer semestre\\POO\\SUMAL0\\src\\newpackage\\1163 (1).png"); // Reemplaza con la ruta de tu imagen

    //Image img;
    String pro;
    Random ran = new Random();
private BufferedImage image;
    public Enemigo(int x, int i, String pro) {
        this.x = x;
        this.i = i;
        this.pro = pro;
        //img = new ImageIcon(getClass().getResource("/newpackage/carro2.png")).getImage();
    }


public Enemigo(int x) {
        this.x = x;
    }

    public void moverAbajo() {
        // Mueve el cuadrado hacia abajo entre 3 y 5 unidades en y
        int j1 = ran.nextInt(1, 3);
        y = y + j1;

        // Limita el movimiento para que no pase la posición 180 en y
        if (y > 320) {
            y = 320;
        }
    }

    public void pintar(Graphics g) {
        g.drawImage(imagen.getImage(), x, y, ancho, alto, null);
       
        //g.setColor(Color.BLUE); // Color del cuadrado
        //g.fillRect(x, y, ancho, alto); // Dibuja el cuadrado en la posición (x, y)
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

            // Dibuja el texto con el valor val en la parte superior del cuadrado, desplazado en todas las direcciones
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        g.drawString(String.valueOf(pro), x + 38 + i, y + 45 + j);
                    }
                }
            }

            // Establece el color del texto en blanco y dibuja el texto
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(pro), x + 36, y + 45);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

}
