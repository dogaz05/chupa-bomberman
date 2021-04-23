package org.academiadecodigo.asynctomatics.chupabomberman;

import org.academiadecodigo.asynctomatics.chupabomberman.blocks.SolidBlocks;
import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;
import org.academiadecodigo.asynctomatics.chupabomberman.interfaces.Destroyable;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Explosion {

    private static final int CELL_SIZE = 40;
    private LinkedList<Picture> explosion = new LinkedList<>();
    private LinkedList<FieldPosition> radius = new LinkedList<>();


    public Explosion(int x, int y, Field field) {
        setRadius(new FieldPosition(x, y, field));
        checkSolidBlocks();
        drawExplosion();
        destroyObjects();
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
        deleteExplosion();

    }

    private void setRadius(FieldPosition initialPos) {
        radius.add(initialPos);
        radius.add(new FieldPosition(initialPos.getX() + CELL_SIZE, initialPos.getY(), initialPos.getField()));
        radius.add(new FieldPosition(initialPos.getX() - CELL_SIZE, initialPos.getY(), initialPos.getField()));
        radius.add(new FieldPosition(initialPos.getX(), initialPos.getY() + CELL_SIZE, initialPos.getField()));
        radius.add(new FieldPosition(initialPos.getX(), initialPos.getY() - CELL_SIZE, initialPos.getField()));
    }

    private void checkSolidBlocks() {
        Iterator<FieldPosition> iterator = radius.iterator();
        while (iterator.hasNext()) {
            FieldPosition pos = iterator.next();
            for (GameObject gameObject : Environment.getGameObjects()) {
                if(gameObject instanceof SolidBlocks) {
                    if(gameObject.getPos().equals(pos.getX(), pos.getY())) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    private void drawExplosion() {
        for (FieldPosition pos : radius) {
            explosion.add(new Picture(pos.getX(), pos.getY(), "resources/fire.gif"));
        }
        for (Picture picture : explosion) {
            picture.draw();
        }
    }

    private void destroyObjects() {
        for (FieldPosition pos : radius) {
            Iterator<GameObject> it = Environment.getGameObjects().iterator();
            while (it.hasNext()) {
                GameObject obj = it.next();
                if(obj instanceof Destroyable) {
                    if(pos.equals(obj.getPos().getX(), obj.getPos().getY())) {
                        ((Destroyable) obj).destroy();
                    }
                }
            }
        }
    }

    private void deleteExplosion() {
        for (Picture picture : explosion) {
            picture.delete();
        }
    }




}

