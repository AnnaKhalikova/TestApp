package com.istg.controller;


import com.istg.config.AppConfig;
import com.istg.config.WebSecurityConfig;
import com.istg.dao.Interface.*;
import com.istg.service.interfaces.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@ContextConfiguration(classes = {AppConfig.class, WebSecurityConfig.class})
@AutoConfigureMockMvc
@ComponentScan("com.istg")
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminController adminController;

    @MockBean
    private CheckoutOrderDao checkoutOrderDao;

    @MockBean
    private TableGamesDao tableGamesDao;

    @MockBean
    private ContactUsDao contactUsDao;

    @MockBean
    private UserDao userDao;

    @MockBean
    private FeedBackDao feedBackDao;

    @MockBean
    private CheckoutOrderService checkoutOrderService;

    @MockBean
    private TableGameService tableGameService;

    @Autowired
    private ContactUsService contactUsService;

    @MockBean
    private UserService userService;

    @Autowired
    private FeedBackService feedBackService;

    @Test
    public void HomeAdminPageLoadTest_LoadPage_True() throws Exception {
        this.mockMvc.perform(get("/admin/home")
                .with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='title_admin']")
                        .string("Списки объектов сайта для администратора"));
    }

    @Test
    public void HomeAdminPageTest_CountLinkOnPage_5() throws Exception {
        this.mockMvc.perform(get("/admin/home").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='item_link']").nodeCount(5));
    }

    @Test
    public void AdminPanelListGamePageTest_LoadPage_True()throws Exception{
        this.mockMvc.perform(get("/admin/listofgame").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='text_page']").string("List of all games"));
    }

    @Test
    public void AdminPanelListGamePage_CountNode_0()throws Exception{
        this.mockMvc.perform(get("/admin/listofgame").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='item']").nodeCount(0));
    }

    @Test
    public void AdminPanelAddGameFormTest_SendFormSuccess_ParamFull_True() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "orig", null, "bar".getBytes());
        this.mockMvc.perform(fileUpload("/admin/listofgame/addgame").file(file)
                .param("name_game","test")
                .param("describe_game","test")
                .param("price","123")
                .param("type_id","1").with(csrf()).with(user("adm").password("123")
                        .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listofgame"));
    }

    @Test
    public void AdminPanelAddGameFormTest_SendFormFailed_EmptyParam_True() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "orig", null, "bar".getBytes());
        this.mockMvc.perform(fileUpload("/admin/listofgame/addgame").file(file)
                .param("name_game","")
                .param("describe_game","test")
                .param("price","123")
                .param("type_id","1").with(csrf()).with(user("adm").password("123")
                        .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listofgame"));
    }

    @Test
    public void AdminPanelEditGameTest_SendFormSuccess_FullParam_True() throws Exception{
        MockMultipartFile file = new MockMultipartFile("file_edit", "orig", null, "bar".getBytes());
        this.mockMvc.perform(fileUpload("/admin/Edit/4").file(file)
                .param("name_game_edit","zoomzoomator")
                .param("describe_game_edit","test")
                .param("price_edit","123")
                .param("type_id_edit","1").with(csrf()).with(user("adm").password("123")
                        .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listofgame"));
    }


    @Test
    public void AdminPanelEditGameTest_SendFormFailed_EmptyParam_True() throws Exception{
        MockMultipartFile file = new MockMultipartFile("file_edit", "orig", null, "bar".getBytes());
        this.mockMvc.perform(fileUpload("/admin/Edit/-1").file(file)
                .param("name_game_edit","zoomzoomator")
                .param("describe_game_edit","test")
                .param("price_edit","123")
                .param("type_id_edit","1").with(csrf()).with(user("adm").password("123")
                        .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listofgame"));
    }

    @Test
    public void AdminPanelDeleteGameTest_DeleteGameById7_True() throws Exception {
        this.mockMvc.perform(get("/admin/delete/7").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listofgame"));
    }

    @Test
    public void AdminPanelListUserTest_LoadPage_True()throws Exception{
        this.mockMvc.perform(get("/admin/listofuser").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='title_admin']")
                        .string("List of all users"));
    }

    @Test
    public void AdminPanelListUserTest_CountNode_0()throws Exception{
        this.mockMvc.perform(get("/admin/listofuser").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='item']")
                        .nodeCount(0));
    }

    @Test
    public void AdminPanelDeleteUserTest_DeleteUser_True()throws Exception{
        this.mockMvc.perform(get("/admin/listofuser/delete/2").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listofuser"));
    }

    @Test
    public void AdminPanelListFeedBackTest_LoadPage_True()throws Exception{
        this.mockMvc.perform(get("/admin/listoffeedback").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='title_admin']").string("List of all feedback"));
    }

    @Test
    public void AdminPanelListUserUpdateTest_SendForm_True()throws Exception{
        this.mockMvc.perform(post("/admin/listofuser/edit/3")
                .param("username_user_edit","Tom")
                .param("name_user_edit","Tommas")
                .param("soname_user_edit","Shelby")
                .param("email_user_edit","tomshelb@mail.cu")
                .param("role","ADMIN").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void AdminPanelListFeedBackTest_CountNode_0()throws Exception{
        this.mockMvc.perform(get("/admin/listoffeedback").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='item']")
                        .nodeCount(0));
    }

    @Test
    public void AdminPanelListFeedBackTest_DeleteFeedback_True()throws Exception{
        this.mockMvc.perform(get("/admin/listoffeedback/delete/1").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listoffeedback"));
    }

    @Test
    public void AdminPanelListCardUserTest_LoadPage_True()throws Exception{
        this.mockMvc.perform(get("/admin/listcarduser").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='title_admin']").string("List of all card user"));
    }

    @Test
    public void AdminPanelListContactUsTest_LoadPage_True()throws Exception{
        this.mockMvc.perform(get("/admin/listcontactus").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='item']")
                        .nodeCount(0));
    }

    @Test
    public void AdminPanelListContactUsTest_DeleteContactUs_True()throws Exception{
        this.mockMvc.perform(get("/admin/listcontactus/delete/3").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listcontactus"));
    }

    @Test
    public void AdminPanelListCheckoutOrderTest_LoadPage_True()throws Exception{
        this.mockMvc.perform(get("/admin/listcheckoutorder").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='title_admin']").string("List of all checkout order"));
    }

    @Test
    public void AdminPanelListCheckoutOrderTest_CountNode_0() throws Exception {
            this.mockMvc.perform(get("/admin/listcheckoutorder").with(user("adm").password("123")
                    .authorities(new SimpleGrantedAuthority("ADMIN"))))
                    .andExpect(authenticated())
                    .andExpect(status().isOk())
                    .andExpect(xpath("//*[@id='item']")
                            .nodeCount(0));

    }

    @Test
    public void AdminPanelListCheckoutOrderTest_DeleteCheckoutOrder_True()throws Exception{
        this.mockMvc.perform(get("/admin/listcheckoutorder/delete/6").with(user("adm").password("123")
                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/listcheckoutorder"));
    }

}
