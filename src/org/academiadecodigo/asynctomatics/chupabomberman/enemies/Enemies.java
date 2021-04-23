package org.academiadecodigo.asynctomatics.chupabomberman.enemies;

import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;
import org.academiadecodigo.asynctomatics.chupabomberman.interfaces.Destroyable;

public abstract class Enemies extends GameObject implements Destroyable {

    private boolean destroyed;

    public Enemies(FieldPosition pos) {
        super(pos);
    }

    public abstract void move(int direction) throws InterruptedException;
}
