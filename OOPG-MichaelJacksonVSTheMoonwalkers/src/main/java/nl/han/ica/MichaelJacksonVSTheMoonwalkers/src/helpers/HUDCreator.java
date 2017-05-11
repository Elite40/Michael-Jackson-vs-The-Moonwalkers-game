package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;

import java.awt.*;

/**
 * Created by tiesbaltissen on 11-05-17.
 */
public class HUDCreator {

    public static Dashboard drawHealthBarContainer(int x, int y, int w, int h, Color c) {
        Dashboard healthBarContainer = bar(x, y, w, h);
        colorDashboard(healthBarContainer, c);
        return healthBarContainer;
    }

    public static Dashboard drawHealthBar(int x, int y, int w, int h, Color c) {
        Dashboard healthbar = bar(x, y, w, h);
        colorDashboard(healthbar, c);
        return  healthbar;
    }

    private static Dashboard bar(int x, int y, int w, int h) {
        return new Dashboard(x, y, w, h);
    }

    private static void colorDashboard(Dashboard d, Color c) {
        d.setBackground(c.getRed(), c.getGreen(), c.getBlue());
    }


    public static Dashboard drawHealthText(int x, int y) {
        return null;
    }
}
