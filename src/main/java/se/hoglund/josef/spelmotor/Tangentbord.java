package se.hoglund.josef.spelmotor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Tangentbord implements KeyListener {
    private final HashMap<Integer, Spelmotor.Tangenthanterare> nedtryck;
    private final HashMap<Integer, Spelmotor.Tangenthanterare> släpp;
    private final HashMap<Integer, Boolean> tangentstatus;

    public Tangentbord(
            HashMap<Integer, Spelmotor.Tangenthanterare> nedtryck,
            HashMap<Integer, Spelmotor.Tangenthanterare> släpp,
            HashMap<Integer, Boolean> tangentstatus) {

        this.nedtryck = nedtryck;
        this.släpp = släpp;
        this.tangentstatus = tangentstatus;
    }

    public boolean nedtryckt(int tangent) {
        return tangentstatus.getOrDefault(tangent, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        nedtryck.getOrDefault(e.getKeyCode(), () -> {}).hantera();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        släpp.getOrDefault(e.getKeyCode(), () -> {}).hantera();
    }
}
