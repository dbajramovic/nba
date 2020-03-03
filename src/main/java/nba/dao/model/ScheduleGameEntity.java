package nba.dao.model;

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
public class ScheduleGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    Long id;

    String homeAbrv;
    String visitAbrv;
    String gameId;
    String date;
    Boolean isLeaguePass;

    @ManyToOne
    @JoinColumn(name = "SCHEDULE")
    ScheduleEntity schedule;

    @ManyToOne
    @JoinColumn(name = "GAME")
    GameEntity game;

}
