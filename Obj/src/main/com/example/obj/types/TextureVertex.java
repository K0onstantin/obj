package main.com.example.obj.types;
import java.util.Locale;

public class TextureVertex implements IType{
    public final int MinimumDataLength = 3;
    public final String Prefix = "vt";

    private double x;

    private double y;

    private int index;

    @Override
    public void ReadFromStringArray(String[] data) {
        if (data.length < MinimumDataLength)
            throw new IllegalArgumentException("Длина входных данных должна быть не меньше " + MinimumDataLength);

        if (!data[0].equalsIgnoreCase(Prefix))
            throw new IllegalArgumentException("Префикс должен быть '" + Prefix + "'");

        double x, y;

        try {
            x = Double.parseDouble(data[1]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Не удается прочитать параметр X как double");
        }

        try {
            y = Double.parseDouble(data[2]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Не удается прочитать параметр Y как double");
        }

        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "vt %f %f", x, y);
    }
}
