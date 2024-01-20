package se.hoglund.josef.spel;

import se.hoglund.josef.spelmotor.Spelmotor;
import se.hoglund.josef.spelmotor.Tileset;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class Spel {

    private final Spelmotor motor;
    private final Tangentbord tangentbord;
    private final BiConsumer<Long, Tangentbord> uppdaterare;
    private final Function<Dimensioner, Stream<Visning>> visare;
    private final Tileset bildsamling;
    private final int skalning;

    public Spel(String titel, BiConsumer<Long, Tangentbord> uppdaterare, int millisekunderFördröjning, Function<Dimensioner, Stream<Visning>> visare, Tileset bildsamling, int skalning) {
        this.tangentbord = new Tangentbord();
        this.uppdaterare = uppdaterare;
        this.visare = visare;
        this.bildsamling = bildsamling;
        this.skalning = skalning;
        motor = new Spelmotor(titel, this::visa, tangentbord, this::uppdatera, millisekunderFördröjning);
    }

    private void visa(Graphics2D graphics) {
        var bounds = graphics.getClipBounds();
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        visare.apply(new Dimensioner(bounds.width / skalning, bounds.height / skalning)).forEach(visning -> visa(graphics, visning));
    }

    private void visa(Graphics2D skärm, Visning visning) {
        skärm.drawImage(bildsamling.bild(visning.identitet()), visning.x() * skalning, visning.y() * skalning, null);
    }

    private void uppdatera(long tid) {
        uppdaterare.accept(tid, tangentbord);
    }

    public void starta() {
        motor.starta();
    }
}
