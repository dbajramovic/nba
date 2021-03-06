package nba.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nba.dao.model.BoxscoreEntity;
import nba.model.Boxscore;

@Service
public class BoxscoreMapper {

    @Autowired
    TeamBoxscoreStatMapper teamBoxscoreStatMapper;

    public Boxscore entitytoDto(BoxscoreEntity boxEnt) {
        Boxscore box = new Boxscore();
        box.setTimesTied(boxEnt.getLeadChanges());
        box.setLeadChanges(boxEnt.getLeadChanges());
        box.sethTeam(teamBoxscoreStatMapper.entitytoDto(boxEnt.getHTeam()));
        box.setvTeam(teamBoxscoreStatMapper.entitytoDto(boxEnt.getVTeam()));
        box.setYear(boxEnt.getYear());
        box.setGameId(boxEnt.getGameId());
        return box;
    }

    public BoxscoreEntity dtoToEntity(Boxscore boxEnt) {
        BoxscoreEntity box = new BoxscoreEntity();
        box.setTimesTied(boxEnt.getLeadChanges());
        box.setLeadChanges(boxEnt.getLeadChanges());
        box.setYear(boxEnt.getYear());
        box.setGameId(boxEnt.getGameId());
        return box;
    }

    public List<Boxscore> entitesToDtos(List<BoxscoreEntity> ents) {
        List<Boxscore> boxs = new ArrayList<>();
        for (BoxscoreEntity ent : ents) {
            boxs.add(entitytoDto(ent));
        }
        return boxs;
    }

}
