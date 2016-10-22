package javaball;


public class Ponto extends Desenho {

    private double raio;

    public Ponto() {
        this(0.0, 0.0, 0, 0.01);
    }

    public Ponto(Ponto p) {
        this(p.getX(), p.getY(), p.getCor(), p.getRaio());
    }

    public Ponto(double a, double b, int i, double c) {
        setPonto(a, b, i, c);
    }

    public void setPonto(double a, double b, int i, double c) {
        setDesenho(a, b, i);
        setRaio(c);
    }

    public void setRaio(double c) {
        raio = (c > 0) ? c : 0.01;
    }

    public double getRaio() {
        return raio;
    }

    public void desenhar() {
        switch (getCor()) {
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
        StdDraw.filledCircle(getX(), getY(), raio);
    }
}
