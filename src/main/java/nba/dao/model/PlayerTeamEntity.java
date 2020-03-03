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
public class PlayerTeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    TeamEntity team;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    PlayerEntity player;

    @Column(name = "PLAYER_REF_ID")
    String playerRefId;

    @Column(name = "TEAM_REF_ID")
    String teamRefId;

    String seasonStart;

    String seasonEnd;

}
