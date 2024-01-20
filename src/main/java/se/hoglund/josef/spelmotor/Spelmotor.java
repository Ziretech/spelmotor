package se.hoglund.josef.spelmotor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Spelmotor extends JFrame {
    private final Timer timer;

    public interface Tangenthanterare {
        void hantera();
    }

    public interface Grafikuppdaterare {
        void uppdatera(Graphics2D graphics2D);
    }

    public interface Uppdaterare {
        void uppdatera(long tid);
    }

    private long tid = 0L;

    public Spelmotor(String titel, Grafikuppdaterare grafikuppdaterare, KeyListener tangentbord, Uppdaterare uppdaterare, int millisekunderFördröjning) {
        this(titel, grafikuppdaterare, tangentbord, uppdaterare, millisekunderFördröjning, true);
    }

    public Spelmotor(String titel, Grafikuppdaterare grafikuppdaterare, KeyListener tangentbord, Uppdaterare uppdaterare, int millisekunderFördröjning, boolean fullscreen) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(titel);
        setUndecorated(true);
        if (fullscreen) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setSize(192, 192);
        }
        addKeyListener(tangentbord);
        add(new JComponent() {
            @Override
            public void paintComponent(Graphics graphics) {
                grafikuppdaterare.uppdatera((Graphics2D) graphics);
            }
        });
        timer = new Timer(millisekunderFördröjning, event -> {
            uppdaterare.uppdatera(tid++);
            repaint();
        });
    }

    public void starta() {
        timer.start();
        setVisible(true);
    }
}
