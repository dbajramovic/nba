package nba.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import nba.dao.model.PlayerGameStatsEntity;
import nba.model.PlayerGameStats;

@Service
public class PlayerGameStatsMapper {
    public PlayerGameStats entityToDto(PlayerGameStatsEntity ent) {
        PlayerGameStats pgs = new PlayerGameStats();
        if (ent.getAssists() != null) {
            pgs.setAssists(ent.getAssists());
        } else {
            pgs.setAssists(0L);
        }
        if (ent.getBlocks() != null) {
            pgs.setBlocks(ent.getBlocks());
        } else {
            pgs.setBlocks(0L);
        }
        if (ent.getDefReb() != null) {
            pgs.setDefReb(ent.getDefReb());
        } else {
            pgs.setDefReb(0L);
        }
        if (ent.getDnp() != null) {
            pgs.setDnp(ent.getDnp());
        }
        if (ent.getFga() != null) {
            pgs.setFga(ent.getFga());
        } else {
            pgs.setFga(0L);
        }

        if (ent.getFgm() != null) {
            pgs.setFgm(ent.getFgm());
        } else {
            pgs.setFgm(0L);
        }
        pgs.setFgp(ent.getFgp());
        pgs.setFta(ent.getFta());
        pgs.setFtm(ent.getFtm());
        pgs.setFtp(ent.getFtp());
        pgs.setIsOnCourt(ent.getIsOnCourt());
        pgs.setMin(ent.getMin());
        pgs.setOffReb(ent.getOffReb());
        pgs.setPersonId(ent.getPersonId());
        pgs.setpFouls(ent.getPFouls());
        pgs.setPlusMinus(ent.getPlusMinus());
        pgs.setPoints(ent.getPoints());
        pgs.setPos(ent.getPos());
        pgs.setSteals(ent.getSteals());
        pgs.setTeamId(ent.getTeamId());
        pgs.setTotReb(ent.getTotReb());
        pgs.setTpa(ent.getTpa());
        pgs.setTpm(ent.getTpm());
        pgs.setTpp(ent.getTpp());
        pgs.setTurnovers(ent.getTurnovers());
        if (ent.getBoxscore() != null) {
            pgs.setBoxscoreId(ent.getBoxscore().getId());
        }
        return pgs;
    }

    public PlayerGameStatsEntity dtoToEntity(PlayerGameStats ent) {
        PlayerGameStatsEntity pgs = new PlayerGameStatsEntity();
        pgs.setAssists(ent.getAssists());
        pgs.setBlocks(ent.getBlocks());
        pgs.setDefReb(ent.getDefReb());
        pgs.setDnp(ent.getDnp());
        pgs.setFga(ent.getFga());
        pgs.setFgm(ent.getFgm());
        pgs.setFgp(ent.getFgp());
        pgs.setFta(ent.getFta());
        pgs.setFtm(ent.getFtm());
        pgs.setFtp(ent.getFtp());
        pgs.setIsOnCourt(ent.getIsOnCourt());
        pgs.setMin(ent.getMin());
        pgs.setOffReb(ent.getOffReb());
        pgs.setPersonId(ent.getPersonId());
        pgs.setPFouls(ent.getpFouls());
        pgs.setPlusMinus(ent.getPlusMinus());
        pgs.setPoints(ent.getPoints());
        pgs.setPos(ent.getPos());
        pgs.setSteals(ent.getSteals());
        pgs.setTeamId(ent.getTeamId());
        pgs.setTotReb(ent.getTotReb());
        pgs.setTpa(ent.getTpa());
        pgs.setTpm(ent.getTpm());
        pgs.setTpp(ent.getTpp());
        pgs.setTurnovers(ent.getTurnovers());
        return pgs;
    }

    public List<PlayerGameStats> entitiesToDtos(List<PlayerGameStatsEntity> ents) {
        List<PlayerGameStats> players = new ArrayList<>();
        for (PlayerGameStatsEntity ent : ents) {
            players.add(entityToDto(ent));
        }
        return players;
    }
}
