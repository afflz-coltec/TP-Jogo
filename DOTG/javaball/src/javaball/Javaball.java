package javaball;

import java.io.*;
import audio.play;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Javaball extends Ponto {

    private static double energy_trade = 0.6;
    private static double limite_x = 10;
    private static double limite_y = 10;

    boolean aftershook;
    private double massa;
    String name;
    private double Vx;
    private double Vy;
    float rate = (float) 0;
    private Color color;
    play p;
    private double nextVx;
    private double nextVy;
    private boolean colision;

    public Javaball(double x, double y, int cor, double massa, double vx, double vy, String nome) throws Exception {
        super(x, y, cor, massa / 1000);
        setMassa(massa);
        setVx(vx);
        setVy(vy);
        nextVx = vx;
        nextVy = vy;
        this.aftershook = false;
        this.colision = false;
        this.name = nome;
        p = new play();
        this.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
    }

    public void setMassa(double d) {
        massa = (d > 0) ? d : 0;
        setRaio(Math.sqrt(massa / Math.PI));
    }

    public void setVx(double e) {
        this.Vx = e;
    }

    public void setVy(double e) {
        this.Vy = e;
    }

    public double getMassa() {
        return massa;
    }

    public double getVx() {
        return Vx;
    }

    public double getVy() {
        return Vy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void desenhar() {
        StdDraw.filledCircle(getX(), getY(), getRaio());
    }

    public void mover(boolean c, ArrayList<Javaball> balls) {
        StdDraw.setPenColor(this.getColor());
        verificarLimite(c);

        for (Javaball ball : balls) {
            try {
                if (this != ball) {

                    this.verificarColisao_norm(this, ball, c);
                }
            } catch (Exception ex) {
            }
        }
        if (!this.colision && this.aftershook) {
            this.aftershook = false;
        }
        colision = false;

        setX(this.getX() + this.getVx());
        setY(this.getY() + this.getVy());
        desenhar();
    }

    public void mover() {
        StdDraw.setPenColor(this.getColor());
        setX(StdDraw.mouseX());
        setY(StdDraw.mouseY());
        if (StdDraw.mousePressed()) {
            setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        }
        desenhar();
    }

    public void verificarLimite(boolean c) {
        double aux1 = this.getX() + this.getRaio();
        double aux2 = this.getY() + this.getRaio();

        if (aux1 < -Javaball.limite_x + this.getRaio() * 2 && this.nextVx < 0) {
            set(true, false, c, 0);
        } else if (aux1 > Javaball.limite_x && this.nextVx > 0) {
            set(true, false, c, 0);
        }
        if (aux2 > Javaball.limite_y && this.nextVy > 0) {
            set(false, true, c, 0);
        } else if (aux2 < -Javaball.limite_y + this.getRaio() * 2 && this.nextVy < 0) {
            set(false, true, c, 0);
        }

    }

    public double raio() {
        return getRaio();
    }

    /**
     *
     * @param x verificação de alteração positiva ou negativa
     * @param y verificação de alteração positiva ou negativa
     * @param c trocar/não trocar de cor
     * @param rate taxa de incremento
     */
    public void set(boolean x, boolean y, boolean c, float rate) {
        if (x) {
            this.nextVx = (-(this.getVx() + (this.getVx() * rate)));
        } else {
            this.nextVx = (this.getVx() + (this.getVx() * rate));
        }
        if (y) {
            this.nextVy = (-(this.getVy() + (this.getVy() * rate)));
        } else {
            this.nextVy = (this.getVy() + (this.getVy() * rate));
        }
        if (c) {
            this.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        }
        System.out.println("Setou : " + this.name);
    }

    static public void m_change(Javaball p1, Javaball p2) {
        double avr_x = (p1.getVx() + p2.getVx()) * Javaball.energy_trade;
        double avr_y = (p1.getVy() + p2.getVy()) * Javaball.energy_trade;

        if ((p1.getVx() > 0) == (p2.getVx() > 0)) {
            p1.nextVx = (p1.nextVx + avr_x) / 2;
            p2.nextVx = (p1.nextVx + avr_x) / 2;
        } else if ((p1.getVx() > 0) != (p2.getVx() > 0)) {
            p1.nextVx = (p1.nextVx - avr_x) / 2;
            p2.nextVx = (p1.nextVx - avr_x) / 2;
        }
        if ((p1.getVy() > 0) == (p2.getVy() > 0)) {
            p1.nextVy = (p1.nextVy + avr_y) / 2;
            p2.nextVy = (p1.nextVy + avr_y) / 2;
        } else if ((p1.getVy() > 0) != (p2.getVy() > 0)) {
            p1.nextVy = (p1.nextVy - avr_y) / 2;
            p2.nextVy = (p1.nextVy - avr_y) / 2;
        }

        p1.colision = true;
        p2.colision = true;
        p1.aftershook = true;
        p2.aftershook = true;

    }

    private void reset() {
        this.setVx(nextVx);
        this.setVy(nextVy);
    }

    public void verificarColisao_norm(Javaball p1, Javaball p2, boolean c) throws IOException, Exception {
        double dx = Math.pow(((p1.getX()) - (p2.getX())), 2);
        double dy = Math.pow(((p1.getY()) - (p2.getY())), 2);
        double rx = Math.pow(((p1.getX() + p1.raio()) - (p2.getX() + p2.raio())), 2);
        double ry = Math.pow(((p1.getY() + p1.raio()) - (p2.getY() + p2.raio())), 2);
        double d = Math.sqrt(dx + dy);
        double r = Math.sqrt(rx + ry);
        // Ocorreu uma colisao !!
        if (d < r && !(p1.aftershook || p1.colision)) {
            System.out.println(p1.name);
            System.out.println(p2.name);
            System.out.println("colidiu");
            Javaball.m_change(p1, p2);
        } 
        //p1.p.soltaOSom();
    }

    public static void main(String args[]) throws Exception {

        StdDraw.setXscale(-Javaball.limite_x, Javaball.limite_x);
        StdDraw.setYscale(-Javaball.limite_y, Javaball.limite_y);

        boolean c = false;

        ArrayList<Javaball> balls = new ArrayList<Javaball>();
        Javaball p1;
        p1 = new Javaball(2.0, 0, 0, 1.0, 0.6, 0.2, "bolinha1");
        balls.add(p1);
        p1 = new Javaball(1.0, 0, 0, 1.0, -0.4, -0.4, "bolinha2");
        balls.add(p1);
        p1 = new Javaball(0.0, 0, 0, 1.0, -0.2, 0.6, "bolinha3");
        balls.add(p1);
        p1 = new Javaball(-2.0, 0, 0, 1.0, 0.4, -0.3, "bolinha4");
        balls.add(p1);
        p1 = new Javaball(-2.0, 0, 0, 1.0, 0.8, -0.2, "bolinha5");
        balls.add(p1);
         p1 = new Javaball(2.0, 0, 0, 1.0, 0.4, -0.3, "bolinha5");
        balls.add(p1);

        Javaball mouse = new Javaball(4.0, -9.0, 0, 10.0, 0.7, 0.4, "bololo haha");

        while (true) {
            StdDraw.clear();

            for (Javaball ball : balls) {
                ball.mover(c, balls);
            }
            for (Javaball ball : balls) {
                ball.reset();
            }

            mouse.mover();
            StdDraw.show(15);
        }
    }

}
