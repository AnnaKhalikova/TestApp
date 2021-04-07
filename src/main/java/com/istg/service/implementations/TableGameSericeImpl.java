package com.istg.service.implementations;

import com.istg.dao.Interface.TableGamesDao;
import com.istg.domain.TableGames;
import com.istg.domain.enums.TypeGame;
import com.istg.service.interfaces.TableGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class TableGameSericeImpl implements TableGameService {

    private static final Logger log = Logger.getLogger(String.valueOf(TableGameSericeImpl.class));

    @Value("${upload_image.path}")
    private String uploadPath;

    @Autowired
    private TableGamesDao tableGamesDao;

    @Override
    public List<TableGames> findByName(String nameGames) {
        if(nameGames!=null) {
            log.info("Find game by name");
            return tableGamesDao.findByName(nameGames);
        }else{
            log.info("Don't find game by name");
            return null;
        }
    }

    @Override
    public List<TableGames> findByType(TypeGame typeGame) {
        if(typeGame!=null) {
            log.info("Find game by type");
            return tableGamesDao.findByType(typeGame);
        }else{
            log.info("Don't find game by type");
            return null;
        }
    }

    @Override
    public void saveFile( TableGames tableGames,MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();//Уникальное имя файла
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            tableGames.setFilename_photo_game(resultFileName);
        }
    }

    @Override
    public int save(TableGames tableGames) {
        if(!tableGames.getName_game().equals("")) {
            log.info("Save new game");
            return tableGamesDao.save(tableGames);
        }else{
            log.info("Not save new game");
            return 0;
        }
    }

    @Override
    public List<TableGames> getAll() {
        log.info("Get all game");
        return tableGamesDao.getAll();
    }

    @Override
    public int update(TableGames tableGames) {
        if(tableGames.getId_game()!=null) {
            log.info("Update game - " + tableGames.toString());
            return tableGamesDao.update(tableGames);
        }else{
            log.info("Not update game");
            return 0;
        }
    }

    @Override
    public int delete(TableGames tableGames) {
        if(tableGames.getId_game()!=null) {
            log.info("Delete game - " + tableGames.toString());
            return tableGamesDao.delete(tableGames);
        }else{
            log.info("Not delete game");
            return 0;
        }
    }

    @Override
    public TableGames getById(Long id) {
        if(id!=null) {
            log.info("Get table by id - " + id);
            return tableGamesDao.getById(id);
        }else{
            log.info("Don't get table by id");
            return null;
        }

    }
}
