package com.istg.controller;

import com.istg.config.AppConfig;
import com.istg.dao.Interface.CardProductDao;
import com.istg.dao.Interface.CheckoutOrderDao;
import com.istg.service.interfaces.CardProductService;
import com.istg.service.interfaces.CheckoutOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CardController.class)
@ContextConfiguration(classes = AppConfig.class)
@AutoConfigureMockMvc
@ComponentScan("com.istg")
@WithUserDetails(value = "AND")
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckoutOrderDao checkoutOrderDao;

    @MockBean
    private CardProductDao cardProductDao;

    @Autowired
    private CheckoutOrderService checkoutOrderService;

    @Autowired
    private CardProductService cardProductService;

    @Test
    public void CardUserTest_LoadPageAndCheckExists_True() throws Exception {
        this.mockMvc.perform(get("/card/carduser"))
                .andExpect(status().isOk());
    }

    @Test
    public void CardUserTest_CountNode_0() throws Exception {
        this.mockMvc.perform(get("/card/carduser"))
                .andExpect(status().isOk())
        .andExpect(xpath("//*[@id='item']").nodeCount(0));
    }

    @Test
    public void AddGameToCardTest_AddGameByUrl_True() throws Exception {
        this.mockMvc.perform(get("/card/addtocard/3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games/"));
    }

    @Test
    public void DeleteCardTest_DeleteCardUser_True() throws Exception {
        this.mockMvc.perform(get("/card/deletecard/3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/card/carduser"));
    }

    @Test
    public void CheckoutOrderTest_SendFormSuccess_FullParam_True() throws Exception {
        this.mockMvc.perform(post("/card/orderCard")
                .param("name_client","test")
                .param("soname_client","test")
                .param("email_client","test")
                .param("phone_client","test")
                .with(csrf())).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games/"));
    }

    @Test
    public void CheckoutOrderTest_SendFormFailed_EmptyParam_True() throws Exception {
        this.mockMvc.perform(post("/card/orderCard")
                .param("name_client","")
                .param("soname_client","test")
                .param("email_client","test")
                .param("phone_client","test")
                .with(csrf())).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games/"));
    }

}
