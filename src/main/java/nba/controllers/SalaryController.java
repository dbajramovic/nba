package nba.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nba.model.GamePriceStructure;
import nba.model.PlayerSalary;
import nba.service.SalaryService;

@Controller
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryController.class);

    @GetMapping(value = "saveAllSalaries", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PlayerSalary> saveAllSalaries(Model model) throws IOException {
        LOGGER.info("Saving salaries");
        return salaryService.saveAllSalaries();
    }

    @GetMapping(value = "getPriceOfGame", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public GamePriceStructure getPriceOfGame(@RequestParam final String gameId) throws IOException {
        return salaryService.getPriceOfGame(gameId);
    }

}
