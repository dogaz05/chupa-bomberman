package org.academiadecodigo.asynctomatics.chupabomberman.field;


import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Field {

    private int width;
    private int height;
    private int cols;
    private int rows;
    private Picture picture;
    private static final int CELL_SIZE = 40;
    private static final int PADDING = 10;

    public Field(int cols, int rows) {
        width = cols * CELL_SIZE;
        height = rows * CELL_SIZE;
        picture = new Picture(PADDING, PADDING, "resources/field.gif");
        this.cols = cols;
        this.rows = rows;
        show();
    }

    private void show() {
        picture.draw();
    }

    public int getCellSize() {
        return CELL_SIZE;
    }

    public int getPadding() { return PADDING;}

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}


}
