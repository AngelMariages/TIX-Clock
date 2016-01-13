import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Finestra extends JPanel {
    private Timer timer;
    private ArrayList<Quadrat> quadrats = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Finestra();
            }
        });
    }

    public void paint(Graphics g) {
        for (Quadrat quadratTemp : quadrats) {
            g.setColor(quadratTemp.color);
            g.fillRect(quadratTemp.x, quadratTemp.y, quadratTemp.amplada, quadratTemp.alcada);
        }
    }

    public Finestra() {
        final JFrame frame = new JFrame("TIX");
        frame.setSize(560,165 + 32);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this);
        crearQuadrats();
        timer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tick();
            }
        });
        timer.start();
        frame.setVisible(true);
    }

    private void actualitzarQuadrats(int hora1, int hora2, int mins1, int mins2) {
        Random rnd = new Random();
        Quadrat quadratTemp;

        for(int i = 0; i < 27; i++) {
            quadratTemp = quadrats.get(i);
            quadratTemp.color = Color.lightGray;
            quadratTemp.ences = false;
        }

        //hora1 (0,2)
        for(int i = 0; i < hora1; i++) {
            int r = rnd.nextInt(3);
            while(quadrats.get(r).ences) {
                r = rnd.nextInt(3);
            }
            quadrats.get(r).color = Color.red;
            quadrats.get(r).ences = true;
        }

        //hora2 (3,11)
        for(int i = 0; i < hora2; i++) {
            int r = 3 + rnd.nextInt(11 - 3 + 1);
            while(quadrats.get(r).ences) {
                r = 3 + rnd.nextInt(11 - 3 + 1);
            }
            quadrats.get(r).color = Color.green;
            quadrats.get(r).ences = true;
        }

        //mins1 (12,17)
        for(int i = 0; i < mins1; i++) {
            int r = 12 + rnd.nextInt(17 - 12 + 1);
            while(quadrats.get(r).ences) {
                r = 12 + rnd.nextInt(17 - 12 + 1);
            }
            quadrats.get(r).color = Color.blue;
            quadrats.get(r).ences = true;
        }

        //mins2 (18,26)
        for(int i = 0; i < mins2; i++) {
            int r = 18 + rnd.nextInt(26 - 18 + 1);
            while(quadrats.get(r).ences)
                r = 18 + rnd.nextInt(26 - 18 + 1);
            quadrats.get(r).color = Color.red;
            quadrats.get(r).ences = true;
        }
    }

    private void crearQuadrats() {
        for(int i = 0; i < 3; i++) {
            quadrats.add(new Quadrat(10,i * (50 + 5), Color.red, false));
        }

        for(int i = 1; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                quadrats.add(new Quadrat(i * (50 + 5) + 20, j * (50 + 5), Color.green, false));
            }
        }

        for(int i = 1; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                quadrats.add(new Quadrat(i * (50 + 5) + 210, j * (50 + 5), Color.blue, false));
            }
        }

        for(int i = 1; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                quadrats.add(new Quadrat(i * (50 + 5) + 330, j * (50 + 5), Color.red, false));
            }
        }
    }

    public void tick() {
        Date d = new Date();
        actualitzarQuadrats(d.toString().split(" ")[3].charAt(0)-48,
                d.toString().split(" ")[3].charAt(1)-48,
                d.toString().split(" ")[3].charAt(3)-48,
                d.toString().split(" ")[3].charAt(4)-48);
        paint(this.getGraphics());
        timer.restart();
    }
}
