package nba.dao.repos;

import java.util.Map;

public interface PlayerSalaryRepositoryCustom {

    Map<String, Long> createPrices(String teamId, String year, String seasonEnd);

}
