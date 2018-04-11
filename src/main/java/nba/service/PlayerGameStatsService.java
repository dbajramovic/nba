package nba.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

import nba.helpers.MappingChecker;
import nba.model.PlayerGameStats;

@Component
public class PlayerGameStatsService {

    public PlayerGameStats mapPGS(Map<String, Object> map) {
        PlayerGameStats pgs = new PlayerGameStats();
        if (map.get("personId") != null) {
            pgs.setPersonId((String) map.get("personId"));
        }
        if (map.get("teamId") != null) {
            pgs.setTeamId((String) map.get("teamId"));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("points"))) {
            pgs.setPoints(Long.parseLong((String) map.get("points")));
        }
        if (map.get("pos") != null) {
            pgs.setPos((String) map.get("pos"));
        }
        if (map.get("min") != null) {
            pgs.setMin((String) map.get("min"));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("fgm"))) {
            pgs.setFgm(Long.parseLong((String) map.get("fgm")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("fga"))) {
            pgs.setFga(Long.parseLong((String) map.get("fga")));
        }
        if (MappingChecker.canBeParsedIntoBigDecimal((String) map.get("fgp"))) {
            pgs.setFgp(new BigDecimal((String) map.get("fgp")));
        }
        if (MappingChecker.canBeParsedIntoBigDecimal((String) map.get("tpp"))) {
            pgs.setTpp(new BigDecimal((String) map.get("tpp")));
        }
        if (MappingChecker.canBeParsedIntoBigDecimal((String) map.get("ftp"))) {
            pgs.setFtp(new BigDecimal((String) map.get("ftp")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("ftm"))) {
            pgs.setFtm(Long.parseLong((String) map.get("ftm")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("fta"))) {
            pgs.setFta(Long.parseLong((String) map.get("fta")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("tpm"))) {
            pgs.setTpm(Long.parseLong((String) map.get("tpm")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("tpa"))) {
            pgs.setTpa(Long.parseLong((String) map.get("tpa")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("offReb"))) {
            pgs.setOffReb(Long.parseLong((String) map.get("offReb")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("defReb"))) {
            pgs.setDefReb(Long.parseLong((String) map.get("defReb")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("assists"))) {
            pgs.setAssists(Long.parseLong((String) map.get("assists")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("pFouls"))) {
            pgs.setpFouls(Long.parseLong((String) map.get("pFouls")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("totReb"))) {
            pgs.setTotReb(Long.parseLong((String) map.get("totReb")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("steals"))) {
            pgs.setSteals(Long.parseLong((String) map.get("steals")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("blocks"))) {
            pgs.setBlocks(Long.parseLong((String) map.get("blocks")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("turnovers"))) {
            pgs.setTurnovers(Long.parseLong((String) map.get("turnovers")));
        }
        if (map.get("plusMinus") != null) {
            pgs.setPlusMinus((String) map.get("plusMinus"));
        }
        if (map.get("dnp") != null) {
            pgs.setDnp((String) map.get("dnp"));
        }
        return pgs;
    }

}
