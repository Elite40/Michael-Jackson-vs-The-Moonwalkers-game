package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PFont;
import processing.core.PGraphics;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static java.awt.Font.createFont;

/**
 * @author Ralph Niels
 * Wordt gebruikt om een tekst te kunnen afbeelden
 */
public class TextObject extends GameObject {

    private String text;
    private int fontSize = 50;
    private Font customFont;

    private PFont tmpFont;

    /**
     * By default the text color is white.
     */
    private Color textColor;

    public TextObject(String text) {
        this.text=text;
    }

    public void setText(String text) {
        this.text=text;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(this.fontSize);
        if (this.tmpFont != null) {
            g.textFont(this.tmpFont);
        }
        g.text(text,getX(),getY());

        if (this.textColor != null) {
            g.fill(this.textColor.getRGB());
        }
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setCustomFont(String font) {
        this.customFont = new Font(font, Font.PLAIN, this.fontSize);

        this.tmpFont = new PFont(this.customFont, true);
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
