package nba.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    SalaryController saCont;

    @PostMapping(value = "refreshAll", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String refreshAll(Model model) throws IOException {
        LOGGER.info("Saving teams...");
        tCont.saveAllTeams(model);
        LOGGER.info("Saving players...");
        pCont.saveAllPlayers(model);
        LOGGER.info("Saving schedules...");
        sCont.createAllSchedules(model);
        sCont.saveAllTeams(model);
        LOGGER.info("Saving games(play by play)...");
        gCont.saveAllGames(model);
        LOGGER.info("Saving boxscores...");
        bCont.saveAllBoxscores(model);
        LOGGER.info("Saving salaries...");
        saCont.saveAllSalaries(model);
        return "Saved all stuff";
    }

}
