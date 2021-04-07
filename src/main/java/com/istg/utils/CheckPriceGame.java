package com.istg.utils;

public class CheckPriceGame {
    private int priceGame;

    public CheckPriceGame(int priceGame) {
        this.priceGame = priceGame;
    }

    public CheckPriceGame() {
    }

    public int getPriceGame() {
        return priceGame;
    }

    public void setPriceGame(int priceGame) {
        this.priceGame = priceGame;
    }

    public boolean checkPriceGame(){
        if(priceGame>=1&&priceGame<=2000){
            return true;
        }else{
            return false;
        }
    }
}
