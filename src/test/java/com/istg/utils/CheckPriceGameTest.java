package com.istg.utils;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckPriceGameTest extends TestCase {

    private int price;
    private boolean expected;

    public CheckPriceGameTest(int price, boolean expected) {
        this.price = price;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection parametrs(){
        return Arrays.asList(new Object[][]{
                {1,true},
                {2000,true},
                {0,false},
                {2001,false},
                {567,true},
                {431,true},
                {-2,false},
                {55012,false},
                {1999,true},
                {11,true},
                {20000,false},
                {2010,false},
                {1,true},
                {2,true},
        });
    }


    @Test
    public void CheckPriceGameTest_WithParametrizedParam() {
        CheckPriceGame checkPriceGame = new CheckPriceGame();
        checkPriceGame.setPriceGame(price);
        boolean result = checkPriceGame.checkPriceGame();
        Assert.assertEquals(expected,result);


    }
}