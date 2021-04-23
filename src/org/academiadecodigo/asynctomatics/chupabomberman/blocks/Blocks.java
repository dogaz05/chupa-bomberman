package org.academiadecodigo.asynctomatics.chupabomberman.blocks;

import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;

public abstract class Blocks extends GameObject {

    public Blocks(FieldPosition pos){
        super(pos);
    }

    public abstract void show();

}
