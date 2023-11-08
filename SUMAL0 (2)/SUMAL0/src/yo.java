
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
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class yo {

    private int x = 90, y = 80, ancho = 210, alto = 400, val;
    private ImageIcon imagen = new ImageIcon("C:\\Primer semestre\\POO\\SUMAL0\\src\\newpackage\\si.png"); // Reemplaza con la ruta de tu imagen

    public yo() {
        ancho = 90;
        alto = 80;
        x = 210;
        y = 400;
    }

    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                x = x - 100;
                break;
            case KeyEvent.VK_RIGHT:
                x = x + 100;
                break;
            case KeyEvent.VK_SPACE:

        }

    }

    public void pintar(Graphics g) {
        //g.setColor(Color.RED);
        //g.fillRect(x, y, ancho, alto);
        g.drawImage(imagen.getImage(), x, y, ancho, alto, null);
        // Establece el color del texto en blanco
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

            // Dibuja el texto con el valor val en la parte superior del cuadrado, desplazado en todas las direcciones
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        g.drawString(String.valueOf(val), x + 38 + i, y + 45 + j);
                    }
                }
            }

            // Establece el color del texto en blanco y dibuja el texto
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(val), x + 36, y + 45);
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

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

}
