/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MIGUEL  MARQUEZ
 */
import java.awt.Color;
import java.awt.Graphics;

public class Peñon {
    private int x, y=400, ancho=20, alto=20;
    private boolean visible;

    public Peñon(int x, int y) {
        this.x = x + 50;
        this.visible = false;
    }

    public void moverArriba() {
        if (visible) {
            y=y-70;
        }
    }

    public void pintar(Graphics g) {
        if (visible) {
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, ancho, alto);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
    
}