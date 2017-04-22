package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.TextObject;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PApplet;

/**
 * Created by tiesbaltissen on 21-04-17.
 */
public class MichaelJacksonVSTheMoonwalkers extends GameEngine {

    private int worldWidth;
    private int worldHeight;

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers"});}
    @Override
    public void setupGame() {
        try {
            worldWidth=1000;
            worldHeight=518;

            createDashboard(worldWidth, 100);


            createViewWithoutViewport(worldWidth, worldHeight);
            GameSession.sharedInstance().setupGameSession(this);

        } catch (Exception e) {
            println(e);
        }
    }

    @Override
    public void update() {

    }

    public int[] getScreenSize() {
        return new int[]{ worldWidth, worldHeight };
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/Backgrounds/sprite-bg.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        TextObject dashboardText=new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }
}
