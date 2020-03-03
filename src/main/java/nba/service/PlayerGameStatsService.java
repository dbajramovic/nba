package nba.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nba.dao.model.BoxscoreEntity;
import nba.dao.model.GameEntity;
import nba.dao.model.PlayerEntity;
import nba.dao.model.PlayerGameStatsEntity;
import nba.dao.repos.BoxscoreDAO;
import nba.dao.repos.GameDAO;
import nba.dao.repos.PlayerDAO;
import nba.dao.repos.PlayerGameStatsDAO;
import nba.helpers.MappingChecker;
import nba.mapper.PlayerGameStatsMapper;
import nba.model.PlayerGameStats;
import nba.model.PlayerGameStatsAdjusted;
import nba.model.PlayerGameStatsTimeline;
import nba.model.PlayerGameStatsTimelineSpecific;

@Component
public class PlayerGameStatsService {

    @Autowired
    PlayerGameStatsDAO pgsDAO;

    @Autowired
    PlayerDAO playerDAO;

    @Autowired
    BoxscoreDAO boxscoreDAO;

    @Autowired
    GameDAO gameDAO;

    @Autowired
    PlayerGameStatsMapper playerGameStatsMapper;

    public PlayerGameStats mapPGS(Map<String, Object> map) {
        PlayerGameStats pgs = new PlayerGameStats();
        if (map.get("personId") != null) {
            pgs.setPersonId((String) map.get("personId"));
        }
        if (map.get("teamId") != null) {
            pgs.setTeamId((String) map.get("teamId"));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("points"))) {
            pgs.setPoints(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("points"))));
        }
        if (map.get("pos") != null) {
            pgs.setPos((String) map.get("pos"));
        }
        if (map.get("min") != null) {
            pgs.setMin((String) map.get("min"));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("fgm"))) {
            pgs.setFgm(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("fgm"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("fga"))) {
            pgs.setFga(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("fga"))));
        }
        if (MappingChecker.canBeParsedIntoBigDecimal((String) map.get("fgp"))) {
            pgs.setFgp(new BigDecimal(MappingChecker.returnCleanValue(((String) map.get("fgp")))));
        }
        if (MappingChecker.canBeParsedIntoBigDecimal((String) map.get("tpp"))) {
            pgs.setTpp(new BigDecimal(MappingChecker.returnCleanValue((String) map.get("tpp"))));
        }
        if (MappingChecker.canBeParsedIntoBigDecimal((String) map.get("ftp"))) {
            pgs.setFtp(new BigDecimal(MappingChecker.returnCleanValue((String) map.get("ftp"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("ftm"))) {
            pgs.setFtm(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("ftm"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("fta"))) {
            pgs.setFta(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("fta"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("tpm"))) {
            pgs.setTpm(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("tpm"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("tpa"))) {
            pgs.setTpa(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("tpa"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("offReb"))) {
            pgs.setOffReb(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("offReb"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("defReb"))) {
            pgs.setDefReb(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("defReb"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("assists"))) {
            pgs.setAssists(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("assists"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("pFouls"))) {
            pgs.setpFouls(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("pFouls"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("totReb"))) {
            pgs.setTotReb(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("totReb"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("steals"))) {
            pgs.setSteals(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("steals"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("blocks"))) {
            pgs.setBlocks(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("blocks"))));
        }
        if (MappingChecker.canBeParsedIntoLong((String) map.get("turnovers"))) {
            pgs.setTurnovers(Long.parseLong(MappingChecker.returnCleanValue((String) map.get("turnovers"))));
        }
        if (map.get("plusMinus") != null) {
            pgs.setPlusMinus((String) map.get("plusMinus"));
        }
        if (map.get("dnp") != null) {
            pgs.setDnp((String) map.get("dnp"));
        }
        return pgs;
    }

    public PlayerGameStatsTimeline getTimeline(String name, String surname, LocalDateTime start, LocalDateTime end) throws Exception {
        PlayerGameStatsTimeline timeline = new PlayerGameStatsTimeline();
        List<PlayerGameStats> stats = new ArrayList<>();
        List<PlayerEntity> playerIdsForPeriod = playerDAO.findByNameAndSurname(name, surname);
        List<String> personIds = playerIdsForPeriod.stream().filter(isInTimeFrame(start, end)).map(PlayerEntity::getPersonId).distinct()
                .collect(Collectors.toList());
        if (personIds.isEmpty()) {
            throw new Exception(String.format("Player %s %s does not exist. Please check name and surname!", name, surname));
        }
        List<PlayerGameStatsEntity> pgsForTimeAndPlayer = pgsDAO.getPGSForTimeAndPlayer(personIds, createLong(start), createLong(end));

        for (PlayerGameStatsEntity ent : pgsForTimeAndPlayer) {
            stats.add(playerGameStatsMapper.entityToDto(ent));
        }
        timeline.setStart(start);
        timeline.setEnd(end);
        timeline.setStats(stats);
        timeline.setCumulativeStats(createCumulativeStat(stats));
        timeline.setCumulativeStatsAdjusted(createCumulativeAdjustedStats(stats, new BigDecimal(36L)));
        setMatchDescriptions(stats);
        return timeline;
    }

    public void setMatchDescriptions(List<PlayerGameStats> stats) {
        Set<Long> boxscoreIds = stats.stream().map(PlayerGameStats::getBoxscoreId).collect(Collectors.toSet());
        Map<Long, String> boxscoreGameMap = boxscoreDAO.findByIds(boxscoreIds).stream()
                .collect(Collectors.toMap(BoxscoreEntity::getId, BoxscoreEntity::getGameId));
        Set<String> gameIds = boxscoreDAO.findByIds(boxscoreIds).stream().map(BoxscoreEntity::getGameId).collect(Collectors.toSet());
        List<GameEntity> games = gameDAO.findByGameIds(gameIds);
        for (PlayerGameStats stat : stats) {
            Optional<GameEntity> game = games.stream().filter(isGameId(boxscoreGameMap.get(stat.getBoxscoreId()))).findFirst();
            if (game.isPresent()) {
                StringBuilder sb = new StringBuilder();
                sb.append(game.get().getDate().substring(0, 4));
                sb.append("/");
                sb.append(game.get().getDate().substring(4, 6));
                sb.append("/");
                sb.append(game.get().getDate().substring(6, 8));
                sb.append(" - ");
                sb.append(game.get().getArena());
                sb.append(" ");
                sb.append(game.get().getCity());
                sb.append(" - ");
                sb.append(game.get().getGameUrl().split("/")[1].substring(0, 3));
                sb.append("@");
                sb.append(game.get().getGameUrl().split("/")[1].substring(3, 6));
                stat.setMatchDescription(sb.toString());
            }
        }
        Collections.sort(stats, (s1, s2) -> compareStats(s1, s2));
    }

    private int compareStats(PlayerGameStats s1, PlayerGameStats s2) {
        return s1.getMatchDescription().compareTo(s2.getMatchDescription());
    }

    private Predicate<? super GameEntity> isGameId(String gameId) {
        return z -> z.getGameId().equalsIgnoreCase(gameId);
    }

    private Long createLong(LocalDateTime start) {
        return start.getDayOfYear() + start.getMonth().ordinal() * 100L + start.getYear() * 10000L;
    }

    private PlayerGameStatsAdjusted createCumulativeAdjustedStats(List<PlayerGameStats> stats, BigDecimal minutes) {
        PlayerGameStatsAdjusted cumulativeStats = new PlayerGameStatsAdjusted();
        BigDecimal assist = BigDecimal.ZERO;
        BigDecimal block = BigDecimal.ZERO;
        BigDecimal defReb = BigDecimal.ZERO;
        BigDecimal fga = BigDecimal.ZERO;
        BigDecimal fgm = BigDecimal.ZERO;
        BigDecimal fta = BigDecimal.ZERO;
        BigDecimal ftm = BigDecimal.ZERO;
        BigDecimal offreb = BigDecimal.ZERO;
        BigDecimal foul = BigDecimal.ZERO;
        BigDecimal point = BigDecimal.ZERO;
        BigDecimal steal = BigDecimal.ZERO;
        BigDecimal totReb = BigDecimal.ZERO;
        BigDecimal tpa = BigDecimal.ZERO;
        BigDecimal tpm = BigDecimal.ZERO;
        BigDecimal turnover = BigDecimal.ZERO;
        BigDecimal fgp = BigDecimal.ZERO;
        BigDecimal ftp = BigDecimal.ZERO;
        BigDecimal tpp = BigDecimal.ZERO;
        BigDecimal plusMinus = BigDecimal.ZERO;
        int dnp = 0;
        Long sumOfMinutes = 0L;
        for (PlayerGameStats stat : stats) {
            BigDecimal coeficient = BigDecimal.ZERO;
            if (stat.getMin() != null && !stat.getMin().equalsIgnoreCase("")) {
                Long minutesForGame = Long.parseLong(stat.getMin().split(":")[0]) + Long.parseLong(stat.getMin().split(":")[1]) / 60L;
                sumOfMinutes += minutesForGame;
                if (minutesForGame != 0L) {
                    coeficient = minutes.divide(new BigDecimal(minutesForGame), 4, RoundingMode.FLOOR);
                }
            } else {
                dnp++;
            }
            if (stat.getPlusMinus() != null && !"".equalsIgnoreCase(stat.getPlusMinus())) {
                Long plusMinusLong = 0L;
                if (stat.getPlusMinus().contains("-")) {
                    plusMinusLong += Long.parseLong(stat.getPlusMinus().split("-")[1]) * -1;
                } else {
                    plusMinusLong += Long.parseLong(stat.getPlusMinus().split("-")[0]);
                }
                plusMinus = plusMinus.add(new BigDecimal(plusMinusLong));
            }
            if (stat.getAssists() != null) {
                assist = assist.add(coeficient.multiply(new BigDecimal(stat.getAssists())));
            }
            if (stat.getBlocks() != null) {
                block = block.add(coeficient.multiply(new BigDecimal(stat.getBlocks())));
            }
            if (stat.getPoints() != null) {
                point = point.add(coeficient.multiply(new BigDecimal(stat.getPoints())));
            }
            if (stat.getDefReb() != null) {
                defReb = defReb.add(coeficient.multiply(new BigDecimal(stat.getDefReb())));
            }
            if (stat.getFga() != null) {
                fga = fga.add(coeficient.multiply(new BigDecimal(stat.getFga())));
            }
            if (stat.getFgm() != null) {
                fgm = fgm.add(coeficient.multiply(new BigDecimal(stat.getFgm())));
            }
            if (stat.getFta() != null) {
                fta = fta.add(coeficient.multiply(new BigDecimal(stat.getFta())));
            }
            if (stat.getFtm() != null) {
                ftm = ftm.add(coeficient.multiply(new BigDecimal(stat.getFtm())));
            }
            if (stat.getOffReb() != null) {
                offreb = offreb.add(coeficient.multiply(new BigDecimal(stat.getOffReb())));
            }
            if (stat.getpFouls() != null) {
                foul = foul.add(coeficient.multiply(new BigDecimal(stat.getpFouls())));
            }
            if (stat.getSteals() != null) {
                steal = steal.add(coeficient.multiply(new BigDecimal(stat.getSteals())));
            }
            if (stat.getTotReb() != null) {
                totReb = totReb.add(coeficient.multiply(new BigDecimal(stat.getTotReb())));
            }
            if (stat.getTpa() != null) {
                tpa = tpa.add(coeficient.multiply(new BigDecimal(stat.getTpa())));
            }
            if (stat.getTpm() != null) {
                tpm = tpm.add(coeficient.multiply(new BigDecimal(stat.getTpm())));
            }
            if (stat.getTurnovers() != null) {
                turnover = turnover.add(coeficient.multiply(new BigDecimal(stat.getTurnovers())));
            }
        }
        if (!fga.equals(BigDecimal.ZERO)) {
            fgp = fgm.divide(fga, 4, RoundingMode.FLOOR).multiply(new BigDecimal(100));
        }
        if (!fta.equals(BigDecimal.ZERO)) {
            ftp = ftm.divide(fta, 4, RoundingMode.FLOOR).multiply(new BigDecimal(100));
        }
        if (!tpa.equals(BigDecimal.ZERO)) {
            tpp = tpm.divide(tpa, 4, RoundingMode.FLOOR).multiply(new BigDecimal(100));
        }
        cumulativeStats.setPersonId("");
        cumulativeStats.setTeamId("");
        cumulativeStats.setAssists(assist.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setBlocks(block.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setDefReb(defReb.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setDnp(dnp + "");
        cumulativeStats.setFga(fga.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setFgm(fgm.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setFgp(fgp);
        cumulativeStats.setFta(fta.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setFtm(ftm.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setFtp(ftp);
        cumulativeStats.setIsOnCourt("");
        cumulativeStats.setMin((sumOfMinutes / (stats.size() - dnp)) + "");
        cumulativeStats.setOffReb(offreb.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setPersonId("");
        cumulativeStats.setpFouls(foul.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setPlusMinus("");
        cumulativeStats.setPoints(point.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setPos("");
        cumulativeStats.setSteals(steal.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setTotReb(totReb.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setTpa(tpa.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setTpm(tpm.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setTpp(tpp);
        cumulativeStats.setTurnovers(turnover.divide(new BigDecimal(stats.size() - dnp), 4, RoundingMode.FLOOR));
        cumulativeStats.setPlusMinus(plusMinus.toString());
        return cumulativeStats;
    }

    private PlayerGameStats createCumulativeStat(List<PlayerGameStats> stats) {
        PlayerGameStats cumulativeStats = new PlayerGameStats();
        Long assist = 0L;
        Long block = 0L;
        Long defReb = 0L;
        Long fga = 0L;
        Long fgm = 0L;
        Long fta = 0L;
        Long ftm = 0L;
        Long offreb = 0L;
        Long foul = 0L;
        Long point = 0L;
        Long steal = 0L;
        Long totReb = 0L;
        Long tpa = 0L;
        Long tpm = 0L;
        Long turnover = 0L;
        for (PlayerGameStats stat : stats) {
            assist += stat.getAssists();
            block += stat.getBlocks();
            defReb += stat.getDefReb();
            if (stat.getFga() != null) {
                fga += stat.getFga();
            }
            if (stat.getFgm() != null) {
                fgm += stat.getFgm();
            }
            if (stat.getFta() != null) {
                fta += stat.getFta();
            }
            if (stat.getFtm() != null) {
                ftm += stat.getFtm();
            }
            if (stat.getOffReb() != null) {
                offreb += stat.getOffReb();
            }
            if (stat.getpFouls() != null) {
                foul += stat.getpFouls();
            }
            if (stat.getPoints() != null) {
                point += stat.getPoints();
            }
            if (stat.getSteals() != null) {
                steal += stat.getSteals();
            }
            if (stat.getTotReb() != null) {
                totReb += stat.getTotReb();
            }
            if (stat.getTpa() != null) {
                tpa += stat.getTpa();
            }
            if (stat.getTpm() != null) {
                tpm += stat.getTpm();
            }
            if (stat.getTurnovers() != null) {
                turnover += stat.getTurnovers();
            }
        }
        cumulativeStats.setPersonId("");
        cumulativeStats.setTeamId("");
        cumulativeStats.setAssists(assist);
        cumulativeStats.setBlocks(block);
        cumulativeStats.setDefReb(defReb);
        cumulativeStats.setDnp("");
        cumulativeStats.setFga(fga);
        cumulativeStats.setFgm(fgm);
        cumulativeStats.setFgp(BigDecimal.valueOf(((float) fgm / fga) * 100L).round(new MathContext(4, RoundingMode.CEILING)));
        cumulativeStats.setFta(fta);
        cumulativeStats.setFtm(ftm);
        cumulativeStats.setFtp(BigDecimal.valueOf(((float) ftm / fta) * 100L).round(new MathContext(4, RoundingMode.CEILING)));
        cumulativeStats.setIsOnCourt("");
        cumulativeStats.setMin("");
        cumulativeStats.setOffReb(offreb);
        cumulativeStats.setPersonId("");
        cumulativeStats.setpFouls(foul);
        cumulativeStats.setPlusMinus("");
        cumulativeStats.setPoints(point);
        cumulativeStats.setPos("");
        cumulativeStats.setSteals(steal);
        cumulativeStats.setTotReb(totReb);
        cumulativeStats.setTpa(tpa);
        cumulativeStats.setTpm(tpm);
        cumulativeStats.setTpp(BigDecimal.valueOf(((float) tpm / tpa) * 100L).round(new MathContext(4, RoundingMode.CEILING)));
        cumulativeStats.setTurnovers(turnover);
        return cumulativeStats;
    }

    private Predicate<? super PlayerEntity> isInTimeFrame(LocalDateTime start, LocalDateTime end) {
        return z -> {
            Long startYear = start.getYear() + 0L;
            Long endYear = end.getYear() + 0L;
            Long year = Long.parseLong(z.getYear());
            return year.equals(startYear) || year.equals(endYear) || (year < endYear && year > startYear);
        };
    }

    public PlayerGameStatsTimelineSpecific getTimeLineSpecific(String name, String surname, LocalDateTime startDate, LocalDateTime endDate,
            List<String> selectedStats) throws Exception {
        PlayerGameStatsTimelineSpecific timeline = new PlayerGameStatsTimelineSpecific();
        List<PlayerEntity> playerIdsForPeriod = playerDAO.findByNameAndSurname(name, surname);
        List<String> personIds = playerIdsForPeriod.stream().filter(isInTimeFrame(startDate, endDate)).map(PlayerEntity::getPersonId)
                .distinct().collect(Collectors.toList());
        if (personIds.isEmpty()) {
            throw new Exception(String.format("Player %s %s does not exist. Please check name and surname!", name, surname));
        }
        List<PlayerGameStatsEntity> pgsForTimeAndPlayer = pgsDAO.getPGSForTimeAndPlayer(personIds, createLong(startDate),
                createLong(endDate));
        Map<String, List<Long>> map = new HashMap<>();
        Field[] attributes = PlayerGameStatsEntity.class.getDeclaredFields();
        for (Field field : attributes) {
            if (selectedStats.contains(field.getName())) {
                field.setAccessible(true);
                map.put(field.getName(), new ArrayList<>());
            }
        }
        for (PlayerGameStatsEntity ent : pgsForTimeAndPlayer) {
            for (Field field : attributes) {
                field.setAccessible(true);
                if (map.get(field.getName()) != null) {
                    map.get(field.getName()).add((Long) field.get(ent));
                }
            }
        }
        timeline.setMapOfStats(map);
        return timeline;
    }
}
