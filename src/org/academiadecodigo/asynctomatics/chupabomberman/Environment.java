package org.academiadecodigo.asynctomatics.chupabomberman;

import org.academiadecodigo.asynctomatics.chupabomberman.blocks.Blocks;
import org.academiadecodigo.asynctomatics.chupabomberman.blocks.SoftBlocks;
import org.academiadecodigo.asynctomatics.chupabomberman.enemies.Enemies;
import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Environment {

    private static LinkedList<GameObject> gameObjects = new LinkedList<>();

    private static Bomb savedBomb;

    private static Bomb savedBombP2;

    private static Game game;


    public static void setGameObjects(GameObject[] gameObjs, Game gm) {
        gameObjects.addAll(Arrays.asList(gameObjs));
        game = gm;
    }

    public static boolean saveBomb(Bomb bomb) {
        if(game.isGameOver()) {
            return false;
        }
        if(!(savedBomb == null)) {
            return false;
        }
        savedBomb = bomb;
        return true;
    }

    public static boolean saveBombP2(Bomb bomb) {
        if(game.isGameOver()) {
            return false;
        }

        if(!(savedBombP2 == null)) {
            return false;
        }
        savedBombP2 = bomb;
        return true;
    }

    public static LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public static boolean isBlockedPos(int x, int y) {

        for (GameObject obj:gameObjects) {
            if(obj instanceof Blocks) {
                if(obj.getPos().getX() == x && obj.getPos().getY() == y) {
                    if(obj instanceof SoftBlocks && ((SoftBlocks) obj).isDestroyed()) {
                        return false;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checkPvsECollision(FieldPosition playerPos) {

        for (GameObject obj:gameObjects) {
            if(obj instanceof Enemies) {
                if(obj.getPos().getX() == playerPos.getX() && obj.getPos().getY() == playerPos.getY() && !((Enemies) obj).isDestroyed()) {
                    return true;
                }
            }
        }

        return false;

    }

    public static void checkBombP1() {

        if(savedBomb == null) {
            return;
        }

        savedBomb.setExplosion();
        savedBomb = null;

    }

    public static void checkBombP2() {

        if(savedBombP2 == null) {
            return;
        }

        savedBombP2.setExplosion();
        savedBombP2 = null;

    }

}
