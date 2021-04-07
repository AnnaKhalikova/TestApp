package com.istg.utils;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckDescribeGameTest extends TestCase {

    private String describeGame;
    private boolean expected;

    public CheckDescribeGameTest(String describeGame, boolean expected) {
        this.describeGame = describeGame;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection parametrs(){
        return Arrays.asList(new Object[][]{
                {"test",true},
                {"te",false},
                {"t",false},
                {"testdescribe",true},
                {"Lorem Ipsum - взятый с потолка псевдо-латинский набор слов, но это не совсем так. " +
                        "Его корни уходят в один фрагмент классической латыни " +
                        "45 года н.э., то есть более двух тысячелетий назад. " +
                        "Ричард МакКлинток, профессор латыни из колледжа Hampden-Sydney шта",true},
                {"Lorem Ipsum - взятый с потолка псевдо-латинский набор слов, но это не совсем так. Его корни уходят в один фрагмент классической латыни 45 года н.э., 4" +
                        "то есть более двух тысячелетий назад. Ричард МакКлинток, профессор латыни из колледжа Hampden-Sydney штат Вирджиния",false},
                {"",false},
                {"test",true},
                {"TestDescribeWithDiffIndex",true},
                {"t",false},
                {"TestwithNumber213123",true},
                {"1313",true},
                {"test",true},
                {"testssss",true},
        });
    }

    @Test
    public void CheckDescribeTest_WithParametrizedParam() {
        CheckDescribeGame checkDescribeGame = new CheckDescribeGame();
        checkDescribeGame.setDescribeGame(describeGame);

        boolean result = checkDescribeGame.checkDescribeGame();

        Assert.assertEquals(expected,result);

    }
}