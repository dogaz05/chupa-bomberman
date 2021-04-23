package org.academiadecodigo.asynctomatics.chupabomberman.enemies;

import org.academiadecodigo.asynctomatics.chupabomberman.Environment;
import org.academiadecodigo.asynctomatics.chupabomberman.Music;
import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.players.Player1;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.concurrent.TimeUnit;

public class Enemy extends Enemies {

    private boolean destroyed;
    private final Picture picture;
    private final int pace = 1;
    private final int CELL_SIZE = getPos().getField().getCellSize();

    public Enemy(int x, int y, Field field) {

        super(new FieldPosition(x, y, field));
        String picSource = "resources/enemy.gif";
        picture = new Picture(x,y, picSource);
        picture.draw();
        destroyed = false;
    }

    public void moveUp() throws InterruptedException {


            if (!(Environment.isBlockedPos(getPos().getX(), (getPos().getY() - CELL_SIZE)))) {

                getPos().translate(0, -CELL_SIZE);

                for (int i = 0; i < (CELL_SIZE / pace); i++) {

                    picture.translate(0, -pace);
                    TimeUnit.MILLISECONDS.sleep(10);

                }
            }

    }

    public void moveDown() throws InterruptedException {

            if (!(Environment.isBlockedPos(getPos().getX(), (getPos().getY() + CELL_SIZE)))) {

                getPos().translate(0, CELL_SIZE);

                for (int i = 0; i < CELL_SIZE / pace; i++) {

                    picture.translate(0, pace);
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            }

    }

    public void moveRight() throws InterruptedException {

            if (!(Environment.isBlockedPos((getPos().getX() + CELL_SIZE), getPos().getY()))) {

                getPos().translate(CELL_SIZE, 0);

                for (int i = 0; i < CELL_SIZE / pace; i++) {

                    picture.translate(pace, 0);
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            }

    }

    public void moveLeft() throws InterruptedException {


            if (!(Environment.isBlockedPos((getPos().getX() - CELL_SIZE), getPos().getY()))) {

                getPos().translate(-CELL_SIZE, 0);

                for (int i = 0; i < CELL_SIZE / pace; i++) {

                    picture.translate(-pace, 0);
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            }

    }

    public void move(int direction) throws InterruptedException {

        switch (direction) {

            case 0:
                moveUp();
                break;
            case 1 :
                moveDown();
                break;
            case 2 :
                moveLeft();
                break;
            case 3 :
                moveRight();
                break;
            default:
                break;
        }
    }

    @Override
    public void destroy() {
        if(destroyed) {
            return;
        }
        Music.getSound(Music.Sounds.ENEMYDEAD);
        destroyed = true;
        picture.delete();
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

}