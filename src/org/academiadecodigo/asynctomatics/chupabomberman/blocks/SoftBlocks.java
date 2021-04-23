package org.academiadecodigo.asynctomatics.chupabomberman.blocks;

import org.academiadecodigo.asynctomatics.chupabomberman.Environment;
import org.academiadecodigo.asynctomatics.chupabomberman.blocks.Blocks;
import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.interfaces.Destroyable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SoftBlocks extends Blocks implements Destroyable {

    private Picture picture;
    private boolean destroyed;

    public SoftBlocks(int x, int y, Field field){

        super(new FieldPosition(x,y,field));
        picture = new Picture(x,y,"resources/softBlock.gif");
        show();

    }

    @Override
    public void show() {
        picture.draw();
    }

    @Override
    public void destroy() {
        if(destroyed) {
            return;
        }
        picture.delete();
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public Picture getPicture() {
        return picture;
    }

}
