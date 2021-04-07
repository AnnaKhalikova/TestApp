package com.istg.service.implementations;

import com.istg.config.AppConfig;
import com.istg.dao.Interface.ContactUsDao;
import com.istg.domain.ContactUs;
import com.istg.service.interfaces.ContactUsService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@SpringBootTest
public class ContactUsServiceImplTest extends TestCase {
    @Autowired
    private ContactUsService contactUsService;

    @MockBean
    private ContactUsDao contactUsDao;


    @Test
    public void SaveContactUs_withGoodParam_True() {
        ContactUs contactUs = new ContactUs();
        contactUs.setName_ct("sa");
        contactUs.setSoname_ct("sad");
        when(contactUsDao.save(contactUs)).thenReturn(1);
        assertEquals(1,contactUsService.save(contactUs));
        verify(contactUsDao,times(1)).save(contactUs);
    }

    @Test
    public void SaveContactUsTest_withBadParam_False() {
        ContactUs contactUs = new ContactUs();
        contactUs.setName_ct("");
        contactUs.setSoname_ct("sad");
        when(contactUsDao.save(contactUs)).thenReturn(0);
        assertEquals(0,contactUsService.save(contactUs));
        verify(contactUsDao,times(0)).save(contactUs);
    }

    @Test
    public void GetAllContactUs_Test() {
        ContactUs contactUs = new ContactUs();
        contactUs.setName_ct("sa");
        contactUs.setSoname_ct("sad");
        when(contactUsDao.getAll()).thenReturn(Stream.of(contactUs).collect(Collectors.toList()));
        assertEquals(1,contactUsService.getAll().size());
        verify(contactUsDao,times(1)).getAll();
    }

    @Test
    public void UpdateContactUsTest_WithGoodParam_True() {
        ContactUs contactUs = new ContactUs();
        contactUs.setName_ct("sa");
        contactUs.setSoname_ct("sad");
        when(contactUsDao.update(contactUs)).thenReturn(1);
        assertEquals(1,contactUsService.update(contactUs));
        verify(contactUsDao,times(1)).update(contactUs);
    }

    @Test
    public void UpdateContactUsTest_WithBadParam_False() {

        when(contactUsDao.update(null)).thenReturn(0);
        assertEquals(0,contactUsService.update(null));
        verify(contactUsDao,times(0)).update(null);
    }

    @Test
    public void DeleteContactUsTest_WithGoodParam_True() {
        ContactUs contactUs = new ContactUs();
        contactUs.setName_ct("sa");
        contactUs.setSoname_ct("sad");

        when(contactUsDao.delete(contactUs)).thenReturn(1);
        assertEquals(1,contactUsService.delete(contactUs));
        verify(contactUsDao,times(1)).delete(contactUs);
    }


    @Test
    public void DeleteContactUsTest_WithBadParam_False() {
        when(contactUsDao.delete(null)).thenReturn(0);
        assertEquals(0,contactUsService.delete(null));
        verify(contactUsDao,times(0)).delete(null);
    }

    @Test
    public void GetByIdContactUsTest_withGoodParam_true() {
        ContactUs contactUs = new ContactUs();
        contactUs.setId_ct(1L);
        contactUs.setName_ct("sa");
        contactUs.setSoname_ct("sad");
        when(contactUsDao.getById(1L)).thenReturn(contactUs);
        assertEquals("sa",contactUsService.getById(1L).getName_ct());
        verify(contactUsDao,times(1)).getById(1L);
    }

    @Test
    public void GetByIdContactUsTest_withBadParam_False() {
        when(contactUsDao.getById(null)).thenReturn(null);
        assertNull(contactUsService.getById(null));
        verify(contactUsDao,times(0)).getById(null);
    }

}