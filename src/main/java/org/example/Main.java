package org.example;

import se.hoglund.josef.spel.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {

    public static final int FPS_30 = 1000 / 30;
    public static final int HUVUDPERSON_BILD = 0;
    public static final int GRÄS_BILD = 1;
    private final Spel spelet;
    private int xPosition = 0;
    private int yPosition = 0;

    public Main() throws IOException {
        spelet = new SpelBuilder()
                .titel("Spelet")
                .visning(this::visa)
                .uppdatera(this::uppdatera, FPS_30)
                .bildsamling("grafik.png")
                .skalning(4)
                .bild(HUVUDPERSON_BILD, 0, 0, 16, 16)
                .bild(GRÄS_BILD, 16, 0, 16, 16)
                .build();
    }

    public static void main(String[] args) throws IOException {
        new Main().kör();
    }

    private void kör() {
        spelet.starta();
    }

    private void uppdatera(long tid, Tangentbord tangentbord) {
        tangentbord.nedtrycktaTangenter().forEach(tangent ->
        {
            if (tangent == KeyEvent.VK_RIGHT) {
                xPosition += 1;
            }
            if (tangent == KeyEvent.VK_LEFT) {
                xPosition -= 1;
            }
            if (tangent == KeyEvent.VK_DOWN) {
                yPosition += 1;
            }
            if (tangent == KeyEvent.VK_UP) {
                yPosition -= 1;
            }
        });
    }

    private Stream<Visning> visa(Dimensioner dimensioner) {
        return Stream.of(new Visning(HUVUDPERSON_BILD, xPosition, yPosition));
    }
}