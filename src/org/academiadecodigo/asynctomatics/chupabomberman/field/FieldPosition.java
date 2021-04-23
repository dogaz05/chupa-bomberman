package org.academiadecodigo.asynctomatics.chupabomberman.field;

import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;

public class FieldPosition {

    private int x;
    private int y;
    private Field field;

    public FieldPosition(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Field getField() {
        return field;
    }

    public void translate(int diffX, int diffY) {
        x += diffX;
        y += diffY;
    }

    public boolean equals(int x, int y) {

        return (this.x == x && this.y == y);
    }
}
