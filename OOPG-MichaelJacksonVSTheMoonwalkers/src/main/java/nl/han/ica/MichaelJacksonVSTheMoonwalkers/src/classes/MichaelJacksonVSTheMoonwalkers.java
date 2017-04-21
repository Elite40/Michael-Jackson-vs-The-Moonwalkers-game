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

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers"});}
    @Override
    public void setupGame() {
        try {
            int worldWidth=1204;
            int worldHeight=903;

            createDashboard(worldWidth, 100);
            initializeTileMap();


            createViewWithoutViewport(worldWidth, worldHeight);
            GameSession.sharedInstance().setupGameSession();

        } catch (Exception e) {
            println(e);
        }
    }

    @Override
    public void update() {

    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/waterworld/media/background.jpg"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        TextObject dashboardText=new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    /**
     * Initialiseert de tilemap
     */
    private void initializeTileMap() {
        /* TILES */
        Sprite boardsSprite = new Sprite("src/main/java/nl/han/ica/waterworld/media/boards.jpg");
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardsSprite);

        TileType[] tileTypes = { boardTileType };
        int tileSize=50;
        int tilesMap[][]={
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1, 0, 0,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1, 0,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1, 0,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1, 0, 0, 0, 0,-1,0 , 0, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }
}
