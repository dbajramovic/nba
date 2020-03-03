package nba.dao.repos;

import java.util.List;
import java.util.Set;

import nba.dao.model.BoxscoreEntity;

public interface BoxscoreRepositoryCustom {

    public List<BoxscoreEntity> findByIds(Set<Long> boxscoreIds);

    List<String> findTeamIdsForGameId(String gameId);

}
