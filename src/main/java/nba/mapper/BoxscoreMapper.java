package nba.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nba.dao.model.BoxscoreEntity;
import nba.model.Boxscore;

public class BoxscoreMapper {

    @Autowired
    TeamBoxscoreStatMapper teamBoxscoreStatMapper;

    public Boxscore entitytoDto(BoxscoreEntity boxEnt) {
        Boxscore box = new Boxscore();
        box.setTimesTied(boxEnt.getLeadChanges());
        box.setTimesTied(boxEnt.getLeadChanges());
        box.sethTeam(teamBoxscoreStatMapper.entitytoDto(boxEnt.gethTeam()));
        box.setvTeam(teamBoxscoreStatMapper.entitytoDto(boxEnt.getvTeam()));
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
