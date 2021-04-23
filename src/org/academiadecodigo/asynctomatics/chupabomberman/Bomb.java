package org.academiadecodigo.asynctomatics.chupabomberman;
import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Bomb extends GameObject {
    
    private Picture bomb;
    private Explosion explosion;


    public Bomb(int x, int y, Field field) {
        super(new FieldPosition(x, y, field));
        this.bomb = new Picture(x, y, "resources/bomb.gif");
    }

    public void setExplosion() {
        try{
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
        bomb.delete();
        Music.getSound(Music.Sounds.BOMBEXPLOSION);
        this.explosion = new Explosion(getPos().getX(), getPos().getY(), getPos().getField());
    }

    public Picture getBombPic() {
        return bomb;
    }

}
