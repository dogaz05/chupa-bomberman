package org.academiadecodigo.asynctomatics.chupabomberman.players;

import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;
import org.academiadecodigo.asynctomatics.chupabomberman.interfaces.Destroyable;

public abstract class Player extends GameObject implements Destroyable {

    public Player(FieldPosition pos) {
        super(pos);
    }


    @Override
    public abstract void destroy();

    @Override
    public abstract boolean isDestroyed();
}
