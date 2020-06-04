package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Window extends JFrame {

    private DrawPanel drawPanel;

    public Window() {
        this.setBackground(Color.black);
        this.setSize(getToolkit().getScreenSize());
        this.drawPanel = new DrawPanel(getToolkit().getScreenSize(), 10, 10);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(this.drawPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread thread = new Thread(drawPanel);
        thread.start();
    }

    public static void main(String[] args) {
        Window window = new Window();

        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.exit(1);
            }
        });
        window.setVisible(true);
    }
}
