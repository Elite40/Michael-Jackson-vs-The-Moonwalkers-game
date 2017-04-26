package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by OsmanKaya on 25-4-2017.
 */
public class ButtonCreator extends GameObject {

    /**
     * The width of the button
     */
    private int buttonWidth;

    /**
     * The height of the button
     */
    private int buttonHeight;

    /**
     * The text of the button
     */
    private String buttonText;

    private Color buttonTextColor = Color.white;

    /**
     * X position of the button
     */
    private int xPosition;

    /**
     * Y Position of the button
     */
    private int yPosition;

    /**
     * Background color of the button
     */
    private Color backgroundColor = Color.white;

    /**
     * Border color of the button
     */
    private Color borderColor = Color.white;

    /**
     * Font size of the text inside the button
     */
    private int buttonFontSize;

    public ButtonCreator(String buttonText, int xPosition, int yPosition) {
        this.buttonText = buttonText;
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        this.buttonWidth = 150;
        this.buttonHeight = 50;
        this.buttonFontSize = 40;
    }

    public ButtonCreator(String buttonText, int xPosition, int yPosition, int buttonWidth, int buttonHeight) {
        this.buttonText = buttonText;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.fill(this.backgroundColor.getRGB());
        g.stroke(this.borderColor.getRGB());
        g.rect(this.xPosition, this.yPosition, this.buttonWidth, this.buttonHeight);

        g.fill(this.buttonTextColor.getRGB());
        g.textSize(this.buttonFontSize);

        centerTextInsideButton(g);
    }

    private void centerTextInsideButton(PGraphics g){

        float textXPosition = (this.xPosition + (this.buttonWidth/2)) - g.textWidth(this.buttonText) / 2;
        float textYPosition = (this.yPosition + (this.buttonHeight/2)) - g.textAscent()/2;

        g.text(this.buttonText, textXPosition, textYPosition);
    }

    public int getButtonWidth() {
        return buttonWidth;
    }

    public void setButtonWidth(int buttonWidth) {
        this.buttonWidth = buttonWidth;
    }

    public void setButtonHeight(int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setButtonTextColor(Color buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
    }

    public void setButtonFontSize(int buttonFontSize) {
        this.buttonFontSize = buttonFontSize;
    }
}
