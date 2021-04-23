package org.academiadecodigo.asynctomatics.chupabomberman;


import org.academiadecodigo.asynctomatics.chupabomberman.blocks.SolidBlocks;
import org.academiadecodigo.bootcamp.Sound;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {


        Game game = new Game(15, 11);
        game.startScreen();

        while(!game.isInitialized()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.getStackTrace();
            }
            if(game.isInitialized()) {
                game.start();
                return;
            }
        }


    }
}
