package com.istg.utils;

public class CheckDescribeGame {
    private String describeGame;

    public CheckDescribeGame(String describeGame) {
        this.describeGame = describeGame;
    }

    public CheckDescribeGame() {
    }

    public String getDescribeGame() {
        return describeGame;
    }

    public void setDescribeGame(String describeGame) {
        this.describeGame = describeGame;
    }

    public boolean checkDescribeGame(){
        if(describeGame.length()>=3&&describeGame.length()<=255){
            return true;
        }else{
            return false;
        }
    }
}
