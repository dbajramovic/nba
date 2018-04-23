package nba.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RefreshTableController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshTableController.class);

    @Autowired
    PlayersController pCont;

    @Autowired
    TeamsController tCont;

    @Autowired
    ScheduleController sCont;

    @Autowired
    GameController gCont;
    @Autowired
    BoxscoreController bCont;

    @RequestMapping(value = "refreshAll", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String refreshAll(Model model) {
        LOGGER.info("Saving teams...");
        tCont.saveAllTeams(model);
        LOGGER.info("Saving players...");
        pCont.saveAllPlayers(model);
        LOGGER.info("Saving schedules...");
        sCont.saveAllTeams(model);
        LOGGER.info("Saving games(play by play)...");
        gCont.saveAllGames(model);
        LOGGER.info("Saving boxscores...");
        bCont.saveAllBoxscores(model);
        return "Saved all stuff";
    }

}