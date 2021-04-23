package org.academiadecodigo.asynctomatics.chupabomberman.gameobject;

import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;

public abstract class GameObject {

    private FieldPosition pos;

    public GameObject(FieldPosition pos){
        this.pos = pos;
    }

    public FieldPosition getPos(){
        return pos;
    }

}
