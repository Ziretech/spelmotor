package se.hoglund.josef.spelmotor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tileset {

    private final Map<Integer, Image> tiles = new HashMap<>();
    public Tileset(TilesetBuilder builder) throws IOException {
        var bufferedImage = ImageIO.read(builder.bildfil());
        builder.tileDefinitioner()
                .forEach(definition -> tiles.put(
                        definition.identitet(),
                        bufferedImage
                                .getSubimage(definition.x(), definition.y(), definition.bredd(), definition.höjd())
                                .getScaledInstance(definition.bredd() * definition.skalning(), definition.höjd() * definition.skalning(), Image.SCALE_FAST)));
    }

    public Image bild(int identitet) {
        return tiles.get(identitet);
    }
}
