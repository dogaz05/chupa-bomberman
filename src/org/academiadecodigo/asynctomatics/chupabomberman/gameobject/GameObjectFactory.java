package org.academiadecodigo.asynctomatics.chupabomberman.gameobject;

import org.academiadecodigo.asynctomatics.chupabomberman.blocks.SoftBlocks;
import org.academiadecodigo.asynctomatics.chupabomberman.blocks.SolidBlocks;
import org.academiadecodigo.asynctomatics.chupabomberman.enemies.Enemies;
import org.academiadecodigo.asynctomatics.chupabomberman.enemies.Enemy;
import org.academiadecodigo.asynctomatics.chupabomberman.field.Field;
import org.academiadecodigo.asynctomatics.chupabomberman.players.Player;
import org.academiadecodigo.asynctomatics.chupabomberman.players.Player1;
import org.academiadecodigo.asynctomatics.chupabomberman.players.Player2;

public class GameObjectFactory {


    public static SolidBlocks[] createSolidBlocks(Field field) {

        String picturePath = "resources/solidBlack.gif";

        SolidBlocks[] solidBlocks = new SolidBlocks[72];
        for (int i = 0; i < solidBlocks.length; i++) {
            //create top wall
            for (int j = field.getPadding(); j < (field.getWidth() + field.getPadding()); j += field.getCellSize()) {
                solidBlocks[i] = new SolidBlocks(j, field.getPadding(), field, picturePath);
                i++;
            }
            //create bottom wall
            for (int h = (field.getPadding()); h < (field.getWidth() + field.getPadding()); h += field.getCellSize()) {
                solidBlocks[i] = new SolidBlocks(h, (field.getPadding() + field.getHeight() - field.getCellSize()), field, picturePath);
                i++;
            }

            //create left wall
            for (int k = (field.getPadding() + field.getCellSize()); k < (field.getPadding() + field.getHeight() - field.getCellSize()); k += field.getCellSize()) {
                solidBlocks[i] = new SolidBlocks(field.getPadding(), k, field, picturePath);
                i++;
            }
            //create right wall
            for (int g = (field.getPadding() + field.getCellSize()); g < (field.getPadding() + field.getHeight() - field.getCellSize()); g += field.getCellSize()) {
                solidBlocks[i] = new SolidBlocks((field.getPadding() + field.getWidth() - field.getCellSize()), g, field, picturePath);
                i++;
            }

            //create inside solid blocks
            for (int q = 2; q < field.getRows(); q++) {
                for (int w = 2; w < field.getCols(); w++) {
                    if ((q % 2 != 0) && (w % 2 != 0)) {
                        solidBlocks[i] = new SolidBlocks((field.getPadding() + (field.getCellSize() * (w - 1))), (field.getPadding() + (field.getCellSize() * (q - 1))), field, "resources/solidRed.gif");
                        i++;
                    }
                }
            }
        }
        return solidBlocks;
    }

