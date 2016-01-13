import java.awt.*;

public class Quadrat {
    public int amplada = 50;
    public int alcada = 50;
    public int x = 0;
    public int y = 0;
    public Color color = Color.gray;
    public boolean ences;

    public Quadrat(int x, int y, Color color, boolean ences) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.ences = ences;
    }

}
