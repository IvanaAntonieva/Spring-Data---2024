package org.modelmapperexercise.service;

import org.modelmapperexercise.service.dtos.AllGamesDTO;
import org.modelmapperexercise.service.dtos.GameAddDTO;

import java.util.Map;
import java.util.Set;

public interface GameService {
    String addGame(GameAddDTO gameAddDTo);

    String editGame(long id, Map<String, String> map);

    String deleteGame(long id);

    Set<AllGamesDTO> getAllGames();

    String allGamesReadyToPrint();
}
