package se.hoglund.josef.spel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.stream.Stream;

public class Tangentbord implements KeyListener {
    private final HashSet<Integer> nedtrycktaTangenter = new HashSet<>();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        nedtrycktaTangenter.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        nedtrycktaTangenter.remove(e.getKeyCode());
    }

    public Stream<Integer> nedtrycktaTangenter() {
        return nedtrycktaTangenter.stream();
    }
}
