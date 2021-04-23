package org.academiadecodigo.asynctomatics.chupabomberman;

import org.academiadecodigo.bootcamp.Sound;

public class Music {

    private static Sound music ;
    private static Sound sound1;
    private static boolean mute;
    private static Sounds lastplay;

    public static void soundsPlay(String sound){
       if(!mute) {
            // if(sound1 != null){
            //    sound1.stop();
            // }
            sound1 = new Sound(sound);
            sound1.play(true);
       }
    }

    public static void musicPlay(String sound) {
        if(!mute) {
            if (music != null) {
                music.stop();
            }
            music = new Sound(sound);
            music.play(true);
            music.setLoop(-1);
        }
    }

    public static void mute(boolean b, int i){
        if(i == 1) {
            mute = b;
            music.stop();
        }
        if(i == 0){
            mute = b;
            getSound(lastplay);
        }
    }

    public enum Sounds {
        STARTSCREEN,
        START,
        GAMEOVER,
        WIN,
        BOMBEXPLOSION,
        ENEMYDEAD,
        SELECTPLAYER
    }

    public static void getSound(Sounds sounds){

        switch (sounds){
            case STARTSCREEN:
                musicPlay("/resources/startscreen.wav");
                lastplay = Sounds.STARTSCREEN;
                break;
            case START:
                musicPlay("/resources/game.wav");
                lastplay = Sounds.START;
                break;
            case GAMEOVER:
                musicPlay("/resources/gameover.wav");
                lastplay = Sounds.GAMEOVER;
                break;
            case WIN:
                musicPlay("/resources/win.wav");
                lastplay = Sounds.WIN;
                break;
            case BOMBEXPLOSION:
                soundsPlay("/resources/explosion.wav");
                break;
            case ENEMYDEAD:
                soundsPlay("/resources/ohlarecas.wav");
                break;
            case SELECTPLAYER:
                soundsPlay("/resources/beep.wav");
        }
    }

}
