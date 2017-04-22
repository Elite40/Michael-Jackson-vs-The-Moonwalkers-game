package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

public class SpriteGroup {
    public String name;
    public List<Sprite> sprites;

    public SpriteGroup(String name, List<Sprite> sprites) {
        this.name = name;
        this.sprites = sprites;
    }
}
