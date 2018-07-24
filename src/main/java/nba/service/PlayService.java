package nba.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nba.dao.model.GameEntity;
import nba.dao.model.PlayEntity;
import nba.dao.repos.GameDAO;
import nba.dao.repos.PlayDAO;
import nba.mapper.GameMapper;
import nba.mapper.PlayMapper;
import nba.model.Game;

@Component
public class PlayService {

    @Autowired
    GameDAO gameDAO;

    @Autowired
    PlayDAO playDAO;

    @Autowired
    PlayMapper playMapper;

    @Autowired
    GameMapper gameMapper;

    @Transactional(rollbackFor = Exception.class)
    public Game savePlays(List<LinkedHashMap<String, Object>> plays, String gameId, String date) {
        GameEntity game = gameDAO.findGameForGameIdAndDate(gameId, date);
        Game gameModel = gameMapper.entitytoDto(game, false);
        gameModel.setPlays(new ArrayList<>());
        List<PlayEntity> playEnts = new ArrayList<>();
        for (LinkedHashMap<String, Object> t : plays) {
            PlayEntity play = new PlayEntity();
            if (t.get("event") != null) {
                play.setEvent((String) t.get("event"));
            }
            if (t.get("clock") != null) {
                play.setClock((String) t.get("clock"));
            }
            if (t.get("description") != null) {
                play.setDescription((String) t.get("description"));
            }
            if (t.get("player_code") != null) {
                play.setPlayerCode(WordUtils.capitalize(((String) t.get("player_code"))));
            }
            if (t.get("person_id") != null) {
                play.setPersonId((String) t.get("person_id"));
            }
            if (t.get("home_score") != null) {
                play.setHomeScore((String) t.get("home_score"));
            }
            if (t.get("visitor_score") != null) {
                play.setVisitorScore((String) t.get("visitor_score"));
            }
            if (t.get("team_abr") != null) {
                play.setTeamAbr((String) t.get("team_abr"));
            }
            if (t.get("period") != null) {
                play.setPeriod((String) t.get("period"));
            }
            play.setGame(game);
            playEnts.add(play);
            gameModel.getPlays().add(playMapper.entitytoDto(play));
        }
        playDAO.saveAll(playEnts);
        return gameModel;
    }

}
