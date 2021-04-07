package com.istg.utils;

import com.istg.domain.ContactUs;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckNameContactUsTest extends TestCase {

    private ContactUs contactUs;
    private boolean expected;


    public CheckNameContactUsTest(ContactUs contactUs, boolean expected) {
        this.contactUs = contactUs;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection parametrs(){
        ContactUs contactUs1 = new ContactUs();
        contactUs1.setName_ct("Temp");
        ContactUs contactUs2 = new ContactUs();
        contactUs2.setName_ct("Te");
        ContactUs contactUs3 = new ContactUs();
        contactUs3.setName_ct("Lorem Ipsum - взятый с потолка псевдо-латинский набор слов, но это не совсем так. Его корни уходят в один фрагмент классической латыни 45 года н.э., 4\" +\n" +
                "                        \"то есть более двух тысячелетий назад. Ричард МакКлинток, профессор латыни из колледжа Hampden-Sydney штат Вирджиния");
        ContactUs contactUs4 = new ContactUs();
        contactUs4.setName_ct("Lorem Ipsum - взятый ");

        return Arrays.asList(new Object[][]{
                {contactUs1,true},
                {contactUs2,false},
                {contactUs3,false},
                {contactUs1,true},
                {contactUs1,true},
                {contactUs2,false},
                {contactUs4,true},
                {contactUs1,true},
                {contactUs4,true},
                {contactUs3,false},
                {contactUs2,false},
                {contactUs1,true},
                {contactUs4,true},
                {contactUs1,true},
        });
    }

    @Test
    public void CheckNameContactUsTest_WithParametrizedParam() {
        CheckNameContactUs checkNameContactUs = new CheckNameContactUs();
        checkNameContactUs.setContactUs(contactUs);

        boolean result = checkNameContactUs.checkNameContactUs();

        Assert.assertEquals(expected,result);
    }
}