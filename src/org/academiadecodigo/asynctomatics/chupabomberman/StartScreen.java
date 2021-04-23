package org.academiadecodigo.asynctomatics.chupabomberman;

import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.concurrent.TimeUnit;

public class StartScreen implements KeyboardHandler{

    private Field field;
    private Picture startScreen;
    private Picture cursor;
    private Keyboard keyboard;
    private int numOfPlayers;
    private Game game;
    private boolean started;
    private int muteType;

    public StartScreen(Field field, Game game) {
        this.game = game;
        this.field = field;
        numOfPlayers = 1;
        startScreen = new Picture(field.getPadding(), field.getPadding(),"resources/MENUscreen.gif");
        cursor = new Picture(field.getPadding()+ (5* field.getCellSize()), field.getPadding()+(9* field.getCellSize()), "resources/cursor.gif");

        keyboard = new Keyboard(this);
        show();
        setKeyboard();
        started = false;
    }

    public void show(){
        startScreen.draw();
        cursor.draw();
    }

    public void setKeyboard() {
        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent init = new KeyboardEvent();
        init.setKey(KeyboardEvent.KEY_SPACE);
        init.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent mute = new KeyboardEvent();
        mute.setKey(KeyboardEvent.KEY_6);
        mute.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent quit = new KeyboardEvent();
        quit.setKey(KeyboardEvent.KEY_8);
        quit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(down);
        keyboard.addEventListener(up);
        keyboard.addEventListener(init);
        keyboard.addEventListener(mute);
        keyboard.addEventListener(quit);
        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_6) {
                if(muteType == 0){
                    Music.mute(true, 1);
                    muteType = 1;
                } else {
                    Music.mute(false, 0);
                    muteType = 0;
                }
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_8) {
                System.exit(0);
            }

            if(!started) {
                if (numOfPlayers == 1) {
                    if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
                        cursor.translate(0, 0);
                    }

                    if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
                        cursor.translate(0, 25);
                        numOfPlayers = 2;
                        Music.getSound(Music.Sounds.SELECTPLAYER);
                    }
                }

                if (numOfPlayers == 2) {
                    if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
                        cursor.translate(0, -25);
                        numOfPlayers = 1;
                        Music.getSound(Music.Sounds.SELECTPLAYER);
                    }
                    if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
                        cursor.translate(0, 0);
                    }
                }
            }

            if(keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                started = true;
                cursor.delete();
                startScreen.delete();
                game.init(numOfPlayers);
            }
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }


}




