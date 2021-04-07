package com.istg.utils;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckNameGameTest extends TestCase {

    private String nameGame;
    private boolean expected;

    public CheckNameGameTest(String nameGame,boolean expected) {
        this.nameGame = nameGame;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection parametrs(){
        return Arrays.asList(new Object[][]{
                {"name",true},
                {"nam",true},
                {"n",false},
                {"nameGame",true},
                {"Lorem Ipsum - взятый с потолка псевдо-латинский набор слов, но это не совсем так. " +
                        "Его корни уходят в один фрагмент классической латыни " +
                        "45 года н.э., то есть более двух тысячелетий назад. " +
                        "Ричард МакКлинток, профессор латыни из колледжа Hampden-Sydney шта",true},
                {"Lorem Ipsum - взятый с потолка псевдо-латинский набор слов, но это не совсем так. Его корни уходят в один фрагмент классической латыни 45 года н.э., 4" +
                        "то есть более двух тысячелетий назад. Ричард МакКлинток, профессор латыни из колледжа Hampden-Sydney штат Вирджиния",false},
                {"",false},
                {"nameJust",true},
                {"TestNameAgain",true},
                {"n",false},
                {"NameWithNum213",true},
                {"13123113",true},
                {"nn",false},
                {"testssss",true},
        });
    }

    @Test
    public void CheckNameGameTest_WithParametrizedParam() {
        CheckNameGame checkNameGame = new CheckNameGame();
        checkNameGame.setNameGame(nameGame);

        boolean result = checkNameGame.checkNameGame();

        Assert.assertEquals(expected,result);
    }
}