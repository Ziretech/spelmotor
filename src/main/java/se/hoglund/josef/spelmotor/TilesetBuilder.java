package se.hoglund.josef.spelmotor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class TilesetBuilder {
    private File bildfil;
    private final Collection<TileDefinition> tileDefinitioner = new ArrayList<>();

    public TilesetBuilder fil(File bildfil) {
        this.bildfil = bildfil;
        return this;
    }

    public TilesetBuilder fil(String filnamn) {
        return fil(new File(filnamn));
    }

    public TilesetBuilder tile(int identitet, int x, int y, int bredd, int höjd, int skalning) {
        tileDefinitioner.add(new TileDefinition(identitet, x, y, bredd, höjd, skalning));
        return this;
    }

    public File bildfil() {
        return bildfil;
    }

    public Collection<TileDefinition> tileDefinitioner() {
        return tileDefinitioner;
    }

    public Tileset build() throws IOException {
        return new Tileset(this);
    }

    public record TileDefinition(
            int identitet,
            int x,
            int y,
            int bredd,
            int höjd,
            int skalning
    ) {
    }
}
