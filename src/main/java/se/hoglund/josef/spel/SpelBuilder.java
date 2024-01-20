package se.hoglund.josef.spel;

import se.hoglund.josef.spelmotor.TilesetBuilder;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class SpelBuilder {
    private final TilesetBuilder tilesetBuilder = new TilesetBuilder();
    private String titel;
    private Function<Dimensioner, Stream<Visning>> visare;
    private int skalning = 1;
    private BiConsumer<Long, Tangentbord> uppdaterare;
    private int millisekunderFördröjning;

    public SpelBuilder titel(String titel) {
        this.titel = titel;
        return this;
    }

    public SpelBuilder visning(Function<Dimensioner, Stream<Visning>> visare) {
        this.visare = visare;
        return this;
    }

    public SpelBuilder uppdatera(BiConsumer<Long, Tangentbord> uppdaterare, int millisekunderFördröjning) {
        this.uppdaterare = uppdaterare;
        this.millisekunderFördröjning = millisekunderFördröjning;
        return this;
    }

    public SpelBuilder bildsamling(String filnamn) {
        tilesetBuilder.fil(filnamn);
        return this;
    }

    public SpelBuilder skalning(int skalning) {
        this.skalning = skalning;
        return this;
    }

    public SpelBuilder bild(int identitet, int xPosition, int yPosition, int bredd, int höjd) {
        tilesetBuilder.tile(identitet, xPosition, yPosition, bredd, höjd, skalning);
        return this;
    }

    public Spel build() throws IOException {
        return new Spel(titel, uppdaterare, millisekunderFördröjning, visare, tilesetBuilder.build(), skalning);
    }
}
