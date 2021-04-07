package com.istg.service.implementations;

import com.istg.config.AppConfig;
import com.istg.dao.Interface.TableGamesDao;
import com.istg.domain.TableGames;
import com.istg.domain.enums.TypeGame;
import com.istg.service.interfaces.TableGameService;
import junit.framework.TestCase;
import org.junit.Assert;
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
public class TableGameSericeImplTest {

    @Autowired
    private TableGameService tableGameService;

    @MockBean
    private TableGamesDao tableGamesDao;

    @Test
    public void SaveGameTest_SaveGame_WithFullParam_True() {
        when(tableGamesDao.save(new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type()))).thenReturn(1);
        Assert.assertEquals(1, tableGameService.save(new TableGames("Manchkinks", "Adventure games", "None", 123, TypeGame.ADVENTURE.getId_type())));
        verify(tableGamesDao,times(1)).save(new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type()));
    }

    @Test
    public void GetAllGameTest_GetAll_True() {
        when(tableGamesDao.getAll()).thenReturn(Stream.of(new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type()),new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type()),new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type())).collect(Collectors.toList()));
        Assert.assertEquals(3, tableGameService.getAll().size());
        verify(tableGamesDao,times(1)).getAll();
    }

    @Test
    public void EditGameTest_EditGame_WithFullParam_True() {
        TableGames tempgame = new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type());
        tempgame.setId_game(1L);
        when(tableGamesDao.update(tempgame)).thenReturn(1);
        Assert.assertEquals(0, tableGameService.update(new TableGames("Manchkinks", "Adventure games", "None", 123, TypeGame.ADVENTURE.getId_type())));
        verify(tableGamesDao,times(0)).update(new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type()));
    }

    @Test
    public void DeleteGameTest_DeleteGame_WithFullParam_True() {
        TableGames tempgame = new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type());
        tempgame.setId_game(1L);
        when(tableGamesDao.delete(tempgame)).thenReturn(1);
        Assert.assertEquals(0, tableGameService.delete(new TableGames("Manchkinks", "Adventure games", "None", 123, TypeGame.ADVENTURE.getId_type())));
        verify(tableGamesDao,times(0)).delete(new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type()));
    }

    @Test
    public void GetGameByIdTest_GetOneGame_WithId1_True() {
        when(tableGamesDao.getById(1L)).thenReturn(new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type()));
        Assert.assertEquals("Manchkinks", tableGameService.getById(1L).getName_game());
        verify(tableGamesDao,times(1)).getById(1L);
    }

    @Test
    public void FindGameByNameTest_FindByName_WithNama_True(){
        when(tableGamesDao.findByName("Man")).thenReturn(Stream.of(new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type())).collect(Collectors.toList()));
        Assert.assertEquals(1, tableGameService.findByName("Man").size());
        verify(tableGamesDao,times(1)).findByName("Man");
    }

    @Test
    public void FindGameByTypeTest_FindByType_WithType_True(){
        when(tableGamesDao.findByType(TypeGame.ADVENTURE)).thenReturn(Stream.of(new TableGames("Manchkinks","Adventure games","None",123, TypeGame.ADVENTURE.getId_type())).collect(Collectors.toList()));
        Assert.assertEquals(1, tableGameService.findByType(TypeGame.ADVENTURE).size());
        verify(tableGamesDao,times(1)).findByType(TypeGame.ADVENTURE);
    }

}