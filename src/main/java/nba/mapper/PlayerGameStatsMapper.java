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
        pgs.setpFouls(ent.getpFouls());
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
        pgs.setpFouls(ent.getpFouls());
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
