package nba.mapper;

import org.springframework.stereotype.Service;

import nba.dao.model.PlayEntity;
import nba.model.Play;

@Service
public class PlayMapper {

    public Play entitytoDto(PlayEntity playEnt) {

        Play play = new Play();
        play.setId(playEnt.getId());
        play.setEvent(playEnt.getEvent());
        play.setClock(playEnt.getClock());

        play.setDescription(playEnt.getDescription());
        play.setPlayerCode(playEnt.getPlayerCode());
        play.setPersonId(playEnt.getPersonId());
        play.setHomeScore(playEnt.getHomeScore());
        play.setVisitorScore(playEnt.getVisitorScore());
        play.setTeamAbr(playEnt.getTeamAbr());
        play.setPeriod(playEnt.getPeriod());
        return play;
    }

}
