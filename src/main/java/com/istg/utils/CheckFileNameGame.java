package com.istg.utils;

public class CheckFileNameGame {

    private String filenameGame;

    public CheckFileNameGame(String filenameGame) {
        this.filenameGame = filenameGame;
    }

    public CheckFileNameGame() {
    }

    public String getFilenameGame() {
        return filenameGame;
    }

    public void setFilenameGame(String filenameGame) {
        this.filenameGame = filenameGame;
    }

    public boolean CheckFilenameGame(){
        if(filenameGame.length()>=36 && filenameGame.length()<=255){
            return true;
        }else{
            return false;
        }
    }
}
