package nba.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class PlayersController {

    @Autowired
    RestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "roster", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Player> getRoster(@RequestParam String year) {
        final String url = "http://data.nba.net/prod/v1/" + year + "/players.json";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            Map<String, String> params = new HashMap<>();
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }, params);
            Map<String, Object> map = response.getBody();
            List<Player> players = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("league")) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> playerMap = (HashMap<String, Object>) entry.getValue();
                    for (Object o : playerMap.entrySet()) {
                        HashMap.Entry<String, Object> oMap = (HashMap.Entry<String, Object>) o;
                        players = (ArrayList<Player>) oMap.getValue();
                    }
                    return players;
                }
            }
        } catch (Exception e) {
            System.out.println("SHIT");
        }
        return Collections.emptyList();
    }

}
