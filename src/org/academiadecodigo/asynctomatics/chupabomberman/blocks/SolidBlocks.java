package org.academiadecodigo.asynctomatics.chupabomberman.blocks;
import org.academiadecodigo.asynctomatics.chupabomberman.blocks.Blocks;
import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SolidBlocks extends Blocks {

    private Picture picture;


    public SolidBlocks(int x, int y, Field field, String picPath){

        super(new FieldPosition(x,y,field));
        picture = new Picture(x,y,picPath);
        show();

    }

    @Override
    public void show() {
        picture.draw();
    }

    public Picture getPicture() {
        return picture;
    }

}
