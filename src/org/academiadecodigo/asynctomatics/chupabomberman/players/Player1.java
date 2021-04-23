package org.academiadecodigo.asynctomatics.chupabomberman.players;
import org.academiadecodigo.asynctomatics.chupabomberman.Bomb;
import org.academiadecodigo.asynctomatics.chupabomberman.Environment;
import org.academiadecodigo.asynctomatics.chupabomberman.Music;
import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.asynctomatics.chupabomberman.field.FieldPosition;
import org.academiadecodigo.asynctomatics.chupabomberman.gameobject.GameObject;
import org.academiadecodigo.asynctomatics.chupabomberman.interfaces.Destroyable;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player1 extends Player implements KeyboardHandler {

    private Keyboard keyboard;
    private Picture player;
    private Bomb bomb;
    private boolean destroyed;
    private int pace;

    public Player1(int x, int y, Field field) {
        super(new FieldPosition(x, y, field));
        this.keyboard = new Keyboard(this);
        this.player = new Picture(x, y, "resources/P1/down.gif" );
        this.pace = getPos().getField().getCellSize();
        player.draw();
        setKeyboard();
    }

    public void setKeyboard() {
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent dropBomb = new KeyboardEvent();
        dropBomb.setKey(KeyboardEvent.KEY_M);
        dropBomb.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(down);
        keyboard.addEventListener(up);
        keyboard.addEventListener(dropBomb);
    }

    @Override // aquilo que acontece quando as teclas são premidas
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_M) {
            dropBomb();
        }

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                moveLeft();
                break;
            case KeyboardEvent.KEY_RIGHT:
                moveRight();
                break;
            case KeyboardEvent.KEY_UP:
                moveUp();
                break;
            case KeyboardEvent.KEY_DOWN:
                moveDown();
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private void moveLeft() {
        int x = getPos().getX() - pace;
        if(Environment.isBlockedPos((getPos().getX()-pace), getPos().getY())){
            return;
        }
        player.load("resources/P1/left.gif");
        player.translate((-pace), 0);
        getPos().translate((-pace), 0);

        amIDead();
    }

    private void moveRight() {
        int x = getPos().getX() + pace;
        if(Environment.isBlockedPos(x, getPos().getY())) {
            return;
        }
        player.load("resources/P1/right.gif");
        player.translate(pace, 0);
        getPos().translate(pace, 0);

        amIDead();
    }

    private void moveUp() {
        int y = getPos().getY() - pace;
        if(Environment.isBlockedPos(getPos().getX(), y)) {
            return;
        }
        player.load("resources/P1/up.gif");
        player.translate(0, -pace);
        getPos().translate(0, -pace);

        amIDead();
    }

    private void moveDown() {
        int y = getPos().getY() + pace;
        if(Environment.isBlockedPos(getPos().getX(), y)) {
            return;
        }
        player.load("resources/P1/down.gif");
        player.translate(0, pace);
        getPos().translate(0, pace);

        amIDead();
    }

    private void amIDead() {
        if(Environment.checkPvsECollision(getPos())) {
            destroy();
        }
    }

    public void dropBomb() {
        bomb = new Bomb(getPos().getX(), getPos().getY(), getPos().getField());
        if (Environment.saveBomb(bomb)) {
            bomb.getBombPic().draw();
        }
    }

    @Override
    public void destroy() {
        if(destroyed) {
            return;
        }
        player.delete();
        destroyed = true;
        //Environment.getGameObjects().remove(this);
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;

    }


}
