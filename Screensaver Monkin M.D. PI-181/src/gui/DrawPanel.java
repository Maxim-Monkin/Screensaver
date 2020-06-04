package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;
import javax.swing.JPanel;

class DrawPanel extends JPanel implements Runnable {

    private int x, y;
    private int dx, dy;
    private int time;
    private Color actualColor;
    private int color;
    private boolean isViolett;
    private Random random;
    private int option;
   
    

    public DrawPanel(Dimension size, int dx, int dy) {
        this.setSize(size);
        random = new Random();
        this.dx = dx;
        this.dy = dy;


        actualColor = new Color(147, 112, 219);
        color = 20;
        isViolett = true;

        setTimeValue();


        x = random.nextInt(this.getSize().width - 10);
        y = random.nextInt(this.getSize().height - 10);

        option = random.nextInt(4) + 1;
    
        changeD();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(actualColor);
        g2d.fillOval(x, y, 10, 10);
    }


    @Override
    public void run() {

        while (true)
        {
            boolean collision = checkCollision();
            if (collision) {
                changeD();
            }


            x += dx;
            y += dy;

            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }


            time--;
            color--;


            if (time == 0) {
                changeD();
                setTimeValue();
            }


            if (color == 0) {
                isViolett = changeColor();
            }
            repaint();
        }
    }


    private boolean checkCollision() {
        if ((x < 10 || x > this.getWidth()) || (y < 10 || y > this.getHeight())) {
            return true;
        }
        return false;

    }


    private void setTimeValue() {
        time = random.nextInt(100) + 50;
    }


    private void changeD() {

        int newOption = 0;
        if ((option % 2) == 0) {
            newOption = random.nextInt(4) + 1;
            while ((newOption % 2) != 1) {
                newOption = random.nextInt(4) + 1;
            }
        }

        if ((option % 2) != 0) {
            newOption = random.nextInt(4) + 1;
            while ((newOption % 2) != 0) {
                newOption = random.nextInt(4) + 1;
            }
        }

        switch (newOption) {
            case 1: {
                dx = 3;
                dy = 0;
            }
            break;

            case 2: {
                dx = 0;
                dy = 3;
            }
            break;

            case 3: {
                dx = -3;
                dy = 0;
            }
            break;

            case 4: {
                dx = 0;
                dy = -3;
            }
            break;
        }

        option = newOption;
    }


    private boolean changeColor() {
        color = 20;
        if (isViolett) {
            actualColor = new Color(46, 189, 87);
            return false;
        }

        if (!(isViolett)) {
            actualColor = new Color(147, 112, 219);
            return true;
        }

        return false;
    }
}

