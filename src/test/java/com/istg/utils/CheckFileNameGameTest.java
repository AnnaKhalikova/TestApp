package com.istg.utils;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckFileNameGameTest extends TestCase {

    private String filenameGame;
    private boolean expected;

    public CheckFileNameGameTest(String filenameGame, boolean expected) {
        this.filenameGame = filenameGame;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection parametrs(){
        return Arrays.asList(new Object[][]{
                {"filename://testfilename.testfilename",true},
                {"filename://testfilename.testfi",false},
                {"filen",false},
                {"filename://testfilename.testfilen" +
                        "ametemptestetestetetetetetetetetetetetetetetetetetetete" +
                        "tetetetetetetetetetetetetetetetetetetetetetet" +
                        "etetetetetetetetetetetetetetetetetetete" +
                        "tetetetetetetetetetetetetetetetetetetet" +
                        "etetetetetetetetetetetetetetetetetetetetetetetetetetet" +
                        "etetetetetetetetetetetetetetetetetetete" +
                        "tetetetetetetetetetetetetetetetetetete" +
                        "tetetetetetetetetetetetetetetetetetetete",false},
                {"filename://testfilename.testfile",false},
                {"filename://testfilename.testfilenam",false},
                {"filename://testfilename.testfilename",true},
                {"filename://testfilename.testfilename",true},
                {"filename://testfilename.testfilename22212311111111112222222222222222222222222222",true},
                {"//testfilename.testfilename",false},
                {"filename://testfilename.testfilenameworkvariant",true},
                {"filename://testfilename.testfilename",true},
                {"filename://testfilename.testfilenametest2",true},
                {"filename://testfilename.testfilenametest3",true},

        });
    }

    @Test
    public void CheckFilenameGameTest_WithParametrizedParam() {
        CheckFileNameGame fileNameGame = new CheckFileNameGame();
        fileNameGame.setFilenameGame(filenameGame);

        boolean result = fileNameGame.CheckFilenameGame();

        Assert.assertEquals(expected,result);

    }
}