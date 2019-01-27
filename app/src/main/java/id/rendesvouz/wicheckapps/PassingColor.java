package id.rendesvouz.wicheckapps;


public class PassingColor {
    private static PassingColor instance;
    private int red;
    private int green;
    private int blue;
    private int step;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public static PassingColor getInstance(){
        if (instance == null) instance = new PassingColor();

        return instance;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