    public static SoftBlocks[] createSoftBlocks(Field field) {

        SoftBlocks[] array = new SoftBlocks[40];

        // row 2

        int counter = 1;
        int rowY = field.getCellSize() + field.getPadding();

        array[0] = new SoftBlocks(getPixelPos(3, field), rowY, field);
        array[1] = new SoftBlocks(getPixelPos(4, field), rowY, field);
        array[2] = new SoftBlocks(getPixelPos(6, field), rowY, field);
        array[3] = new SoftBlocks(getPixelPos(8, field), rowY, field);
        array[4] = new SoftBlocks(getPixelPos(9, field), rowY, field);
        array[5] = new SoftBlocks(getPixelPos(10, field), rowY, field);

        // row 3
        counter++;
        rowY = field.getCellSize()*counter + field.getPadding();

        array[6] = new SoftBlocks(getPixelPos(5, field), rowY, field);
        array[7] = new SoftBlocks(getPixelPos(7, field), rowY, field);

        // row 4
        counter++;
        rowY = field.getCellSize()*counter + field.getPadding();


        array[8] = new SoftBlocks(getPixelPos(2, field), rowY, field);
        array[9] = new SoftBlocks(getPixelPos(3, field), rowY, field);
        array[10] = new SoftBlocks(getPixelPos(5, field), rowY, field);
        array[11] = new SoftBlocks(getPixelPos(7, field), rowY, field);
        array[12] = new SoftBlocks(getPixelPos(9, field), rowY, field);
        array[13] = new SoftBlocks(getPixelPos(10, field), rowY, field);
        array[14] = new SoftBlocks(getPixelPos(11, field), rowY, field);
        array[15] = new SoftBlocks(getPixelPos(12, field), rowY, field);

        // row 5
        counter++;
        rowY = field.getCellSize()*counter + field.getPadding();


        array[16] = new SoftBlocks(getPixelPos(1, field), rowY, field);
        array[17] = new SoftBlocks(getPixelPos(3, field), rowY, field);
        array[18] = new SoftBlocks(getPixelPos(5, field), rowY, field);

        // row 6
        counter++;
        rowY = field.getCellSize()*counter + field.getPadding();

        array[19] = new SoftBlocks(getPixelPos(1, field), rowY, field);
        array[20] = new SoftBlocks(getPixelPos(4, field), rowY, field);
        array[21] = new SoftBlocks(getPixelPos(10, field), rowY, field);
        array[22] = new SoftBlocks(getPixelPos(11, field), rowY, field);
        array[23] = new SoftBlocks(getPixelPos(12, field), rowY, field);


        // row 7

        counter++;
        rowY = field.getCellSize()*counter + field.getPadding();

        array[24] = new SoftBlocks(getPixelPos(9, field), rowY, field);
        array[25] = new SoftBlocks(getPixelPos(11, field), rowY, field);

        // row 8

        counter++;
        rowY = field.getCellSize()*counter + field.getPadding();

        array[26] = new SoftBlocks(getPixelPos(1, field), rowY, field);
        array[27] = new SoftBlocks(getPixelPos(2, field), rowY, field);
        array[28] = new SoftBlocks(getPixelPos(4, field), rowY, field);
        array[29] = new SoftBlocks(getPixelPos(12, field), rowY, field);
        array[30] = new SoftBlocks(getPixelPos(13, field), rowY, field);

        // row 9

        counter++;
        rowY = field.getCellSize()*counter + field.getPadding();

        array[31] = new SoftBlocks(getPixelPos(3, field), rowY, field);
        array[32] = new SoftBlocks(getPixelPos(5, field), rowY, field);
        array[33] = new SoftBlocks(getPixelPos(7, field), rowY, field);
        array[34] = new SoftBlocks(getPixelPos(11, field), rowY, field);

        // row 10

        counter++;
        rowY = field.getCellSize()*counter + field.getPadding();

        array[35] = new SoftBlocks(getPixelPos(4, field), rowY, field);
        array[36] = new SoftBlocks(getPixelPos(6, field), rowY, field);
        array[37] = new SoftBlocks(getPixelPos(7, field), rowY, field);
        array[38] = new SoftBlocks(getPixelPos(9, field), rowY, field);
        array[39] = new SoftBlocks(getPixelPos(10, field), rowY, field);

        return array;
    }

    public static Enemies[] createEnemies(int numbPlayers, Field field) {
        Enemies[] array;
        int index = 0;
        if(numbPlayers == 1) {
            array = new Enemy[5];
            array[index++] = new Enemy(getPixelPos(13, field), getPixelPos(9, field), field);
        } else {
            array = new Enemy[4];
        }

        array[index++] = new Enemy(getPixelPos(13, field), getPixelPos(1, field), field);
        array[index++] = new Enemy(getPixelPos(2, field), getPixelPos(5, field), field);
        array[index++] = new Enemy(getPixelPos(7, field), getPixelPos(6, field), field);
        array[index] = new Enemy(getPixelPos(1, field), getPixelPos(8, field), field);


        return array;
    }

    public static Player[] createPlayer(int numbPlayers, Field field) {

        Player[] array = new Player[numbPlayers];
        array[0] = new Player1(getPixelPos(1, field), getPixelPos(1, field), field);

        if(numbPlayers == 2) {
            array[1] = new Player2(getPixelPos(13, field), getPixelPos(9, field), field);
        }


        return array;

    }

    private static int getPixelPos(int colOrRow, Field field) {

        return ((colOrRow*field.getCellSize()) + field.getPadding());

    }
}
