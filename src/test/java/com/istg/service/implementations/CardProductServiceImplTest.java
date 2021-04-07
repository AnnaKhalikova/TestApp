package com.istg.service.implementations;

import com.istg.config.AppConfig;
import com.istg.dao.Interface.CardProductDao;
import com.istg.dao.Interface.UserDao;
import com.istg.domain.CardProduct;
import com.istg.domain.User;
import com.istg.service.interfaces.CardProductService;
import com.istg.service.interfaces.UserService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@SpringBootTest
public class CardProductServiceImplTest extends TestCase {

    @Autowired
    private CardProductService cardProductService;

    @MockBean
    private CardProductDao cardProductDao;

    @MockBean
    private UserService userService;

    @Test
    public void FindAllCartTest_FindByUserId_UserId1_True() {
        when(cardProductService.find_all_by_id_user(1L)).thenReturn(Stream.of(new CardProduct(1L,1L,null)).collect(Collectors.toList()));

        assertEquals(1,cardProductService.find_all_by_id_user(1L).size());

        verify(cardProductDao, times(1)).find_all_by_id_user(1L);

    }

    @Test
    public void GetAllTest_GetAllCard_True() {
        when(cardProductService.getAll()).thenReturn(Stream.of(new CardProduct(1L,1L,new Date(new java.util.Date().getTime()))).collect(Collectors.toList()));

        assertEquals(1,cardProductService.getAll().size());

        verify(cardProductDao, times(1)).getAll();

    }

    @Test
    public void SaveCardTest_SaveCardSuccess_WithFullParam_True() {
        User user = new User();
        user.setId_user(1L);
        user.setName("Tim");
        user.setPassword("Smth");

        CardProduct cardProduct = new CardProduct();
        when(cardProductDao.save(cardProduct,user )).thenReturn(1);
        assertEquals(1,cardProductService.save(cardProduct,user));
        verify(cardProductDao,times(1)).save(cardProduct,user);
    }

    @Test
    public void SaveCardTest_SaveCardFailed_WithEmptyParam_True() {
        CardProduct cardProduct = new CardProduct();
        when(cardProductDao.save(cardProduct,null)).thenReturn(0);
        assertEquals(0,cardProductService.save(cardProduct,null));
        verify(cardProductDao,times(0)).save(cardProduct,null);
    }

    @Test
    public void DeleteCardTest_DeleteByUserSuccess_UserId1_True() {
        User user = new User();
        user.setId_user(1L);
        user.setName("Tim");
        user.setPassword("Smth");

        CardProduct cardProduct = new CardProduct();
        cardProduct.setId_card(1L);
        cardProduct.setId_game(1L);

        when(cardProductDao.delete(cardProduct,user)).thenReturn(1);
        assertEquals(1,cardProductService.delete(cardProduct,user));


        verify(cardProductDao,times(1)).delete(cardProduct,user);
     }
    @Test
    public void DeleteCardTest_DeleteByUserFailed_EmptyParam_True() {
        User user = new User();
        user.setId_user(1L);
        user.setName("Tim");
        user.setPassword("Smth");

        when(cardProductDao.delete(null,user)).thenReturn(0);
        assertEquals(0,cardProductService.delete(null,user));

        verify(cardProductDao,times(0)).delete(null,user);
    }

    @Test
    public void CreateCardTest_CreateCardForNewUserSuccess_UserId1_True() {
        User user = new User();
        user.setId_user(1L);
        user.setName("Tim");
        user.setPassword("Smth");

        when(cardProductDao.create_card(user)).thenReturn(1);
        assertEquals(1,cardProductService.create_card(user));
        verify(cardProductDao,times(1)).create_card(user);

    }

    @Test
    public void CreateCardTest_CreateCardForNewUserSucess_EmptyParam_True() {
        when(cardProductDao.create_card(null)).thenReturn(0);
        assertEquals(0,cardProductService.create_card(null));
        verify(cardProductDao,times(0)).create_card(null);
    }

