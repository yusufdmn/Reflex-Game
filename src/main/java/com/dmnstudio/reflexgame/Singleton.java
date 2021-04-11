package com.dmnstudio.reflexgame;

public class Singleton {


    public int getReklam() {
        return reklam;
    }

    public void setReklam(int reklam) {
        this.reklam = reklam;
    }

    private int reklam=1;
    private Boolean playMusic=true;
    private static Singleton singleton;

    private Singleton(){

    }


    public Boolean getPlayMusic() {
        return playMusic;
    }

    public void setPlayMusic(Boolean playMusic) {
        this.playMusic = playMusic;
    }

    public static Singleton getInstance(){

        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }


}
