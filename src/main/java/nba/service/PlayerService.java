package nba.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nba.dao.model.BoxscoreEntity;
import nba.dao.model.PlayerEntity;
import nba.dao.model.PlayerGameStatsEntity;
import nba.dao.model.PlayerTeamEntity;
import nba.dao.model.TeamEntity;
import nba.dao.repos.BoxscoreDAO;
import nba.dao.repos.PlayerDAO;
import nba.dao.repos.PlayerGameStatsDAO;
import nba.dao.repos.PlayerTeamDAO;
import nba.dao.repos.TeamDAO;
import nba.helpers.MappingChecker;
import nba.mapper.PlayerGameStatsMapper;
import nba.mapper.PlayerMapper;
import nba.mapper.TeamMapper;
import nba.model.Player;
import nba.model.PlayerGameHistory;
import nba.model.PlayerGameStats;
import nba.model.Team;

@Component
public class PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private PlayerTeamDAO playerTeamDAO;

    @Autowired
    private PlayerGameStatsDAO playerGameStatsCustom;

    @Autowired
    private BoxscoreDAO boxscoreDAO;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private PlayerGameStatsMapper playerGameStatsMapper;

    public List<Team> teamsOfPlayer(String name, String surname) {
        Map<String, TeamEntity> map = new HashMap<>();
        List<TeamEntity> teams = new ArrayList<>();
        List<PlayerEntity> players = playerDAO.findByNameAndSurname(name, surname);
        List<String> refIds = players.parallelStream().map(PlayerEntity::getPersonId).distinct().collect(Collectors.toList());
        for (String refId : refIds) {
            teams.addAll(teamDAO.findTeamsOfPlayer(refId));
        }
        for (TeamEntity team : teams) {
            map.put(team.getTeamId(), team);
        }
        teams.clear();
        for (Map.Entry<String, TeamEntity> entry : map.entrySet()) {
            teams.add(entry.getValue());
        }
        return teamMapper.entitiesToDtos(teams);
    }

    @Transactional(rollbackOn = Exception.class)
    public List<Player> savePlayers(ArrayList<LinkedHashMap<String, Object>> players, String year) {
        List<Player> playerList = new ArrayList<>();
        for (LinkedHashMap<String, Object> t : players) {
            PlayerEntity ent = new PlayerEntity();

            if (t.get("firstName") != null) {
                ent.setFirstName((String) t.get("firstName"));
            }
            if (t.get("lastName") != null) {
                ent.setLastName((String) t.get("lastName"));
            }
            if (t.get("personId") != null) {
                ent.setPersonId((String) t.get("personId"));
            }
            if (t.get("jersey") != null) {
                ent.setJersey((String) t.get("jersey"));
            }
            if (t.get("pos") != null) {
                ent.setPos((String) t.get("pos"));
            }
            if (t.get("isActive") != null) {
                ent.setIsActive((Boolean) t.get("isActive"));
            }
            if (MappingChecker.canBeParsedIntoLong((String) t.get("heightFeet"))) {
                ent.setHeightFeet(Long.parseLong((String) t.get("heightFeet")));
            }
            if (MappingChecker.canBeParsedIntoLong((String) t.get("heightInches"))) {
                ent.setHeightInches(Long.parseLong((String) t.get("heightInches")));
            }
            if (MappingChecker.canBeParsedIntoBigDecimal((String) t.get("heightMeters"))) {
                ent.setHeightMeters(new BigDecimal((String) t.get("heightMeters")));
            }
            if (MappingChecker.canBeParsedIntoLong((String) t.get("weightPounds"))) {
                ent.setWeightPounds(Long.parseLong((String) t.get("weightPounds")));
            }
            if (MappingChecker.canBeParsedIntoBigDecimal((String) t.get("weightKilograms"))) {
                ent.setWeightKilograms(new BigDecimal((String) t.get("weightKilograms")));
            }
            if (t.get("dateOfBirthUTC") != null && !((String) t.get("dateOfBirthUTC")).equalsIgnoreCase("")) {
                String dob = (String) t.get("dateOfBirthUTC");
                String yearDob = dob.split("-")[0];
                String monthDob = dob.split("-")[1];
                String dayDob = dob.split("-")[2];
                LocalDateTime dateTime = LocalDateTime.now().withYear(Integer.parseInt(yearDob)).withMonth(Integer.parseInt(monthDob))
                        .withDayOfMonth(Integer.parseInt(dayDob));
                ent.setDateOfBirthUTC(dateTime);
            }
            if (t.get("nbaDebutYear") != null) {
                ent.setNbaDebutYear((String) t.get("nbaDebutYear"));
            }
            if (MappingChecker.canBeParsedIntoLong((String) t.get("yearsPro"))) {
                ent.setYearsPro(Long.parseLong((String) t.get("yearsPro")));
            }
            if (t.get("collegeName") != null) {
                ent.setCollegeName((String) t.get("collegeName"));
            }
            if (t.get("lastAffliation") != null) {
                ent.setLastAffliation((String) t.get("lastAffliation"));
            }
            if (t.get("country") != null) {
                ent.setCountry((String) t.get("country"));
            }
            ent.setYear(year);
            PlayerEntity player = playerDAO.save(ent);
            playerList.add(playerMapper.entityToDto(player));
            if (t.get("teams") != null) {
                @SuppressWarnings("unchecked")
                ArrayList<LinkedHashMap<String, Object>> teams = (ArrayList<LinkedHashMap<String, Object>>) t.get("teams");
                for (LinkedHashMap<String, Object> t1 : teams) {
                    TeamEntity team = teamDAO.findByTeamId((String) t1.get("teamId"), year);
                    PlayerTeamEntity playerTeam = new PlayerTeamEntity();
                    playerTeam.setPlayer(player);
                    playerTeam.setSeasonStart((String) t1.get("seasonStart"));
                    playerTeam.setSeasonEnd((String) t1.get("seasonEnd"));
                    playerTeam.setTeam(team);
                    playerTeam.setPlayerRefId(player.getPersonId());
                    playerTeam.setTeamRefId(team.getTeamId());
                    playerTeamDAO.save(playerTeam);
                }
            }
        }
        return playerList;
    }

    public List<PlayerGameHistory> playerHistory(String name, String surname) {
        List<PlayerEntity> players = playerDAO.findByNameAndSurname(name, surname);
        return getPlayersHistories(players);
    }

    private List<PlayerGameHistory> getPlayersHistories(List<PlayerEntity> players) {
        List<PlayerGameHistory> hists = new ArrayList<>();
        Map<String, List<PlayerGameStats>> yearAverages = new HashMap<>();
        Map<String, PlayerGameStats> yearAveragesFinal = new HashMap<>();
        List<String> playerIds = players.parallelStream().map(PlayerEntity::getPersonId).distinct().collect(Collectors.toList());
        List<PlayerGameStatsEntity> playerStats = playerGameStatsCustom.findByPersonIds(playerIds);
        List<String> existingIds = new ArrayList<>();
        Set<Long> boxscoreIds = new HashSet<>();
        for (PlayerEntity player : players) {
            if (!existingIds.contains(player.getPersonId())) {
                PlayerGameHistory hist = new PlayerGameHistory();
                hist.setBoxscoreMap(new HashMap<>());
                hist.setPlayer(playerMapper.entityToDto(player));
                List<PlayerGameStatsEntity> indPlayerStats = playerStats.parallelStream().filter(isPersonId(player.getPersonId()))
                        .collect(Collectors.toList());
                for (PlayerGameStatsEntity indPlayerStat : indPlayerStats) {
                    boxscoreIds.add(indPlayerStat.getBoxscoreId());
                    hist.getBoxscoreMap().put(indPlayerStat.getBoxscoreId(), playerGameStatsMapper.entityToDto(indPlayerStat));
                }
                hists.add(hist);
                existingIds.add(player.getPersonId());
            }
        }
        List<BoxscoreEntity> boxscores = boxscoreDAO.findByIds(boxscoreIds);
        Map<String, List<Long>> boxByYear = new HashMap<>();
        for (BoxscoreEntity box : boxscores) {
            if (boxByYear.get(box.getYear()) == null) {
                boxByYear.put(box.getYear(), new ArrayList<>());
            }
            boxByYear.get(box.getYear()).add(box.getId());
        }
        for (Map.Entry<Long, PlayerGameStats> entry : hists.get(0).getBoxscoreMap().entrySet()) {
            for (Map.Entry<String, List<Long>> boxYearEntry : boxByYear.entrySet()) {
                if (boxYearEntry.getValue().contains(entry.getKey())) {
                    if (yearAverages.get(boxYearEntry.getKey()) == null) {
                        yearAverages.put(boxYearEntry.getKey(), new ArrayList<>());
                    }
                    yearAverages.get(boxYearEntry.getKey()).add(entry.getValue());
                }
            }
        }
        for (Map.Entry<String, List<PlayerGameStats>> entry : yearAverages.entrySet()) {
            PlayerGameStats year = new PlayerGameStats();
            Long totalAss = 0L;
            Long totalBlk = 0L;
            Long totalDefReb = 0L;
            Long totalFga = 0L;
            Long totalFgm = 0L;
            Long totalFta = 0L;
            Long totalFtm = 0L;
            Long totalTpa = 0L;
            Long totalTpm = 0L;
            Long totalOffReb = 0L;
            Long totalFouls = 0L;
            Long totalPts = 0L;
            Long totalStl = 0L;
            Long totalReb = 0L;
            Long totalTrn = 0L;
            BigDecimal totalFgp = BigDecimal.ZERO;
            BigDecimal totalTpp = BigDecimal.ZERO;
            BigDecimal totalFtp = BigDecimal.ZERO;
            for (PlayerGameStats pgs : entry.getValue()) {
                totalAss += pgs.getAssists();
                totalBlk += pgs.getBlocks();
                totalDefReb += pgs.getDefReb();
                totalFga += pgs.getFga();
                totalFgm += pgs.getFgm();
                totalFta += pgs.getFta();
                totalFtm += pgs.getFtm();
                totalOffReb += pgs.getOffReb();
                totalFouls += pgs.getpFouls();
                totalPts += pgs.getPoints();
                totalStl += pgs.getSteals();
                totalReb += pgs.getTotReb();
                totalTpa += pgs.getTpa();
                totalTpm += pgs.getTpm();
                totalTrn += pgs.getTurnovers();
                totalFgp = totalFgp.add(pgs.getFgp());
                totalFtp = totalFtp.add(pgs.getFtp());
                totalTpp = totalTpp.add(pgs.getTpp());
                System.out.println(pgs.toString());
            }
            year.setAssists(totalAss);
            year.setBlocks(totalBlk);
            year.setDefReb(totalDefReb);
            year.setFga(totalFga);
            year.setFgm(totalFgm);
            year.setFgp(totalFgp);
            year.setFta(totalFta);
            year.setFtm(totalFtm);
            year.setFtp(totalFtp);
            year.setOffReb(totalOffReb);
            year.setpFouls(totalFouls);
            year.setPoints(totalPts);
            year.setSteals(totalStl);
            year.setTotReb(totalReb);
            year.setTpa(totalTpa);
            year.setTpm(totalTpm);
            year.setTpp(totalTpp);
            year.setTurnovers(totalTrn);
            yearAveragesFinal.put(entry.getKey(), year);
        }
        hists.get(0).setYearAverages(yearAveragesFinal);
        return hists;
    }

    private Predicate<? super PlayerGameStatsEntity> isPersonId(String playerId) {
        return z -> z.getPersonId().equalsIgnoreCase(playerId);
    }

}