    @Test
    public void CheckExistsCardTest_CheckExistsFailed_UserId1_True() {
        when(cardProductDao.checkExists(1L)).thenReturn(true);
        assertTrue(cardProductService.checkExists(1L));
        verify(cardProductDao,times(1)).checkExists(1L);
    }

    @Test
    public void CheckExistsCardTest_CheckExists_EmptyParam_True() {
        when(cardProductDao.checkExists(null)).thenReturn(false);
        assertFalse(cardProductService.checkExists(null));
        verify(cardProductDao,times(0)).checkExists(null);
    }

    @Test
    public void SaveCardWithoutUserTest_SaveCardSuccess_WithFullParam_True() {
        CardProduct cardProduct = new CardProduct();
        cardProduct.setId_card(1L);
        cardProduct.setId_game(1L);

        when(cardProductDao.save(cardProduct)).thenReturn(1);
        assertEquals(1,cardProductService.save(cardProduct));
        verify(cardProductDao,times(1)).save(cardProduct);
    }

    @Test
    public void SaveCardWithoutUserTest_SaveCardFailed_WithEmptyParam_True() {
        CardProduct cardProduct = new CardProduct();
        cardProduct.setId_card(-1L);
        cardProduct.setId_game(1L);

        when(cardProductDao.save(cardProduct)).thenReturn(0);
        assertEquals(0,cardProductService.save(cardProduct));
        verify(cardProductDao,times(0)).save(cardProduct);
    }

    @Test
    public void GetAllCardTest_GetAll_True() {
        CardProduct cardProduct = new CardProduct();
        cardProduct.setId_card(-1L);
        cardProduct.setId_game(1L);

        when(cardProductDao.getAll()).thenReturn(Stream.of(cardProduct).collect(Collectors.toList()));
        assertEquals(1,cardProductService.getAll().size());

        verify(cardProductDao,times(1)).getAll();
    }

    @Test
    public void EditCardTest_EditCardSuccess_WithId1_True() {
        CardProduct cardProduct = new CardProduct();
        cardProduct.setId_card(1L);
        cardProduct.setId_game(1L);

        when(cardProductDao.update(cardProduct)).thenReturn(1);
        assertEquals(1,cardProductService.update(cardProduct));

        verify(cardProductDao,times(1)).update(cardProduct);
    }

    @Test
    public void EditCardTest_EditCardFailed_WithEmptyParam_True() {
        when(cardProductDao.update(null)).thenReturn(0);
        assertEquals(0,cardProductService.update(null));

        verify(cardProductDao,times(0)).update(null);
    }

    @Test
    public void DeleteCardTest_DeleteSuccess_WithFulllParam_True() {
        CardProduct cardProduct = new CardProduct();
        cardProduct.setId_card(1L);
        cardProduct.setId_game(1L);
        when(cardProductDao.delete(cardProduct)).thenReturn(1);
        assertEquals(1,cardProductService.delete(cardProduct));
        verify(cardProductDao,times(1)).delete(cardProduct);
    }

    @Test
    public void DeleteCardTest_DeleteFailed_WithEmptyParam_True() {
        when(cardProductDao.delete(null)).thenReturn(0);
        assertEquals(0,cardProductService.delete(null));
        verify(cardProductDao,times(0)).delete(null);
    }

    @Test
    public void GetCardByIdTest_GetByIdSuccess_WithId1_True() {
        CardProduct cardProduct = new CardProduct();
        cardProduct.setId_card(1L);
        cardProduct.setId_game(1L);

        when(cardProductDao.getById(1L)).thenReturn(cardProduct);
        assertEquals(Long.valueOf(1),cardProductService.getById(1L).getId_card());
        verify(cardProductDao,times(1)).getById(1L);
    }
    @Test
    public void GetCardByIdTest_GetByIdFailed_WithEmptyParam_True() {
        when(cardProductDao.getById(null)).thenReturn(null);
        assertNull(cardProductService.getById(1L));
        verify(cardProductDao,times(1)).getById(1L);
    }
}