package nba.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import nba.dao.model.PlayerSalaryEntity;
import nba.model.PlayerSalary;

@Service
public class SalaryMapper {
    public PlayerSalary entitytoDto(PlayerSalaryEntity playEnt) {

        PlayerSalary play = new PlayerSalary();
        play.setId(playEnt.getId());
        play.setPersonId(playEnt.getPersonId());
        play.setGaranteedSalary(playEnt.getGaranteedSalary());
        play.setSalary(playEnt.getSalary());
        play.setYear(playEnt.getYear());
        return play;
    }

    public List<PlayerSalary> entitesToDtos(List<PlayerSalaryEntity> playEnts) {
        List<PlayerSalary> plays = new ArrayList<>();
        for (PlayerSalaryEntity ent : playEnts) {
            plays.add(entitytoDto(ent));
        }
        return plays;
    }
}
