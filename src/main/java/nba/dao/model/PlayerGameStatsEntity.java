package nba.dao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerGameStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    String personId;
    String teamId;
    String isOnCourt;
    Long points;
    String pos;
    String min;
    Long fgm;
    Long fga;
    BigDecimal fgp;
    Long ftm;
    Long fta;
    BigDecimal ftp;
    Long tpm;
    Long tpa;
    BigDecimal tpp;
    Long offReb;
    Long defReb;
    Long totReb;
    Long assists;
    Long pFouls;
    Long steals;
    Long turnovers;
    Long blocks;
    String plusMinus;
    String dnp;

    @ManyToOne
    @JoinColumn(name = "BOXSCORE")
    BoxscoreEntity boxscore;

    public Long getBoxscoreId() {
        return boxscore.getId();
    }

}
