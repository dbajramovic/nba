package nba.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TeamsController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TeamDAO teamDAO;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "teams", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Team> getTeam(@RequestParam String year, Model model) {
        final String url = "http://data.nba.net/prod/v1/" + year + "/teams.json";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            Map<String, String> params = new HashMap<>();
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }, params);
            Map<String, Object> map = response.getBody();
            List<LinkedHashMap<String, Object>> teams = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("league")) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> playerMap = (HashMap<String, Object>) entry.getValue();
                    for (Object o : playerMap.entrySet()) {
                        HashMap.Entry<String, Object> oMap = (HashMap.Entry<String, Object>) o;
                        teams = (ArrayList<LinkedHashMap<String, Object>>) oMap.getValue();
                    }
                    model.addAttribute("teams", teams);
                }
            }
            for (LinkedHashMap<String, Object> t : teams) {
                TeamEntity ent = new TeamEntity();
                if (t.get("altCityName") != null) {
                    ent.setAltCityName((String) t.get("altCityName"));
                }
                if (t.get("city") != null) {
                    ent.setCity((String) t.get("city"));
                }
                if (t.get("IsNBAFranchise") != null) {
                    ent.setConfName((String) t.get("IsNBAFranchise"));
                }
                if (t.get("divName") != null) {
                    ent.setDivName((String) t.get("divName"));
                }
                if (t.get("fullName") != null) {
                    ent.setFullName((String) t.get("fullName"));
                }
                if (t.get("isAllStar") != null) {
                    ent.setIsAllStar((Boolean) t.get("isAllStar"));
                }
                if (t.get("isNBAFranchise") != null) {
                    ent.setIsNBAFranchise((Boolean) t.get("isNBAFranchise"));
                }
                if (t.get("nickname") != null) {
                    ent.setNickname((String) t.get("nickname"));
                }
                if (t.get("teamId") != null) {
                    ent.setTeamId((String) t.get("teamId"));
                }
                if (t.get("tricode") != null) {
                    ent.setTricode((String) t.get("tricode"));
                }
                if (t.get("urlName") != null) {
                    ent.setUrlName((String) t.get("urlName"));
                }
                if (t.get("confName") != null) {
                    ent.setUrlName((String) t.get("confName"));
                }
                ent.setYear(year);
                teamDAO.save(ent);
            }
            return Collections.emptyList();

        } catch (Exception e) {
            System.out.println("SHIT:" + e.getMessage());
        }
        return Collections.emptyList();
    }

}
