package org.academiadecodigo.asynctomatics.chupabomberman;

import org.academiadecodigo.asynctomatics.chupabomberman.blocks.SoftBlocks;
import org.academiadecodigo.asynctomatics.chupabomberman.blocks.SolidBlocks;
import org.academiadecodigo.asynctomatics.chupabomberman.enemies.Enemies;
import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObjectFactory;
import org.academiadecodigo.asynctomatics.chupabomberman.interfaces.Destroyable;
import org.academiadecodigo.asynctomatics.chupabomberman.players.Player;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.concurrent.TimeUnit;

public class Game {

    public Field field;
    private Player[] players;
    private Enemies[] enemies;
    private boolean initialized = false;
    private boolean gameOver = false;

    public Game(int cols, int rows) {
        field = new Field(cols, rows);
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void startScreen(){
        new StartScreen(field, this);
        Music.getSound(Music.Sounds.STARTSCREEN);
    }

    public void init(int numbPlayers){

        if(initialized) {
            return;
        }

        Music.getSound(Music.Sounds.START);
        players = GameObjectFactory.createPlayer(numbPlayers, field);
        enemies = GameObjectFactory.createEnemies(numbPlayers, field);
        SoftBlocks[] softBlocks = GameObjectFactory.createSoftBlocks(field);
        SolidBlocks[] solidBlocks = GameObjectFactory.createSolidBlocks(field);
        GameObject[] gameObjects = concatAllObjects(softBlocks, solidBlocks);
        Environment.setGameObjects(gameObjects, this);
        initialized = true;

    }

    public void start(){

        if(players.length == 1) {
            start1P();
        } else {
            start2P();
        }
    }

    private void start1P() {

        while(!gameOver) {

            try {
                moveEnemies();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Environment.checkBombP1();

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            checkEndGame1P();
        }

    }

    private void checkEndGame1P() {

        if(players[0].isDestroyed()) {
            setGameOver("resources/gameover.gif");
            Music.getSound(Music.Sounds.GAMEOVER);
            return;
        }

        for (GameObject object : Environment.getGameObjects()) {
            if(object instanceof SoftBlocks || object instanceof Enemies) {
                if(!((Destroyable) object).isDestroyed()) {
                    return;
                }
            }
        }
        setGameOver("resources/player1wins.gif");
        Music.getSound(Music.Sounds.WIN);
    }

    private void start2P() {
        while(!gameOver) {

            try {
                moveEnemies();
            }catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Environment.checkBombP1();
            Environment.checkBombP2();

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            checkEndGame2P();

        }

    }

    private void checkEndGame2P() {
        if(players[0].isDestroyed() && players[1].isDestroyed()){
            Music.getSound(Music.Sounds.GAMEOVER);
            setGameOver("resources/gameover.gif");
            return;
        }
        if(players[0].isDestroyed()) {
            Music.getSound(Music.Sounds.WIN);
            setGameOver("resources/player2wins.gif");
        }
        if(players[1].isDestroyed()) {
            Music.getSound(Music.Sounds.WIN);
            setGameOver("resources/player1wins.gif");
        }
    }

    private void setGameOver(String imagePath) {
        gameOver = true;
        new Picture(field.getPadding(), field.getPadding(), imagePath).draw();
    }

    private void moveEnemies() throws InterruptedException{

        for (int j = 0; j < enemies.length; j++) {
            if (enemies[j].isDestroyed()) {
                if(j == (enemies.length-1)) {
                    return;
                }
                continue;
                }
            int steps = (int) ((Math.random()*3));
            int direction = (int) ((Math.random()*5));
            for(int i = 0; i < steps; i++) {
                enemies[j].move(direction);
                for (Player player : players) {
                    if (Environment.checkPvsECollision(player.getPos())) {
                        player.destroy();
                    }
                }
            }
            }
    }

    private GameObject[] concatAllObjects (SoftBlocks[] softBlocks, SolidBlocks[] solidBlocks) {

        GameObject[] gameObjects = new GameObject[(players.length+enemies.length+softBlocks.length+solidBlocks.length)];
        int length2 = players.length + enemies.length;
        int length3 = players.length + enemies.length + softBlocks.length;


        System.arraycopy(players, 0, gameObjects, 0, players.length);
        System.arraycopy(enemies, 0, gameObjects, players.length, enemies.length);
        System.arraycopy(softBlocks, 0, gameObjects, length2, softBlocks.length);
        System.arraycopy(solidBlocks, 0, gameObjects, length3, solidBlocks.length);


        return gameObjects;
    }
}
