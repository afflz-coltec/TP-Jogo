package javaball;

import javaball.StdDraw;



public class Desenho {

    private double x;
    private double y;
    private int cor;

    public Desenho() {
        setDesenho(0.0, 0.0, 0);
    }

    public Desenho(double a, double b, int i) {
        setDesenho(a, b, i);
    }

    public Desenho(Desenho p) {
        setDesenho(p.getX(), p.getY(), p.getCor());
    }

    public void setDesenho(double a, double b, int i) {
        setX(a);
        setY(b);
        setCor(i);
    }

    public void setX(double a) {
        x = a;
    }

    public void setY(double b) {
        y = b;
    }

    public void setCor(int i) {
        cor = (i >= 0) ? i : 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getCor() {
        return cor;
    }

    public void desenhar() {
        switch (cor) {
            case 0:
                StdDraw.setPenColor(StdDraw.RED);
                break;
            case 1:
                StdDraw.setPenColor(StdDraw.GREEN);
                break;
            case 2:
                StdDraw.setPenColor(StdDraw.YELLOW);
                break;
            case 3:
                StdDraw.setPenColor(StdDraw.CYAN);
                break;
            case 4:
                StdDraw.setPenColor(StdDraw.ORANGE);
                break;
        }
        StdDraw.circle(x, y, 0.01);
    }
}
