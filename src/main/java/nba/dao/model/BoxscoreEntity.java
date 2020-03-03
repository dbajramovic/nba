package nba.dao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BoxscoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    Long id;

    String timesTied;
    String leadChanges;
    String year;
    String gameId;

    @OneToOne
    @JoinColumn(name = "HTEAM")
    TeamBoxscoreStatEntity hTeam;

    @OneToOne
    @JoinColumn(name = "ATEAM")
    TeamBoxscoreStatEntity vTeam;

    @OneToMany(mappedBy = "boxscore")
    List<PlayerGameStatsEntity> activePlayers;

}
