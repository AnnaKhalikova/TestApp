package com.istg.utils;

public class CheckNameGame {

    private String nameGame;

    public CheckNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public CheckNameGame() {
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public boolean checkNameGame(){
        if(nameGame.length()>=3 && nameGame.length()<=255){
            return true;
        }else{
            return false;
        }
    }
}
